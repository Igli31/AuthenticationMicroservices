package com.example.student_service.Service.Security;

import com.example.student_service.Entity.Role;
import com.example.student_service.Entity.User;
import com.example.student_service.Enum.RoleEnum;
import com.example.student_service.Repository.RoleRepository;
import com.example.student_service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleEnum.ADMIN.toString())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(Collections.singletonList(userRole));

        return userRepository.save(user);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    /*What's happening here:
            RoleEnum.ADMIN.toString(): This converts the enum value ADMIN to the string "ADMIN".

            roleRepository.findByName(...): This method looks up the Role entity in the database where the name field matches "ADMIN".

            user.setRoles(...): The found Role is then assigned to the user's roles list.

    In summary:
    Class: UserService

    Method: registerUser(User user)

    Mapping: RoleEnum.ADMIN to the Role entity via roleRepository.findByName().*/
}
