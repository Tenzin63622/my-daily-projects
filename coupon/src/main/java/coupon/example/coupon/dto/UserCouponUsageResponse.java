package coupon.example.coupon.dto;

public class UserCouponUsageResponse {

    public String couponCode;
    public int usageCount;

    public UserCouponUsageResponse(String couponCode, int usageCount) {
        this.couponCode = couponCode;
        this.usageCount = usageCount;
    }
}