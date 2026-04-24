package coupon.example.coupon.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "coupons")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false) // no two coupons can have same code
    private String code;

    @Enumerated(EnumType.STRING) // Enum is used when you know all possible values in advance and they never
                                 // change.
    private DiscountType discountType;

    private double discountValue;
    private LocalDateTime expiryDate;
    private double minAmount;
    private double maxDiscount;
    private boolean isActive;
    private boolean newUserOnly;
    private boolean highSpenderReward;
    private boolean blockCouponHunters;

    // getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
    }

    public double getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(double maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isNewUserOnly() {
        return newUserOnly;
    }

    public boolean isHighSpenderReward() {
        return highSpenderReward;
    }

    public boolean isBlockCouponHunters() {
        return blockCouponHunters;
    }

    public void setNewUserOnly(boolean newUserOnly) {
        this.newUserOnly = newUserOnly;
    }

    public void setHighSpenderReward(boolean highSpenderReward) {
        this.highSpenderReward = highSpenderReward;
    }

    public void setBlockCouponHunters(boolean blockCouponHunters) {
        this.blockCouponHunters = blockCouponHunters;
    }
}