package com.alkemy.disney.disneyApiRest.auth.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Getter
@Setter
public class UserDTO  {

    @Email(message = "Enter a valid email")
    private String username;

    @Size(min = 5)
    private String password;
}
