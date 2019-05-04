package no.oslomet.loginmicroservice;

import no.oslomet.loginmicroservice.model.User;
import no.oslomet.loginmicroservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LoginMicroserviceApplication implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder  = new BCryptPasswordEncoder();

    public static void main(String[] args) {
        SpringApplication.run(LoginMicroserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User("Rejwan","Raouf","rejwan@test.no","test","Merchant","Vevelstadåsen",1405,"Oslo");
        User admin1 = new User("Sensor","Sensorsen","sensor@test.no","test","Admin","Vevelstadåsen",1405,"Langhus");


        String hashPassword = bCryptPasswordEncoder.encode(u1.getPassword());
        u1.setPassword(hashPassword);

        admin1.setPassword(bCryptPasswordEncoder.encode(admin1.getPassword()));
        userRepository.save(u1);
        userRepository.save(admin1);


    }
}
