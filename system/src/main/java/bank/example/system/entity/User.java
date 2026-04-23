package bank.example.system.entity;
import jakarta.persistence.*;
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String password;

 //getter for this are below
  public long getId(){
    return id;
  }
  public String getName(){
    return name;
  }
  public String getEmail(){
    return email;
  }
  public String getPassword(){
    return password;
  }
  //setter for above is belwo 
  public void setName(String name){
      this.name=name;
  }
  public void setEmail(String email){
    this.email=email;
  }
  public void setPassword(String password){
    this.password=password;
  }
}