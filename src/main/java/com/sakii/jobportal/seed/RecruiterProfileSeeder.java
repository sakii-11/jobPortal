// package com.sakii.jobportal.seed;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
// import org.springframework.transaction.annotation.Transactional;

// import com.github.javafaker.Faker;
// import com.sakii.jobportal.entity.RecruiterProfile;
// import com.sakii.jobportal.entity.Users;
// import com.sakii.jobportal.repository.RecruiterProfileRepository;
// import com.sakii.jobportal.repository.UsersRepository;

// @Component
// public class RecruiterProfileSeeder implements CommandLineRunner {

//   @Autowired
//   private RecruiterProfileRepository recruiterProfileRepository;

//   @Autowired
//   private UsersRepository usersRepository;

//   @Autowired
//   private Faker faker; 

//   @Override
//   @Transactional 
//   public void run(String... args) {
//     List<RecruiterProfile> recruiterProfiles = generateRecruiterProfiles();
//     recruiterProfileRepository.saveAll(recruiterProfiles); // Save profiles
//     System.out.println("Seeding completed! Saved " + recruiterProfiles.size() + " recruiter profiles.");
//   }

//   private List<RecruiterProfile> generateRecruiterProfiles() {
//     List<Users> recruiters = usersRepository.findAll().stream()
//         .filter(user -> user.getUserTypeId().getUserTypeId() == 1) 
//         .collect(Collectors.toList());


//     return recruiters.stream()
//         .map(this::generateProfileForRecruiter)
//         .collect(Collectors.toList());
//   }

//   private RecruiterProfile generateProfileForRecruiter(Users recruiter) {
//     recruiter = usersRepository.findById(recruiter.getUserId())
//         .orElseThrow(() -> new RuntimeException("Recruiter not found"));

//     RecruiterProfile profile = new RecruiterProfile(recruiter);
//     profile.setFirstName(faker.name().firstName());
//     profile.setLastName(faker.name().lastName());
//     profile.setCity(faker.address().city());
//     profile.setState(faker.address().state());
//     profile.setCountry(faker.address().country());
//     profile.setCompany(faker.company().name());
//     profile.setProfilePhoto(faker.internet().avatar());

//     return profile;
//   }
// }
