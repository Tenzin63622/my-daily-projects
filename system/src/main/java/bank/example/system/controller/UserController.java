package bank.example.system.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import  bank.example.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import bank.example.system.entity.User;
import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    // CREATE
    @PostMapping
    public User register(@RequestBody User user){
        return service.register(user);
    }

    // READ
    @GetMapping
    public List<User> getAll(){
        return service.getAll();
    }

    // UPDATE
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return service.update(id, user);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "User deleted successfully";
    }
}