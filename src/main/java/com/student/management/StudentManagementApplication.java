package com.student.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@SpringBootApplication
public class StudentManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
    }

    @Configuration
    @EnableWebSecurity
    class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            PasswordEncoder passwordEncoder = passwordEncoder();
            String encodedPWD = passwordEncoder.encode("1234");
            System.out.println(encodedPWD);
            auth.inMemoryAuthentication().withUser("user").password(encodedPWD).roles("USER");
            auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN");

        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.formLogin();
            http.authorizeRequests().antMatchers("/").permitAll();
            http.authorizeRequests().antMatchers("/delete/**", "/edit/**", "/formPatient/**").hasRole("ADMIN");
            http.authorizeRequests().antMatchers("/index/**").hasRole("USER");
            http.authorizeRequests().anyRequest().authenticated();
            http.exceptionHandling().accessDeniedPage("/403");
        }

        @Bean
        PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }


}
