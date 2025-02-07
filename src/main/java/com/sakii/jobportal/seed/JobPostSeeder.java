// package com.sakii.jobportal.seed;

// import java.util.Date;
// import java.util.List;
// import java.util.stream.Collectors;
// import java.util.stream.IntStream;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
// import org.springframework.transaction.annotation.Transactional;

// import com.github.javafaker.Faker;
// import com.sakii.jobportal.entity.JobLocation;
// import com.sakii.jobportal.entity.JobCompany;
// import com.sakii.jobportal.entity.JobPostActivity;
// import com.sakii.jobportal.entity.Users;
// import com.sakii.jobportal.repository.JobPostActivityRepository;
// import com.sakii.jobportal.repository.UsersRepository;

// import jakarta.persistence.EntityManager;


// @Component
// public class JobPostSeeder implements CommandLineRunner {

//     @Autowired
//     private EntityManager entityManager; 

//     @Autowired
//     private JobPostActivityRepository jobPostActivityRepository;

//     @Autowired
//     private UsersRepository usersRepository;

//     @Autowired
//     private Faker faker;

//     @Override
//     @Transactional
//     public void run(String... args) {
//         List<JobLocation> jobLocations = generateJobLocations();
//         List<JobCompany> jobCompanies = generateJobCompanies();

//         jobLocations.forEach(entityManager::persist);
//         jobCompanies.forEach(entityManager::persist);

//         List<JobPostActivity> jobPosts = generateJobPosts(jobLocations, jobCompanies);

//         jobPostActivityRepository.saveAll(jobPosts);

//         System.out.println("Seeding completed! Saved " + jobPosts.size() + " job posts.");
//     }

//     private List<JobLocation> generateJobLocations() {
//         // Create job locations using Faker or hardcoded values
//         return IntStream.range(0, 3) // Create 3 different locations
//                 .mapToObj(i -> {
//                     JobLocation location = new JobLocation();
//                     location.setCity(faker.address().city());
//                     location.setState(faker.address().state());
//                     location.setCountry(faker.address().country());
//                     return location;
//                 })
//                 .collect(Collectors.toList());
//     }

//     private List<JobCompany> generateJobCompanies() {
//         // Create job companies using Faker or hardcoded values
//         return IntStream.range(0, 3) // Create 3 different companies
//                 .mapToObj(i -> {
//                     JobCompany company = new JobCompany();
//                     company.setName(faker.company().name());
//                     company.setLogo(faker.internet().avatar());
//                     return company;
//                 })
//                 .collect(Collectors.toList());
//     }

//     private List<JobPostActivity> generateJobPosts(List<JobLocation> jobLocations, List<JobCompany> jobCompanies) {
//         // Get all recruiters (Users with userTypeId == 1)
//         List<Users> recruiters = usersRepository.findAll().stream()
//                 .filter(user -> user.getUserTypeId().getUserTypeId() == 1)
//                 .collect(Collectors.toList());

//         // Define job types and remote options
//         String[] jobTypes = {"Full-time", "Part-time", "Freelance", "Internship"};
//         String[] remoteOptions = {"Remote-Only", "Office-Only", "Partial-Remote"};

//         // Generate job posts for each recruiter
//         return recruiters.stream()
//                 .flatMap(recruiter -> {
//                     return IntStream.range(0, 2) // 2 job posts per recruiter
//                             .mapToObj(i -> {
//                                 JobPostActivity jobPost = new JobPostActivity();

//                                 jobPost.setPostedById(recruiter);

//                                 jobPost.setJobLocationId(jobLocations.get(faker.number().numberBetween(0, jobLocations.size())));
//                                 jobPost.setJobCompanyId(jobCompanies.get(faker.number().numberBetween(0, jobCompanies.size())));

//                                 jobPost.setDescriptionOfJob(faker.lorem().paragraph());
//                                 jobPost.setJobType(jobTypes[faker.number().numberBetween(0, jobTypes.length)]);
//                                 jobPost.setSalary(faker.number().numberBetween(50000, 120000) + " USD");
//                                 jobPost.setRemote(remoteOptions[faker.number().numberBetween(0, remoteOptions.length)]);
//                                 jobPost.setPostedDate(new Date());
//                                 jobPost.setJobTitle(faker.job().title());

//                                 return jobPost;
//                             });
//                 })
//                 .collect(Collectors.toList());
//     }
// }
