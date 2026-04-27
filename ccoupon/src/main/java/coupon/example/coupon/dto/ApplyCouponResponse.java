package coupon.example.coupon.dto;

public class ApplyCouponResponse {
    public double originalAmount;
    public double discount;
    public double finalAmount;

    public ApplyCouponResponse(double originalAmount, double discount, double finalAmount) {
        this.originalAmount = originalAmount;
        this.discount = discount;
        this.finalAmount = finalAmount;
    }
    
}