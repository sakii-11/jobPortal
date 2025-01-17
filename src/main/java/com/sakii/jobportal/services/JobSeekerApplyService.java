package com.sakii.jobportal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakii.jobportal.entity.JobPostActivity;
import com.sakii.jobportal.entity.JobSeekerApply;
import com.sakii.jobportal.entity.JobSeekerProfile;
import com.sakii.jobportal.repository.JobSeekerApplyRepository;

@Service
public class JobSeekerApplyService {
  private final JobSeekerApplyRepository jobSeekerApplyRepository;

  @Autowired
  public JobSeekerApplyService(JobSeekerApplyRepository jobSeekerApplyRepository) {
    this.jobSeekerApplyRepository = jobSeekerApplyRepository;
  }

  public List<JobSeekerApply> getCandidatesJobs(JobSeekerProfile getUserAccountId) {
    return jobSeekerApplyRepository.findByUserId(getUserAccountId);
  }

  public List<JobSeekerApply> getJobCandidates(JobPostActivity job) {
    return jobSeekerApplyRepository.findByJob(job);
  }

  public void addNew(JobSeekerApply jobSeekerApply) {
    jobSeekerApplyRepository.save(jobSeekerApply);
  }
}
