package bank.example.system.service;
import bank.example.system.repository.AccountRepository;
import bank.example.system.repository.UserRepository;
import bank.example.system.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Service;
import  bank.example.system.entity.User;
import java.util.UUID;
import java.util.List;;
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private UserRepository userRepo;
    public Account createAccount(Long userId){
        User user=userRepo.findById(userId).orElseThrow();
        
        Account acc=new Account();
        acc.setAccountNumber(UUID.randomUUID().toString());
        acc.setBalance(0);
        acc.setUser(user);
        return accountRepo.save(acc);

    }
    public List<Account> getAllAccounts() {
    return accountRepo.findAll();
}
}
