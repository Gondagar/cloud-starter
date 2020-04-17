package cc.serfer.ws.user.controllers;

import cc.serfer.ws.user.model.UserEntity;
import cc.serfer.ws.user.service.UserService;
import cc.serfer.ws.user.transfer.CreateUserRequestModel;
import cc.serfer.ws.user.transfer.CreateUserResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    Environment env;

    @Autowired
    UserService userService;

    @GetMapping("/status/check")
    public String status(){
        return "Working on port " + env.getProperty("local.server.port") + " and my message " + env.getProperty("my.message");
    }



    @GetMapping("/{userId}/{clientType}")
    public ResponseEntity<UserEntity> GetUser(@PathVariable(name = "userId") String id, @PathVariable String clientType){
        UserEntity userByUserId = userService.getUserByUserId(id, clientType);
        return new ResponseEntity<>(userByUserId,HttpStatus.OK);
    }


    @PostMapping(path = "/registration",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public  ResponseEntity createUser(@Valid @RequestBody CreateUserRequestModel  userDetails){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);


        UserEntity user = userService.createUser(userDetails);

        CreateUserResponseModel responseModel = modelMapper.map(user, CreateUserResponseModel.class);

        return new ResponseEntity(responseModel, HttpStatus.CREATED);
    }

}
