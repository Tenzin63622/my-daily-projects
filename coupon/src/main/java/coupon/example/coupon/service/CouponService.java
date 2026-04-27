package coupon.example.coupon.service;

import coupon.example.coupon.entity.*;
import coupon.example.coupon.repository.*;
import coupon.example.coupon.dto.*;
import coupon.example.coupon.exception.CouponException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.time.DayOfWeek;


@Service
public class CouponService {
    // private static final double GLOBAL_DISCOUNT = 10;
    private final CouponRepository couponRepo;
    private final CouponUsageRepository usageRepo;
    
    public CouponService(CouponRepository couponRepo, CouponUsageRepository usageRepo) {
        this.couponRepo = couponRepo;
        this.usageRepo = usageRepo;
    }


    // Creating the coupon r
    public Coupon createCoupon(CouponRequest request) {
        Coupon coupon = new Coupon();
        coupon.setCode(request.code);
        coupon.setDiscountType(request.discountType);
        coupon.setDiscountValue(request.discountValue);
        coupon.setExpiryDate(request.expiryDate);
        coupon.setMinAmount(request.minAmount);
        coupon.setMaxDiscount(request.maxDiscount);
        coupon.setActive(request.isActive);
        return couponRepo.save(coupon);
    }

    // get all the coupons
    public List<Coupon> getAllCoupons() {
        return couponRepo.findAll();
    }

    // getting coupon by code
    public Coupon getByCode(String code) {
        return couponRepo.findByCode(code)
                .orElseThrow(() -> new CouponException("Coupon not found"));
    }
    //getting coupon by id
    // public Coupon getById(Long id){
    //     return couponRepo.findById(id)
    //         .orElseThrow(() -> new CouponException("Coupon not found"));
    // }

    // applying the coupon
    public ApplyCouponResponse applyCoupon(ApplyCouponRequest request) {

        Coupon coupon = getByCode(request.code);

        // validation for the coupon
        if (!coupon.isActive())
            throw new CouponException("Coupon is inactive");

        if (coupon.getExpiryDate().isBefore(LocalDateTime.now()))
            throw new CouponException("Coupon expired");

        if (request.orderAmount < coupon.getMinAmount())
            throw new CouponException("Minimum amount not met");

        // Calculating the discount
        double discount = 0;

        if (coupon.getDiscountType() == DiscountType.PERCENTAGE) {
            discount = (request.orderAmount * coupon.getDiscountValue()) / 100;
        } else {
            discount = coupon.getDiscountValue();
        }

        // applying max discount limit 
        if (discount > coupon.getMaxDiscount()) {
            discount = coupon.getMaxDiscount();
        }

        double finalAmount = request.orderAmount - discount;
        // // if i allow global discount
        // finalAmount -= GLOBAL_DISCOUNT;

        // // to prevent negative final amount after applying global discount
        // if (finalAmount < 0) {
        // finalAmount = 0;
        // }
        // USAGE TRACKING (OPTIONAL)
        // --- if i allow every friday special discount on top of coupon discount

        if (LocalDateTime.now().getDayOfWeek() == DayOfWeek.THURSDAY) {
            finalAmount = finalAmount - 10;
        }

        // Prevent negative value
        if (finalAmount < 0) {
            finalAmount = 0;
        }
       //below code handles tracking how many times a user uses a coupon.
        if (request.userId != null) {
            CouponUsage usage = usageRepo
                    .findByUserIdAndCouponId(request.userId, coupon.getId())
                    .orElse(new CouponUsage());

            usage.setUserId(request.userId);
            usage.setCoupon(coupon);
            usage.setUsageCount(usage.getUsageCount() + 1);

            usageRepo.save(usage);
        }

        return new ApplyCouponResponse(request.orderAmount, discount, finalAmount);
    }

    // for updating the coupon
    public Coupon updateCoupon(Long id, CouponRequest request) {
        Coupon coupon = couponRepo.findById(id)
                .orElseThrow(() -> new CouponException("Coupon not found"));

        coupon.setDiscountValue(request.discountValue);
        coupon.setExpiryDate(request.expiryDate);
        coupon.setMinAmount(request.minAmount);
        coupon.setMaxDiscount(request.maxDiscount);
        coupon.setActive(request.isActive);

        return couponRepo.save(coupon);
    }

    // for deleting the coupon
    public void deleteCoupon(Long id) {
        couponRepo.deleteById(id);
    }
    //for getting all coupons used by a user 
    public List<UserCouponUsageResponse> getUserUsage(Long userId) {

    List<CouponUsage> usages = usageRepo.findByUserId(userId);

    return usages.stream()
            .map(u -> new UserCouponUsageResponse(
                    u.getCoupon().getCode(),
                    u.getUsageCount()
            ))
            .toList();
      }

    }