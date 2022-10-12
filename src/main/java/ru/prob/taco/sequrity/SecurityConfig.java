package ru.prob.taco.sequrity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;;
import org.springframework.security.web.SecurityFilterChain;
import ru.prob.taco.data.UserRepository;
import ru.prob.taco.model.UserU;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            UserU userU = userRepo.findByUsername(username);
            if (userU != null) {
                return userU;
            }
            throw new UsernameNotFoundException(" 00000000000000 -> User '" + username + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
               .authorizeRequests()
               .antMatchers(HttpMethod.POST, "/api/ingredients")
               .hasAuthority("SCOPE_writeIngredients")
               .antMatchers(HttpMethod.DELETE, "/api//ingredients")
               .hasAuthority("SCOPE_deleteIngredients")
               .antMatchers("/api//tacos", "/api//orders/**")
               .permitAll()
               .antMatchers("/**").access("permitAll")
               .and()
               .oauth2ResourceServer(oauth2 -> oauth2.jwt())
               .httpBasic()
               .realmName("Taco Cloud")
               .and()
               .logout()
               .logoutSuccessUrl("/")
               .and()
               .logout()
               .and()
               .build();
   }
/*
curl localhost:8080/ingredients -H"Content-type: application/json" -d'{"id":"CRKT", "name":"Legless Crickets", "type":"PROTEIN"}'

curl localhost:8080/api/ingredients -H"Content-type: application/json" -d'{"id":"SHMP", "name":"Coconut Shrimp", "type":"PROTEIN"}' -H"Authorization: Bearer eyJraWQiOiJiYjBiNjBlZC05YzE2LTQ5MjItOWNiNi00NjJkYWVlZTYwYzQiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJxIiwiYXVkIjoidGFjby1hZG1pbi1jbGllbnQiLCJuYmYiOjE2NjU1NzAwMTcsInNjb3BlIjpbImRlbGV0ZUluZ3JlZGllbnRzIiwid3JpdGVJbmdyZWRpZW50cyJdLCJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3Q6OTAwMCIsImV4cCI6MTY2NTU3MDMxNywiaWF0IjoxNjY1NTcwMDE3fQ.gkScKwiVYRNw-Zq0acRVpNbf7g5HRk39B2XQa-MZ75Hkg4R508wlTq_Ae7ciZs_cgZORXpNSN7SjVc7-DUOAJDn4HTLUxuXfm_0vg3VPnmylrUm310uvatBmUZodHyg8_8GD7EY-uW6HqaDU-vEFfNQFPZHtnwCiQCgQpqbjzE0QRAn-iuc62Jmiop9CRGFTgfgO4UdkCaE-Gbc9oDCIOoR2-5h2oCD-5CAk5RkH9GdJz2Yftgx7e29d25AOheqpMsR-APF4RpFbMucNv6FsihZdBoQ2zzqsyiGp7282GmkvuI2m91d6yE5Zi_JqtatEnZsG3ESyCU1grswZJcHdrg"

*/

}
