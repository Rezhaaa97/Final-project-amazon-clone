package no.oslomet.loginmicroservice.controller;

import no.oslomet.loginmicroservice.model.User;
import no.oslomet.loginmicroservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;
   private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

   @GetMapping("/")
   public String home(){
       return"this is login microservice.";
   }

    @PostMapping(value = "/login")
    @CrossOrigin
    public User login(Principal principal){
        //User is already authenticated at this point.
        String email = principal.getName();
        System.out.println("Email: " + email);
        System.out.println("user to return: " + userRepository.findUserByEmail(email).get());
        return userRepository.findUserByEmail(email).get();
        //Dummy method to check if username and password is correct.
    }



    @PostMapping("/signup")
    public User register(@RequestBody User user){
      String hashPassword = bCryptPasswordEncoder.encode(user.getPassword());
        String noopPass = "{noop}"+user.getPassword();
        user.setPassword(hashPassword);
        user.setRoles("USER");
        userRepository.save(user);
        return user;
    }




}
