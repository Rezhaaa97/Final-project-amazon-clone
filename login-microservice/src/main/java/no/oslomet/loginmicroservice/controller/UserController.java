package no.oslomet.loginmicroservice.controller;

import no.oslomet.loginmicroservice.model.User;
import no.oslomet.loginmicroservice.repository.UserRepository;
import no.oslomet.loginmicroservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginService loginService;

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
        System.out.println("recieved user: " + user);
      String hashPassword = bCryptPasswordEncoder.encode(user.getPassword());
        String noopPass = "{noop}"+user.getPassword();
        user.setPassword(hashPassword);
        userRepository.save(user);
        return user;
    }

    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable long id,  @RequestBody  User newUser){
        newUser.setId(id);
        return userRepository.save(newUser);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable long id){
        loginService.deleteUserById(id);
    }

    @GetMapping("/overviewOfUsers")
    public List<User> getAllUsers(){
        return loginService.getAllUsers();
    }

    @PutMapping("/changePW/{email}")
    public User updatePWUser(@PathVariable String email,  @RequestBody HashMap<String, Object> body){
       String newPassword = body.get("password").toString();
        System.out.println("newP: " + newPassword);
        User user = loginService.getUserByEmail(email);
        String hashedPassword = bCryptPasswordEncoder.encode(newPassword);
        user.setPassword(hashedPassword);
        return loginService.saveUser(user);
    }




}
