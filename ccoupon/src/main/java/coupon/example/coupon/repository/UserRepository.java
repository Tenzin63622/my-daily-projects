package coupon.example.coupon.repository;

import coupon.example.coupon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}