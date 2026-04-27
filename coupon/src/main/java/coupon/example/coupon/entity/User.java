package coupon.example.coupon.entity;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double totalSpent = 0;
    private int totalOrders = 0;
    private boolean couponHunter = false;

    public Long getId() { return id; }
    public String getName() { return name; }
    public double getTotalSpent() { return totalSpent; }
    public int getTotalOrders() { return totalOrders; }
    public boolean isCouponHunter() { return couponHunter; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setTotalSpent(double totalSpent) { this.totalSpent = totalSpent; }
    public void setTotalOrders(int totalOrders) { this.totalOrders = totalOrders; }
    public void setCouponHunter(boolean couponHunter) { this.couponHunter = couponHunter; }
}