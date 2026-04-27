package coupon.example.coupon.dto;

import coupon.example.coupon.entity.DiscountType;
import java.time.LocalDateTime;

public class CouponRequest {
    public String code;
    public DiscountType discountType;
    public double discountValue;
    public LocalDateTime expiryDate;
    public double minAmount;
    public double maxDiscount;
    public boolean isActive;
    public boolean newUserOnly;
    public boolean lowSpenderOnly;
    public boolean blockCouponHunters;

    public CouponRequest() {
    }
}