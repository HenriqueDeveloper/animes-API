package br.com.henrique.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.henrique.security.jwt.JwtAuthFilter;

@Configuration
public class SecurityConfig {
	
    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter();
    }
    
	@Bean
	public PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authorize -> authorize
        .requestMatchers("/api/anime/**")
        .hasAnyRole("USER", "ADMIN")
        .requestMatchers(HttpMethod.POST,"/api/users/**")
        .permitAll()
        .anyRequest().authenticated())
        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	    @Bean
	    public WebSecurityCustomizer webSecurityCustomizer() {
	         return (web) -> web.ignoring()
                .requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html/**",
                        "/v2/api-docs/**",
                        "/swagger-resources/**"
                );
    }
}
