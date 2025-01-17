package com.sakii.jobportal.entity;
import java.util.Date;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class JobPostActivity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer jobPostId;

  @ManyToOne
  @JoinColumn(name = "postedById", referencedColumnName = "userId")
  private Users postedById;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "jobLocationId", referencedColumnName = "Id")
  private JobLocation JobLocationId;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "jobCompanyId", referencedColumnName="Id")
  private JobCompany jobCompanyId;

  @Transient
  private Boolean isActive;

  @Transient
  private Boolean isSaved;

  @Length(max = 10000)
  private String descriptionOfJob;

  private String jobType;
  private String salary;
  private String remote;
  @DateTimeFormat(pattern = "dd-MM-yyyy")
  private Date postedDate;
  private String jobTitle;




}
