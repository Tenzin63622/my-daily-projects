package coupon.example.coupon.controller;

import coupon.example.coupon.dto.*;
import coupon.example.coupon.entity.Coupon;
import coupon.example.coupon.service.CouponService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    private final CouponService service;

    public CouponController(CouponService service) {
        this.service = service;
    }

    @PostMapping
    public Coupon create(@RequestBody CouponRequest request) {
        return service.createCoupon(request);
    }

    @GetMapping
    public List<Coupon> getAll() {
        return service.getAllCoupons();
    }

    @GetMapping("/{code}")
    public Coupon getByCode(@PathVariable String code) {
        return service.getByCode(code);
    }

    // @GetMapping("/id/{id}")
    // public Coupon getById(@PathVariable Long id) {
    // return service.getById(id);
    // }
    @PostMapping("/apply")
    public ApplyCouponResponse apply(@RequestBody ApplyCouponRequest request) {
        return service.applyCoupon(request);
    }

    @PutMapping("/{id}")
    public Coupon update(@PathVariable Long id, @RequestBody CouponRequest request) {
        return service.updateCoupon(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteCoupon(id);
        return "Deleted successfully";
    }

    // for getting allt the coupons used by a user
    @GetMapping("/usage/{userId}")
    public List<UserCouponUsageResponse> getUserUsage(@PathVariable Long userId) {
        return service.getUserUsage(userId);
    }
}