package bank.example.system.controller;
import org.springframework.beans.factory.annotation.Autowired;
import bank.example.system.service.AccountService;
import bank.example.system.entity.Account;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService service;
    @PostMapping("/{userId}")
    public Account create(@PathVariable Long userId){
        return service.createAccount(userId);
    }
}
