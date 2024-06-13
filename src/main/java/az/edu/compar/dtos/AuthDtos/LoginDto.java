package az.edu.compar.dtos.AuthDtos;


import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
