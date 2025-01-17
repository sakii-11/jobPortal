package com.sakii.jobportal.entity;
import java.sql.Date;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class Users {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int userId;

  @Column(unique = true)
  private String email;

  @NotEmpty
  private String password;


  private boolean isActive;

  @DateTimeFormat(pattern = "dd-MM-yyyy")
  private Date registrationDate;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "userTypeId", referencedColumnName = "userTypeId")
  private UsersType userTypeId;

  @Override
  public String toString() {
    return "Users{" +
        "userId=" + userId +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", isActive=" + isActive +
        ", registrationDate=" + registrationDate +
        ", userTypeId=" + userTypeId +
        '}';
  }

}
