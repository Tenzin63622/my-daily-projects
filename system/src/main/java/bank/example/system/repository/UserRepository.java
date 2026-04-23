package bank.example.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import bank.example.system.entity.User;
public interface UserRepository extends JpaRepository<User, Long> {}