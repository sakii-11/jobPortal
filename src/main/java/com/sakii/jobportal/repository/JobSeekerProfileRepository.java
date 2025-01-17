package com.sakii.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakii.jobportal.entity.JobSeekerProfile;

public interface JobSeekerProfileRepository extends JpaRepository<JobSeekerProfile, Integer> {

}
