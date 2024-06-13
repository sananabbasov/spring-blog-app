package az.edu.compar.services.impls;



import az.edu.compar.payloads.EmailSendConsumer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import az.edu.compar.dtos.UserDtos.UserDto;
import az.edu.compar.dtos.UserDtos.UserRegisterDto;
import az.edu.compar.entities.Role;
import az.edu.compar.entities.User;
import az.edu.compar.payloads.ApiResponse;
import az.edu.compar.repositories.UserRepository;
import az.edu.compar.services.EmailService;
import az.edu.compar.services.UserService;
import org.hibernate.id.GUIDGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ApiResponse register(UserRegisterDto registerDto) throws JsonProcessingException {

        Optional<User> findUser = userRepository.findByEmail(registerDto.getEmail());
        if (!findUser.isEmpty())
            return new ApiResponse("User already exist",false);

        User newUser = modelMapper.map(registerDto, User.class);
        String encodedPassword = passwordEncoder.encode(registerDto.getPassword());
        newUser.setPassword(encodedPassword);
        SecureRandom random = new SecureRandom();
        String token = new BigInteger(500, random).toString(32);
        newUser.setEmailConfirmed(false);
        newUser.setConfirmationToken(token);
        Date test = new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 100);
        newUser.setConfirmationTokenExpired(test);


        String message = "http://localhost:9090/api/auth/confirmEmail?email="+registerDto.getEmail()+"&token="+token;
        EmailSendConsumer consumer = new EmailSendConsumer("hannah.kessler18@ethereal.email","Confirmation",message);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(consumer);


        userRepository.save(newUser);
        rabbitTemplate.convertAndSend("","email-send-consumer",json);



        return new ApiResponse("Register is successfully",true);
    }

    @Override
    public ApiResponse confirmEmail(String email, String token) {
        User findUser = userRepository.findByEmail(email).orElseThrow();
        if (findUser.getConfirmationToken().equals(token)  && findUser.getConfirmationTokenExpired().after(new Date())){
            findUser.setEmailConfirmed(true);
            userRepository.save(findUser);
            return new ApiResponse("Email is confirmed successfully",true);
        }
        return new ApiResponse("Something is wrong",false);
    }

    @Override
    public List<String> userRoles(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
       List<String> roles = user.getRoles().stream().map(x->x.getName()).collect(Collectors.toList());
        return roles;
    }


}
