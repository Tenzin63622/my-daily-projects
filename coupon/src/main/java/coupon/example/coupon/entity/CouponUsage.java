package coupon.example.coupon.entity;
import jakarta.persistence.*;

@Entity
public class CouponUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    private int usageCount;

    // getters & setters
    public long getId(){
        return id;
    }
    public void setId(Long id){
        this.id =id;
    }
    public long getUserId(){
        return userId;
    }
    public void setUserId(Long userId){
        this.userId=userId;
    }
    public Coupon getCoupon(){
        return coupon;
    }
    public void setCoupon(Coupon coupon){
        this.coupon=coupon;
    }
    public int getUsageCount(){
        return usageCount;
    }
    public  void setUsageCount(int usageCount){
        this.usageCount=usageCount;
    }
}