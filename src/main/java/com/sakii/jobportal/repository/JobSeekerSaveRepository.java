package com.sakii.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sakii.jobportal.entity.JobPostActivity;
import com.sakii.jobportal.entity.JobSeekerProfile;
import com.sakii.jobportal.entity.JobSeekerSave;

@Repository
public interface JobSeekerSaveRepository extends JpaRepository<JobSeekerSave, Integer> {
  List<JobSeekerSave> findByUserId(JobSeekerProfile getUserAccountId);

  List<JobSeekerSave> findByJob(JobPostActivity job);
}
