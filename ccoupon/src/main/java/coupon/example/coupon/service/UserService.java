package coupon.example.coupon.service;

import coupon.example.coupon.entity.User;
import coupon.example.coupon.exception.CouponException;
import coupon.example.coupon.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User login(Long id) {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new CouponException("User not found"));

        LocalDateTime now = LocalDateTime.now();

        if (user.getLastLoginDate() != null) {
            long days = ChronoUnit.DAYS.between(user.getLastLoginDate(), now);

            if (days >= 30) {
                user.setHasWelcomeBackOffer(true); // 👈 ADD FLAG IN USER
            }
        }

        user.setLastLoginDate(now);

        return userRepo.save(user);
    }
}