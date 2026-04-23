package bank.example.system.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import bank.example.system.entity.Account;
public interface AccountRepository extends JpaRepository<Account, Long> {}
