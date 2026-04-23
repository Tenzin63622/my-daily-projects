package bank.example.system.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import bank.example.system.service.TransactionService;
import bank.example.system.entity.Transaction; // ✅ IMPORTANT

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping("/deposit/{accountId}")
    public Transaction deposit(@PathVariable Long accountId,
     @RequestParam double amount) {
      return service.deposit(accountId, amount);
    }

    @PostMapping("/withdraw/{accountId}")
    public Transaction withdraw(@PathVariable Long accountId,
      @RequestParam double amount) {
       return service.withdraw(accountId, amount);
    }
}