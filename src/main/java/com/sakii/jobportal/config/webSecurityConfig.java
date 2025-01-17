package com.sakii.jobportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.sakii.jobportal.services.CustomUserDetailsService;

@Configuration
public class webSecurityConfig {
  private final CustomUserDetailsService customUserDetailsService;
  private final CustomAuthenticatonSuccessHandler customAuthenticatonSuccessHandler;

  @Autowired
  public webSecurityConfig(CustomUserDetailsService customUserDetailsService,
      CustomAuthenticatonSuccessHandler customAuthenticatonSuccessHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.customAuthenticatonSuccessHandler = customAuthenticatonSuccessHandler;
    }
  
  private final String[] publicUrl = {"/",
      "/global-search/**",
      "/register",
      "/register/**",
      "/webjars/**",
      "/resources/**",
      "/assets/**",
      "/css/**",
      "/summernote/**",
      "/js/**",
      "/*.css",
      "/*.js",
      "/*.js.map",
      "/fonts**", "/favicon.ico", "/resources/**", "/error"};

  @Bean
  protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.authenticationProvider(authenticationProvider());

    http.authorizeHttpRequests(auth -> {
      auth.requestMatchers(publicUrl).permitAll();
      auth.anyRequest().authenticated();
    });

    http.formLogin(form-> form.loginPage("/login").permitAll().successHandler(customAuthenticatonSuccessHandler)).logout(
      logout -> {
        logout.logoutUrl("/logout");
        logout.logoutSuccessUrl("/");
      }
    ).cors(Customizer.withDefaults()).csrf(csrf->csrf.disable());
  
    return http.build();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    authenticationProvider.setUserDetailsService(customUserDetailsService);
    return authenticationProvider;

  }
 
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
}
