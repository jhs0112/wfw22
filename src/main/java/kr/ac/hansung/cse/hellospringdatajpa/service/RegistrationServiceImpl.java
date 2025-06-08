package kr.ac.hansung.cse.hellospringdatajpa.service;


import kr.ac.hansung.cse.hellospringdatajpa.entity.Role;
import kr.ac.hansung.cse.hellospringdatajpa.entity.User;
import kr.ac.hansung.cse.hellospringdatajpa.repo.RoleRepository;
import kr.ac.hansung.cse.hellospringdatajpa.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user, List<Role> userRoles) {
        for (Role role : userRoles) {
            if(roleRepository.findByRolename(role.getRolename()).isEmpty()) {
                roleRepository.save(role);
            }
        }
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setRoles(userRoles);
        User newUser = userRepository.save(user);
        return newUser;
    }

    @Override
    public boolean checkEmailExists(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public Role findRoleByName(String roleName) {
        Optional<Role> role = roleRepository.findByRolename(roleName);
        return role.orElseGet(() -> new Role(roleName));
    }
}
