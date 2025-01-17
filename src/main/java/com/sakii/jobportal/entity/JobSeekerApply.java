package com.sakii.jobportal.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = {"userId", "Job"})
})
public class JobSeekerApply implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "userId", referencedColumnName = "user_account_id")
  private JobSeekerProfile userId;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "job", referencedColumnName = "jobPostId")
  private JobPostActivity job;

  @DateTimeFormat(pattern = "dd-MM-yyyy")
  private Date applyDate;


}
