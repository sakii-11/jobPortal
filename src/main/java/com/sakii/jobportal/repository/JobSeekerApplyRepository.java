package com.sakii.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakii.jobportal.entity.JobPostActivity;
import com.sakii.jobportal.entity.JobSeekerApply;
import com.sakii.jobportal.entity.JobSeekerProfile;

public interface JobSeekerApplyRepository extends JpaRepository<JobSeekerApply, Integer> {

  List<JobSeekerApply> findByUserId(JobSeekerProfile userId);

  List<JobSeekerApply> findByJob(JobPostActivity job);

}
