package cc.serfer.ws.user.service;

import cc.serfer.ws.user.model.UserEntity;
import cc.serfer.ws.user.repository.UserRepository;
import cc.serfer.ws.user.security.UserDetailsIml;
import cc.serfer.ws.user.transfer.CreateUserRequestModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service

public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public UserEntity createUser(CreateUserRequestModel userDto){

        UserEntity newUser = UserEntity.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .encryptedPassword(passwordEncoder.encode(userDto.getPassword()))
                .userId(UUID.randomUUID().toString())
                .isActive(true)
                .build();

        //ModelMapper modelMapper = new ModelMapper();



        UserEntity save = userRepository.save(newUser);
        return save;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));

        return new UserDetailsIml(userEntity);
    }

    public UserEntity getUser(String email){
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return userEntity;
    }
}
