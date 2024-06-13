package az.edu.compar.controllers;


import az.edu.compar.dtos.AuthDtos.LoginDto;
import az.edu.compar.dtos.UserDtos.UserRegisterDto;
import az.edu.compar.exceptions.ApiException;
import az.edu.compar.payloads.ApiResponse;
import az.edu.compar.payloads.JwtAuthResponse;
import az.edu.compar.security.JwtTokenHelper;
import az.edu.compar.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private JwtTokenHelper tokenHelper;



    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) throws Exception {
        // User is found
        authenticate(loginDto.getEmail(),loginDto.getPassword());
        UserDetails user = userDetailsService.loadUserByUsername(loginDto.getEmail());
        String token = tokenHelper.generateToken(user);
        JwtAuthResponse result = new JwtAuthResponse();
        result.setToken(token);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/confirmEmail")
    public ResponseEntity<ApiResponse> confirmEmail(@RequestParam String email, String token){
        ApiResponse result = userService.confirmEmail(email,token);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody UserRegisterDto registerDto) throws JsonProcessingException {

        ApiResponse result = userService.register(registerDto);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }


    private void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);

        try {
            authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException e){
            System.out.println("Invalid credentials.");
            throw new ApiException("Invalid username or password.");
        }

    }

}
