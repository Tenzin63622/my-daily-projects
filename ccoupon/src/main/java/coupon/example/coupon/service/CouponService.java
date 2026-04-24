package coupon.example.coupon.service;

import coupon.example.coupon.entity.*;
import coupon.example.coupon.repository.*;
import coupon.example.coupon.dto.*;
import coupon.example.coupon.exception.CouponException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CouponService {

    private final CouponRepository couponRepo;
    private final CouponUsageRepository usageRepo;
    private final UserRepository userRepo;

    public CouponService(CouponRepository couponRepo,
            CouponUsageRepository usageRepo,
            UserRepository userRepo) {
        this.couponRepo = couponRepo;
        this.usageRepo = usageRepo;
        this.userRepo = userRepo;
    }

    // Creating a coupon
    public Coupon createCoupon(CouponRequest request) {
        Coupon coupon = new Coupon();
        coupon.setCode(request.code);
        coupon.setDiscountType(request.discountType);
        coupon.setDiscountValue(request.discountValue);
        coupon.setExpiryDate(request.expiryDate);
        coupon.setMinAmount(request.minAmount);
        coupon.setMaxDiscount(request.maxDiscount);
        coupon.setActive(request.isActive);

        coupon.setNewUserOnly(request.newUserOnly);
        coupon.setHighSpenderReward(request.highSpenderReward);
        coupon.setBlockCouponHunters(request.blockCouponHunters);

        return couponRepo.save(coupon);
    }

    // code for updating coupon
    public Coupon updateCoupon(Long id, CouponRequest request) {

        Coupon coupon = couponRepo.findById(id)
                .orElseThrow(() -> new CouponException("Coupon not found"));

        // update fields
        coupon.setCode(request.code);
        coupon.setDiscountType(request.discountType);
        coupon.setDiscountValue(request.discountValue);
        coupon.setExpiryDate(request.expiryDate);
        coupon.setMinAmount(request.minAmount);
        coupon.setMaxDiscount(request.maxDiscount);
        coupon.setActive(request.isActive);

        return couponRepo.save(coupon);
    }

    // getting all coupons
    public List<Coupon> getAllCoupons() {
        return couponRepo.findAll();
    }

    public Coupon getByCode(String code) {
        return couponRepo.findByCode(code)
                .orElseThrow(() -> new CouponException("Coupon not found"));
    }

    // Applying coupon
    public ApplyCouponResponse applyCoupon(ApplyCouponRequest request) {

    // first we will user
    User user = userRepo.findById(request.userId)
            .orElseThrow(() -> new CouponException("User not found"));

    //  Detect coupon hunter BEFORE applying coupon
    if (user.getTotalCouponUsed() >= 5 && user.getTotalSpent() < 700) {
        user.setCouponHunter(true);
        userRepo.save(user);
    }

    // Block coupon hunter
    if (user.isCouponHunter()) {
        throw new CouponException("Coupon blocked: user detected as coupon hunter");
    }

    // Get coupon
    Coupon coupon = getByCode(request.code);

    // Coupon validations
    if (!coupon.isActive())
        throw new CouponException("Coupon inactive");

    if (coupon.getExpiryDate().isBefore(LocalDateTime.now()))
        throw new CouponException("Expired");

    if (request.orderAmount < coupon.getMinAmount())
        throw new CouponException("Minimum amount not met");

    // Business rules

    // new user
    if (coupon.isNewUserOnly() && user.getTotalOrders() > 0)
        throw new CouponException("Only for new users");

    // block hunters per coupon 
    if (coupon.isBlockCouponHunters() && user.isCouponHunter())
        throw new CouponException("Coupon blocked for this user");

    // high spender
    if (coupon.isHighSpenderReward() && user.getTotalSpent() < 2000)
        throw new CouponException("Only for high spending users");

    //  Calculate discount
    double discount = (coupon.getDiscountType() == DiscountType.PERCENTAGE)
            ? (request.orderAmount * coupon.getDiscountValue()) / 100
            : coupon.getDiscountValue();

    if (discount > coupon.getMaxDiscount())
        discount = coupon.getMaxDiscount();

    double finalAmount = request.orderAmount - discount;

    if (finalAmount < 0)
        finalAmount = 0;

    // Track coupon usage
    CouponUsage usage = usageRepo
            .findByUserIdAndCouponId(request.userId, coupon.getId())
            .orElse(new CouponUsage());

    usage.setUserId(request.userId);
    usage.setCoupon(coupon);
    usage.setUsageCount(usage.getUsageCount() + 1);

    usageRepo.save(usage);

    //  Update user stats
    user.setTotalOrders(user.getTotalOrders() + 1);
    user.setTotalSpent(user.getTotalSpent() + finalAmount);
    user.setTotalCouponUsed(user.getTotalCouponUsed() + 1);

    //Detect again AFTER update (important for next request)
    if (user.getTotalCouponUsed() >= 5 && user.getTotalSpent() < 700) {
        user.setCouponHunter(true);
    }

    userRepo.save(user);

    // Returning response
    return new ApplyCouponResponse(request.orderAmount, discount, finalAmount);
}
    

    // Deleting a coupon
    public void deleteCoupon(Long id) {
        couponRepo.deleteById(id);
    }

    // getting all the coupons used by a user
    public List<UserCouponUsageResponse> getUserUsage(Long userId) {
        return usageRepo.findByUserId(userId)
                .stream()
                .map(u -> new UserCouponUsageResponse(
                        u.getCoupon().getCode(),
                        u.getUsageCount()))
                .toList();
    }
}