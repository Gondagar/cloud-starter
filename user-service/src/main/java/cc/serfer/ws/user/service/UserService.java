package cc.serfer.ws.user.service;

import cc.serfer.ws.user.model.UserEntity;
import cc.serfer.ws.user.repository.UserRepository;
import cc.serfer.ws.user.security.UserDetailsIml;
import cc.serfer.ws.user.transfer.AlbumResponseModel;
import cc.serfer.ws.user.transfer.CreateUserRequestModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
@Slf4j
public class UserService implements UserDetailsService {

   /* @Autowired
    AlbumServiceClient albumServiceClient;*/


    @Autowired
    UserRepository userRepository;


    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    RestTemplate restTemplate;

    @Value("${albums.url}")
    String albumUrlRaw;

    public UserEntity createUser(CreateUserRequestModel userDto) {

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

    public UserEntity getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));


        return userEntity;
    }



    public UserEntity getUserByUserId(String userId, String clientType) {
        UserEntity userEntity = userRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException(userId));


        if ("rest".equals(clientType)) {
            log.info("Used rest template client get info");
            try {
                String albumUrl = String.format(albumUrlRaw, userId);
                ResponseEntity<List<AlbumResponseModel>> albumsListResponse = restTemplate.exchange(albumUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<AlbumResponseModel>>() {
                });
                List<AlbumResponseModel> albumsList = albumsListResponse.getBody();
                userEntity.setAlbums(albumsList);
            } catch (Exception e) {
                log.error(e.getLocalizedMessage());

            }


        } /*else if ("feign".equals(clientType)) {
            log.info("Used feign client");
            try {
                log.info("Before calling albums Microservice");
                List<AlbumResponseModel> albums = albumServiceClient.getAlbums(userId);
                log.info("After calling albums Microservice");
                userEntity.setAlbums(albums);

            } catch (Exception e) {
                log.error(e.getLocalizedMessage());
            }
        }*/

        return userEntity;
    }

}
