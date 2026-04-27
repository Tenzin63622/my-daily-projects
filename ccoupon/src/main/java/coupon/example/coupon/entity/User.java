package coupon.example.coupon.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean hasWelcomeBackOffer;
    private String name;

    private double totalSpent = 0;
    private int totalOrders = 0;

    private int totalCouponUsed = 0;

    private boolean couponHunter = false;
    private LocalDateTime lastLoginDate;

    // getters & setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public int getTotalCouponUsed() {
        return totalCouponUsed;
    }

    public boolean isCouponHunter() {
        return couponHunter;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public void setTotalCouponUsed(int totalCouponUsed) {
        this.totalCouponUsed = totalCouponUsed;
    }

    public void setCouponHunter(boolean couponHunter) {
        this.couponHunter = couponHunter;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public boolean isHasWelcomeBackOffer() {
        return hasWelcomeBackOffer;
    }

    public void setHasWelcomeBackOffer(boolean hasWelcomeBackOffer) {
        this.hasWelcomeBackOffer = hasWelcomeBackOffer;
    }
}