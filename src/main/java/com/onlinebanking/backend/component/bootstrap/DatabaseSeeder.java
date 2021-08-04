package com.onlinebanking.backend.component.bootstrap;

import com.onlinebanking.backend.persistent.domain.Role;
import com.onlinebanking.backend.persistent.repository.RoleRepository;
import com.onlinebanking.backend.service.UserService;
import com.onlinebanking.enums.RoleType;
import com.onlinebanking.shared.util.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

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

    private final RoleRepository roleRepository;
    private final UserService userService;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;


    @Override
    public void run(String... args) throws Exception {
        var adminEmail = "admin@gmail.com";
        var admin = UserUtils.createUser(adminUsername, adminPassword, adminEmail, true);
        var adminDto = UserUtils.convertToUserDto(admin);

        Arrays.stream(RoleType.values()).forEach(roleTypeValue -> roleRepository.save(new Role(roleTypeValue)));
        Set<RoleType> adminRoleType = Collections.singleton(RoleType.ROLE_ADMIN);
        userService.createUser(adminDto, adminRoleType);

    }
}
