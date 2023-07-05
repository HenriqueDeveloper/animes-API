package br.com.henrique.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.com.henrique.dtos.CredentialsDTO;
import br.com.henrique.dtos.TokenDTO;
import br.com.henrique.entities.UserAuthenticate;
import br.com.henrique.exceptions.InvalidPasswordException;
import br.com.henrique.security.jwt.JwtService;
import br.com.henrique.services.UserServiceImple;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Usuario")
public class UserController {

    @Autowired
    private UserServiceImple userServiceImple;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
     public UserAuthenticate save(@RequestBody @Valid UserAuthenticate user){
        String passwordEncrypted = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncrypted);
        return userServiceImple.save(user);

     }
     @PostMapping("/auth")
     public TokenDTO authenticate(@RequestBody CredentialsDTO credentials){
    	 try {
             UserAuthenticate userAuthenticate = UserAuthenticate.builder()
                     .login(credentials.getLogin())
                     .password(credentials.getPassword()).build();
             this.userServiceImple.autentificar(userAuthenticate);
             String token = jwtService.generateToken(userAuthenticate);
             return new TokenDTO(token);
         }catch (UsernameNotFoundException  | InvalidPasswordException e){
             throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
         }
      }
}
