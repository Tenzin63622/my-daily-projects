package bank.example.system.service;
import bank.example.system.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import bank.example.system.entity.Account;
import bank.example.system.entity.Transaction;
import bank.example.system.repository.TransactionRepository;
import java.time.LocalDateTime;
@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private TransactionRepository txnRepo;

    public Transaction deposit(Long accountId, double amount) {

        Account acc = accountRepo.findById(accountId).orElseThrow();

        acc.setBalance(acc.getBalance() + amount);
        accountRepo.save(acc);

        Transaction txn = new Transaction();
        txn.setType("DEPOSIT");
        txn.setAmount(amount);
        txn.setDate(LocalDateTime.now());
        txn.setAccount(acc);

        return txnRepo.save(txn);
    }

    public Transaction withdraw(Long accountId, double amount) {

        Account acc = accountRepo.findById(accountId).orElseThrow();

        if (acc.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        acc.setBalance(acc.getBalance() - amount);
        accountRepo.save(acc);

        Transaction txn = new Transaction();
        txn.setType("WITHDRAW");
        txn.setAmount(amount);
        txn.setDate(LocalDateTime.now());
        txn.setAccount(acc);

        return txnRepo.save(txn);
    }
}