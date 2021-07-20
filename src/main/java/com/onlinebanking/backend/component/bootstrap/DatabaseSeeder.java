package com.onlinebanking.backend.component.bootstrap;

import com.onlinebanking.backend.persistent.domain.Role;
import com.onlinebanking.backend.persistent.domain.UserRole;
import com.onlinebanking.backend.persistent.repository.RoleRepository;
import com.onlinebanking.backend.persistent.repository.UserRepository;
import com.onlinebanking.enums.RoleType;
import com.onlinebanking.shared.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * A convenient class to initializes and save user data on application start.
 *
 * @author George on 7/19/2021
 * @version 1.0
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;


    @Override
    public void run(String... args) throws Exception {
        var adminEmail = "admin@gmail.com";

        var roleAdmin = new Role(RoleType.ADMIN);
        var roleCustomer = new Role(RoleType.CUSTOMER);
        roleRepository.save(roleCustomer);
        roleRepository.save(roleAdmin);

        // encode plain password
        var encodedPass = passwordEncoder.encode(adminPassword);

        var admin = UserUtils.createUser(adminUsername, encodedPass, adminEmail, true);

        //set connection between user and userRole
        admin.addUserRole(new UserRole(admin, roleCustomer));
        admin.addUserRole(new UserRole(admin, roleAdmin));

        // save user cascades saving userRoles
        userRepository.save(admin);

    }
}
