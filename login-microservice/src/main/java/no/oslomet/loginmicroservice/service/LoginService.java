package no.oslomet.loginmicroservice.service;


import no.oslomet.loginmicroservice.model.User;
import no.oslomet.loginmicroservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Test email: " + email );
        Optional<User> user = userRepository.findUserByEmail(email);
        if(!user.isPresent()) throw new UsernameNotFoundException("Not found user with email: " + email);
        return getUserDetails(user.get());
    }

    private UserDetails getUserDetails(User user){
        System.out.println("Test: inside getUserDetails");
        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles()).build();
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public void deleteUserById(long id){
        userRepository.deleteById(id);
    }

    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

}
