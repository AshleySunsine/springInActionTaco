package ru.prob.taco.sequrity;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.prob.taco.model.UserU;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public UserU toUser(PasswordEncoder passwordEncoder) {
        return new UserU(
                username, passwordEncoder.encode(password),
                fullname, street, city,
                state, zip, phone
        );
    }
}
