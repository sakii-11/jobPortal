package com.sakii.jobportal.entity;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Table(name = "job_seeker_profile")
public class JobSeekerProfile {

  @Id
  private Integer userAccountId;

  @OneToOne
  @JoinColumn(name = "user_account_id")
  @MapsId
  private Users userId;

  private String firstName;
  private String lastName;
  private String city;
  private String state;
  private String country;
  private String workAuthorization;
  private String employmentType;
  private String resume;
  @Column(nullable = true, length = 64)
  private String profilePhoto;

  @OneToMany(targetEntity = Skills.class, cascade = CascadeType.ALL, mappedBy = "jobSeekerProfile")
  private List<Skills> skills;

  
  @Transient
  public String getPhotosImagePath() {
    if(profilePhoto == null || userAccountId == null) return null;
    return "/photos/candidate/" + userAccountId + "/" + profilePhoto;
  }

  public JobSeekerProfile(Users user){
    this.userId = user;
  }

}
