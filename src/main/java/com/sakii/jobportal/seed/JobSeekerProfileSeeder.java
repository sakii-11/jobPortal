// package com.sakii.jobportal.seed;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
// import org.springframework.transaction.annotation.Transactional;

// import com.github.javafaker.Faker;
// import com.sakii.jobportal.entity.JobSeekerProfile;
// import com.sakii.jobportal.entity.Users;
// import com.sakii.jobportal.repository.JobSeekerProfileRepository;
// import com.sakii.jobportal.repository.UsersRepository;

// @Component
// public class JobSeekerProfileSeeder implements CommandLineRunner {

//   @Autowired
//   private JobSeekerProfileRepository jobSeekerProfileRepository;

//   @Autowired
//   private UsersRepository usersRepository;

//   @Autowired
//   private Faker faker; 
//   @Override
//   @Transactional 
//   public void run(String... args) {
//     List<JobSeekerProfile> jobSeekerProfiles = generateJobSeekerProfiles();
//     jobSeekerProfileRepository.saveAll(jobSeekerProfiles);
//     System.out.println("Seeding completed! Saved " + jobSeekerProfiles.size() + " job seeker profiles.");
//   }

//   private List<JobSeekerProfile> generateJobSeekerProfiles() {
//     List<Users> jobSeekers = usersRepository.findAll().stream()
//         .filter(user -> user.getUserTypeId().getUserTypeId() == 2) 
//         .collect(Collectors.toList());


//     return jobSeekers.stream()
//         .map(this::generateProfileForJobSeeker)
//         .collect(Collectors.toList());
//   }

//   private JobSeekerProfile generateProfileForJobSeeker(Users jobSeeker) {

//     jobSeeker = usersRepository.findById(jobSeeker.getUserId())
//         .orElseThrow(() -> new RuntimeException("Job Seeker not found"));


//     JobSeekerProfile profile = new JobSeekerProfile(jobSeeker);
//     profile.setFirstName(faker.name().firstName());
//     profile.setLastName(faker.name().lastName());
//     profile.setCity(faker.address().city());
//     profile.setState(faker.address().state());
//     profile.setCountry(faker.address().country());
//     profile.setWorkAuthorization(faker.company().industry());
//     profile.setEmploymentType(faker.job().title());
//     profile.setResume(faker.lorem().paragraph());
//     profile.setProfilePhoto(faker.internet().avatar());

//     return profile;
//   }
// }
