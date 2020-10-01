package com.example.simple_project_in_spring.config;

import com.example.simple_project_in_spring.repository.UserRepository;
import com.example.simple_project_in_spring.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsServiceImpl myUserDetailsService;
    private UserRepository userRepository;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl myUserDetailsService, UserRepository userRepository) {
        this.myUserDetailsService = myUserDetailsService;
        this.userRepository = userRepository;
    }

    @Bean
    PasswordEncoder myPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        CustomAuthenticationProvider customAuth = new CustomAuthenticationProvider(userRepository);
        customAuth.setUserDetailsService(myUserDetailsService);
        customAuth.setPasswordEncoder(myPasswordEncoder());
        auth.authenticationProvider(customAuth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/find/**", "/find*").hasAuthority("USER_FIND_PRIVILEGE")
                .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .headers().frameOptions().disable(); // this is needed to access the H2 db's console, turn off on production
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        CustomEntryPoint entryPoint =
                new CustomEntryPoint();
        entryPoint.setRealmName("User Client Rest Api");
        return entryPoint;
    }
}
