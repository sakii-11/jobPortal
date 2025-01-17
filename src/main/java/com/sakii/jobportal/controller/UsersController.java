package com.sakii.jobportal.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.sakii.jobportal.entity.Users;
import com.sakii.jobportal.entity.UsersType;
import com.sakii.jobportal.services.UsersService;
import com.sakii.jobportal.services.UsersTypeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class UsersController {

  private final UsersTypeService usersTypeService;
  private final UsersService usersService;

  @Autowired
  public UsersController(UsersTypeService usersTypeService, UsersService usersService){
    this.usersTypeService = usersTypeService;
    this.usersService = usersService;
  }

  @GetMapping("/register")
  public String register(org.springframework.ui.Model model){
    List<UsersType> usersTypes = usersTypeService.getAll();
    model.addAttribute("getAllTypes", usersTypes);
    model.addAttribute("user", new Users());
    return "register";
  }

  @PostMapping("/register/new")
  public String userRegistration(@Valid Users users, Model model){
    // System.out.println("User:" + users);
    Optional<Users> optionalUsers = usersService.getUserByEmail(users.getEmail());
    if(optionalUsers.isPresent()){
      model.addAttribute("error", "Email already registered, try to login or register with another email.");
      List<UsersType> usersTypes = usersTypeService.getAll();
      model.addAttribute("getAllTypes", usersTypes);
      model.addAttribute("user", new Users());
      return "register";
    }
    usersService.addNew(users);
    return "redirect:/dashboard/";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/logout")
  public String logout(HttpServletRequest request , HttpServletResponse response) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if(authentication != null) {
      new SecurityContextLogoutHandler().logout(request, response, authentication);
    }
    return "redirect:/";
    }
}