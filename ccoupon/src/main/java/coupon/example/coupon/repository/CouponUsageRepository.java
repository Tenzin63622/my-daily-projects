package coupon.example.coupon.repository;

import coupon.example.coupon.entity.CouponUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;
public interface CouponUsageRepository extends JpaRepository<CouponUsage, Long> {
    Optional<CouponUsage> findByUserIdAndCouponId(Long userId, Long couponId);
    List<CouponUsage> findByUserId(Long userId);
    
}