package com.sakii.jobportal.entity;
import java.beans.Transient;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "recruiter_profile")
public class RecruiterProfile {
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
  private String company; 
  private String profilePhoto;

  public RecruiterProfile(Users users) {
    this.userId = users;
  }

  @Transient
  public String getPhotosImagePath() {
    if(profilePhoto == null) return null;
    return "/photos/recruiter/" + userAccountId + "/" + profilePhoto;
  }


  public RecruiterProfile(int userAccountId, Users userId, String firstName, String lastName, String city, String state, String company, String profilePhoto, String country) {
    this.userAccountId = userAccountId;
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.city = city;
    this.state = state;
    this.company = company;
    this.country = country;
    this.profilePhoto = profilePhoto;
  }

  @Override
  public String toString() {
      return "RecruiterProfile{" +
              "userAccountId=" + userAccountId +
              ", userId=" + userId +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", city='" + city + '\'' +
              ", state='" + state + '\'' +
              ", country='" + country + '\'' +
              ", company='" + company + '\'' +
              ", profilePhoto='" + profilePhoto + '\'' +
              '}';
  }

}
