package com.interlink.psychological_tests.authentication.registration;

import com.interlink.psychological_tests.authentication.user.User;
import com.interlink.psychological_tests.authentication.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRegistrationService {
    private final UserRegistrationFormValidator userRegistrationFormValidator;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRegistrationService(UserRegistrationFormValidator userRegistrationFormValidator,
                                   UserService userService,
                                   PasswordEncoder passwordEncoder) {
        this.userRegistrationFormValidator = userRegistrationFormValidator;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserRegistrationResult register(UserRegistrationForm userRegistrationForm) {
        UserRegistrationFormValidationResult userRegistrationFormValidationResult = userRegistrationFormValidator.validate(userRegistrationForm);
        if (!userRegistrationFormValidationResult.hasErrors()) {
            userService.saveUser(insertDataToUser(userRegistrationForm));
            return UserRegistrationResult.ok();
        } else {
            return UserRegistrationResult.fail(userRegistrationFormValidationResult.getErrors());
        }
    }

    public User insertDataToUser(UserRegistrationForm userRegistrationForm) {
        User user = new User();
        user.setUsername(userRegistrationForm.getUsername());
        user.setFirstName(userRegistrationForm.getFirstName());
        user.setLastName(userRegistrationForm.getLastName());
        user.setPassword(passwordEncoder.encode(userRegistrationForm.getPassword()));
        user.setGroup(userRegistrationForm.getGroup());
        user.setEmail(userRegistrationForm.getEmail());
        return user;
    }
}