package az.edu.compar.dtos.UserDtos;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {
    private String firstName;
    private String lastName;
    @Email(message = "Enter correct email address")
    private String email;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    private String passwordReaped;
    private String photoUrl;
    private String about;
}
