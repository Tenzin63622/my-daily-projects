package coupon.example.coupon.controller;

import coupon.example.coupon.dto.*;
import coupon.example.coupon.entity.Coupon;
import coupon.example.coupon.service.CouponService;
import coupon.example.coupon.service.UserService;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import coupon.example.coupon.entity.User;
@RestController
@RequestMapping("/coupons")
public class CouponController {

    private final CouponService service;
     private final UserService userService;
    public CouponController(CouponService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping
    public Coupon create(@RequestBody CouponRequest request) {
        return service.createCoupon(request);
    }

    @GetMapping
    public List<Coupon> getAll() {
        return service.getAllCoupons();
    }

    @GetMapping("/code/{code}")
    public Coupon getByCode(@PathVariable String code) {
        return service.getByCode(code);
    }

    @PostMapping("/apply")
    public ApplyCouponResponse apply(@RequestBody ApplyCouponRequest request) {
        return service.applyCoupon(request);
    }

    // updation
    @PutMapping("/{id}")
    public Coupon update(@PathVariable Long id, @RequestBody CouponRequest request) {
        return service.updateCoupon(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteCoupon(id);
        return "Deleted successfully";
    }

    // for getting all the coupons used by a user
    @GetMapping("/usage/{userId}")
    public List<UserCouponUsageResponse> getUserUsage(@PathVariable Long userId) {
        return service.getUserUsage(userId);
    }

    // building the welcome after login after 30 day
    @PutMapping("/login/{id}")
    public User login(@PathVariable Long id) {
        return userService.login(id);
    }
}