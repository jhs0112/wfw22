package kr.ac.hansung.cse.hellospringdatajpa.controller;

import kr.ac.hansung.cse.hellospringdatajpa.entity.Role;
import kr.ac.hansung.cse.hellospringdatajpa.entity.User;
import kr.ac.hansung.cse.hellospringdatajpa.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute("user") User user, Model model) {

        if (registrationService.checkEmailExists(user.getEmail())) {
            model.addAttribute("emailExists", true);
            return "signup";
        }
        else {
            List<Role> userRoles = new ArrayList<>();

            // 특정 이메일 주소인 경우 ADMIN 역할 추가
            if ("admin@hansung.ac.kr".equals(user.getEmail())) {
                Role roleAdmin = registrationService.findRoleByName("ROLE_ADMIN");
                userRoles.add(roleAdmin);
                registrationService.createUser(user, userRoles);
                return "redirect:/";
            }

            Role role = registrationService.findRoleByName("ROLE_USER");
            userRoles.add(role);

            registrationService.createUser(user, userRoles);

            return "redirect:/";
        }
    }
}