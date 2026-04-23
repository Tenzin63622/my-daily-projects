package bank.example.system.entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;
    private double amount; 
    private LocalDateTime date;

    @ManyToOne
    private Account account;

    //getter 
    public long getId(){
        return id;
    }
    public String getType(){
        return type;
    }
    public double getAmoumt(){
        return amount;
    }
    public LocalDateTime getDate(){
        return date;
    }
   public Account getAccount(){
    return account;
   }
  //now setters is below
  public void setType(String type){
    this.type=type;
  }
   public void setAmount(double amount){
    this.amount=amount;
   }
   public void setDate(LocalDateTime date){
    this.date=date;

   }
  public void setAccount(Account account){
    this.account=account;
  }
}