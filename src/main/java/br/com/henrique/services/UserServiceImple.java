package br.com.henrique.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.henrique.entities.UserAuthenticate;
import br.com.henrique.exceptions.InvalidPasswordException;
import br.com.henrique.repositories.UserRepository;

@Service
public class UserServiceImple implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserAuthenticate save(UserAuthenticate user){

        return userRepository.save(user);
    }

    public UserDetails autentificar(UserAuthenticate user){
        UserDetails userDetails = loadUserByUsername(user.getLogin());
        boolean passwordMatch = encoder.matches( user.getPassword(), userDetails.getPassword() );

        if(passwordMatch){
            return userDetails;
        }

        throw new InvalidPasswordException("Senha inválida!");
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthenticate user = this.userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
        String[] roles = user.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};
        return User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }
}