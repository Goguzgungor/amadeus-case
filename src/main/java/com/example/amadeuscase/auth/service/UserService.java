package com.example.amadeuscase.auth.service;

import com.example.amadeuscase.auth.entity.AmdUser;
import com.example.amadeuscase.auth.model.request.UserCreateDto;
import com.example.amadeuscase.auth.model.request.UserLoginDto;
import com.example.amadeuscase.auth.repository.AmdUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    private final AmdUserRepository userRepository;

    @Autowired
    public UserService(AmdUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private  PasswordEncoder passwordEncoder;





    @Override
    public AmdUser login(UserLoginDto userLoginDto) {
        String encodedPassword = passwordEncoder.encode(userLoginDto.getPassword());
        System.out.println(encodedPassword);
        AmdUser user = this.userRepository.findByEmail(userLoginDto.getEmail());
        if (passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())){
            return user;
        }
        if(user == null){
            throw new RuntimeException("User not found");
        }
        return user;
    }

    @Override
    public void register(UserCreateDto userCreateDto) {
        String encodedPassword = passwordEncoder.encode(userCreateDto.getPassword());
        AmdUser user = new AmdUser(userCreateDto.getEmail(), encodedPassword,userCreateDto.getRole());
        this.userRepository.save(user);
    }
}
