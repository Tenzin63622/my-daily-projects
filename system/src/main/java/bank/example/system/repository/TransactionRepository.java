package bank.example.system.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import bank.example.system.entity.Transaction;
public interface TransactionRepository extends JpaRepository<Transaction, Long> {}