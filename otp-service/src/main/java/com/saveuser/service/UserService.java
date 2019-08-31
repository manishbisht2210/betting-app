package com.saveuser.service;

import com.saveuser.entity.User;
import com.saveuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(String mobileNo) {
        User user = buildUser(mobileNo);
        return userRepository.save(user);
    }

    private User buildUser(String mobileNo) {
        User user = new User();
        user.setDob("04/03/1980");
        user.setEmail(generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 12));
        user.setFullName("ABC");
        user.setPassword("{bcrypt}$2a$04$EZzbSqieYfe/nFWfBWt2KeCdyq0UuDEM1ycFF8HzmlVR6sbsOnw7u");
        user.setUsername(mobileNo);
        return user;
    }

    private String generateRandomChars(String candidateChars, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars
                    .length())));
        }
        return sb.toString();
    }
}
