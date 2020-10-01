package com.example.simple_project_in_spring.config;

import com.example.simple_project_in_spring.model.Privilege;
import com.example.simple_project_in_spring.model.Role;
import com.example.simple_project_in_spring.model.User;
import com.example.simple_project_in_spring.repository.PrivilegeRepository;
import com.example.simple_project_in_spring.repository.RoleRepository;
import com.example.simple_project_in_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
class DataLoader implements ApplicationRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder,
                      RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        this.userRepository
                = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        Privilege userFindPrivilege = createPrivilegeIfNotFound("USER_FIND_PRIVILEGE");
        Privilege userEditPrivilege = createPrivilegeIfNotFound("USER_EDIT_PRIVILEGE");
        List<Privilege> userPrivileges = new ArrayList<>(Arrays.asList(userFindPrivilege));
        List<Privilege> adminPrivileges = new ArrayList<>(Arrays.asList(userFindPrivilege, userEditPrivilege));
        Role userRole = createRoleIfNotFound("USER_ROLE", userPrivileges);
        Role adminRole = createRoleIfNotFound("ADMIN_ROLE", adminPrivileges);
        List<Role> userRoles = new ArrayList<>(Arrays.asList(userRole));
        List<Role> adminRoles = new ArrayList<>(Arrays.asList(adminRole, userRole));
        User adminUser = createUserIfNotFound("Admin", "Admin", adminRoles, true);
        User commonUser = createUserIfNotFound("User", "User", userRoles, true);
        System.out.println("Test data loaded!");
    }

    @Transactional
    private final User createUserIfNotFound(final String username, final String password, final List<Role> roles, boolean isEnabled) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRoles(roles);
            user = userRepository.save(user);
        }
        return user;
    }

    @Transactional
    private final Privilege createPrivilegeIfNotFound(final String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege();
            privilege.setName(name);
            privilege = privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    private final Role createRoleIfNotFound(final String name, final Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            role.setPrivileges(privileges);
            role = roleRepository.save(role);
        }
        return role;
    }
}