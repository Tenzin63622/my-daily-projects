package bank.example.system.service;
import bank.example.system.repository.UserRepository;
import bank.example.system.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository repo;
    public User register(User user){
        return repo.save(user);
    }
    public List<User>getAll(){
       return repo.findAll();
    }
    public User update(Long id,User newUser){
        User existing=repo.findById(id).orElseThrow();
        existing.setName(newUser.getName());
        existing.setEmail(newUser.getEmail());
        existing.setPassword(newUser.getPassword());
        return repo.save(existing);
    }
    public void delete(Long id){
        repo.deleteById(id);
    }
}
