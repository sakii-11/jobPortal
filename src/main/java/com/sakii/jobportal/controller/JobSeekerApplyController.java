package com.sakii.jobportal.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sakii.jobportal.entity.JobPostActivity;
import com.sakii.jobportal.entity.JobSeekerApply;
import com.sakii.jobportal.entity.JobSeekerProfile;
import com.sakii.jobportal.entity.JobSeekerSave;
import com.sakii.jobportal.entity.RecruiterProfile;
import com.sakii.jobportal.entity.Users;
import com.sakii.jobportal.services.JobPostActivityService;
import com.sakii.jobportal.services.JobSeekerApplyService;
import com.sakii.jobportal.services.JobSeekerProfileService;
import com.sakii.jobportal.services.JobSeekerSaveService;
import com.sakii.jobportal.services.RecruiterProfileService;
import com.sakii.jobportal.services.UsersService;

@Controller
public class JobSeekerApplyController {
  private final JobPostActivityService jobPostActivityService;
  private final UsersService usersService;
  private final JobSeekerApplyService jobSeekerApplyService;
  private final JobSeekerSaveService jobSeekerSaveService;
  private final RecruiterProfileService recruiterProfileService;
  private final JobSeekerProfileService jobSeekerProfileService;

  @Autowired
  public JobSeekerApplyController(JobPostActivityService jobPostActivityService, UsersService usersService,
      JobSeekerApplyService jobSeekerApplyService, JobSeekerSaveService jobSeekerSaveService,
      RecruiterProfileService recruiterProfileService, JobSeekerProfileService jobSeekerProfileService) {
    this.jobPostActivityService = jobPostActivityService;
    this.usersService = usersService;
    this.jobSeekerApplyService = jobSeekerApplyService;
    this.jobSeekerSaveService = jobSeekerSaveService;
    this.jobSeekerProfileService = jobSeekerProfileService;
    this.recruiterProfileService = recruiterProfileService;
  }

  @GetMapping("job-details-apply/{id}")
  public String display(@PathVariable("id") int id, Model model) {
    JobPostActivity jobDetails = jobPostActivityService.getOne(id);
    List<JobSeekerApply> jobSeekerApplyList = jobSeekerApplyService.getJobCandidates(jobDetails);
    List<JobSeekerSave> jobSeekerSaveList = jobSeekerSaveService.getJobCandidates(jobDetails);

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))) {
        RecruiterProfile user = recruiterProfileService.getCurrentRecruiterProfile();
        if (user != null) {
          model.addAttribute("applyList", jobSeekerApplyList);
        }
      } else {
        JobSeekerProfile user = jobSeekerProfileService.getCurrentSeekerProfile();
        if (user != null) {
          boolean exists = false;
          boolean saved = false;
          for (JobSeekerApply jobSeekerApply : jobSeekerApplyList) {
            if (jobSeekerApply.getUserId().getUserAccountId() == user.getUserAccountId()) {
              exists = true;
              break;
            }
          }
          for (JobSeekerSave jobSeekerSave : jobSeekerSaveList) {
            if (jobSeekerSave.getUserId().getUserAccountId() == user.getUserAccountId()) {
              saved = true;
              break;
            }
          }
          model.addAttribute("alreadyApplied", exists);
          model.addAttribute("alreadySaved", saved);
        }
      }
    }
    JobSeekerApply jobSeekerApply = new JobSeekerApply();
    model.addAttribute("applyJob", jobSeekerApply);

    model.addAttribute("jobDetails", jobDetails);
    model.addAttribute("user", usersService.getCurrentUserProfile());
    return "job-details";
  }

  @PostMapping("job-details/apply/{id}")
  public String apply(@PathVariable("id") int id, JobSeekerApply jobSeekerApply) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      String currentUsername = authentication.getName();
      Users user = usersService.findByEmail(currentUsername);
      Optional<JobSeekerProfile> seekerProfile = jobSeekerProfileService.getOne(user.getUserId());
      JobPostActivity jobPostActivity = jobPostActivityService.getOne(id);
      if (seekerProfile.isPresent() && jobPostActivity != null) {
        jobSeekerApply = new JobSeekerApply();
        jobSeekerApply.setUserId(seekerProfile.get());
        jobSeekerApply.setJob(jobPostActivity);
        jobSeekerApply.setApplyDate(new Date());
      } else {
        throw new RuntimeException("User not found");
      }
      jobSeekerApplyService.addNew(jobSeekerApply);
    }
    return "redirect:/dashboard/";
  }

}