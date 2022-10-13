package ru.prob.taco.sequrity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
                .antMatchers("/data-api/tacos", "/data-api/orders/**")
                .permitAll()
                .antMatchers("/**").access("permitAll")
               .antMatchers(HttpMethod.POST, "/data-api/ingredients")
               .hasAuthority("SCOPE_writeIngredients")
               .antMatchers(HttpMethod.DELETE, "/data-api//ingredients")
               .hasAuthority("SCOPE_deleteIngredients")
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

add:
curl localhost:8080/data-api/ingredients -H"Content-type: application/json" -d'{"id":"SHMP", "name":"Coconut Shrimp", "type":"PROTEIN"}' -H"Authorization: Bearer eyJraWQiOiJiYjBiNjBlZC05YzE2LTQ5MjItOWNiNi00NjJkYWVlZTYwYzQiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJxIiwiYXVkIjoidGFjby1hZG1pbi1jbGllbnQiLCJuYmYiOjE2NjU1NzE2OTgsInNjb3BlIjpbImRlbGV0ZUluZ3JlZGllbnRzIiwid3JpdGVJbmdyZWRpZW50cyJdLCJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3Q6OTAwMCIsImV4cCI6MTY2NTU3MTk5OCwiaWF0IjoxNjY1NTcxNjk4fQ.I2otXTpYzvgi3O5FSBb3TJCAko5LSVRaj9f664nXyITc7BPMyBu4AFuL3G1oywlavWUUrKoGnbESNCVqVtE9HmSD9-QSex2SOzOfIob83akG4bybC59FirTisE6_0gEVFdUIVoLQkdir9_-QLyHEx2b1CW2q_8VfIRCZzqJjLSKbEpYlkNwsYRxaunOEWQwYG9OdeXTNnWBhl9G-QNctwEwsEObA_O-ssw1F7bMDgJTQ5-Ai_sGRil68AJ3upAaBgSdy4DCijvqut9wiMBvyCzvgt0FcP5uaA14WrETD-BHtfnG2zxeRP-L15DhzogEHmDqrDjqXvKyMqQuoPoG-Kw"

refrash:
curl localhost:9000/oauth2/token -H"Content-type: application/x-www-form-urlencoded" -d"grant_type=refresh_token&refresh_token=$refreshToken" -u taco-admin-client:secret
*/

}
