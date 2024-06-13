package az.edu.compar.services;

import az.edu.compar.dtos.UserDtos.UserDto;
import az.edu.compar.dtos.UserDtos.UserRegisterDto;
import az.edu.compar.payloads.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {

    ApiResponse register(UserRegisterDto registerDto) throws JsonProcessingException;
    ApiResponse confirmEmail(String email, String token);
    List<String> userRoles(String email);

}
