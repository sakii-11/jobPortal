// package com.sakii.jobportal.seed;

// import com.github.javafaker.Faker;
// import com.sakii.jobportal.entity.Users;
// import com.sakii.jobportal.entity.UsersType;
// import com.sakii.jobportal.repository.UsersRepository;
// import com.sakii.jobportal.repository.UsersTypeRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
// import java.util.List;
// import java.util.stream.Collectors;
// import java.util.stream.IntStream;

// import org.springframework.transaction.annotation.Transactional;

// @Component
// public class UserSeeder implements CommandLineRunner {

//     @Autowired
//     private UsersRepository usersRepository;

//     @Autowired
//     private UsersTypeRepository usersTypeRepository;

//     @Autowired
//     private Faker faker;

//     @Override
//     @Transactional  
//     public void run(String... args) {
//         seedUsers();
//     }

//     private void seedUsers() {
//         UsersType recruiterType = usersTypeRepository.findById(1)
//                 .orElseThrow(() -> new RuntimeException("UserType 1 not found"));
//         UsersType jobSeekerType = usersTypeRepository.findById(2)
//                 .orElseThrow(() -> new RuntimeException("UserType 2 not found"));

//         List<Users> users = IntStream.range(0, 10)
//                 .mapToObj(i -> createFakeUser(recruiterType))
//                 .collect(Collectors.toList());
//         users.addAll(IntStream.range(0, 10)
//                 .mapToObj(i -> createFakeUser(jobSeekerType))
//                 .collect(Collectors.toList()));

//         usersRepository.saveAll(users);
//         System.out.println("✅ Seeding completed! Created " + users.size() + " users.");
//     }

//     private Users createFakeUser(UsersType userType) {
//         Users user = new Users();
//         user.setEmail(faker.internet().emailAddress());
//         user.setPassword("mebeforeyou");
//         user.setActive(faker.bool().bool());
//         user.setUserTypeId(userType);  // Assign the managed UsersType

//         return user;
//     }
//   }