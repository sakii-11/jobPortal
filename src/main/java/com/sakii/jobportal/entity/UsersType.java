package com.sakii.jobportal.entity;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersType {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int userTypeId;

  private String userTypeName;

  @OneToMany(targetEntity = Users.class,mappedBy = "userTypeId", cascade = CascadeType.ALL)
  private List<Users> users;

  @Override
  public String toString(){
    return "UserType{" + 
            "userTypeId=" + userTypeId +
            ", userTypeName="+ userTypeName + '\'' +
            '}';
  }
}
