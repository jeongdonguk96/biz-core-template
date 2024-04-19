package io.nexgrid.bizcoretemplate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        // security 해시 암호화


        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http // 특정요청에 대한 config (순서에 유의)
            .authorizeHttpRequests((auth) -> auth                    // boot 3.1.x ~ 부터 람다형식 필수
                .requestMatchers("/", "/login","/loginRequest","/signup","/signupRequest","/list").permitAll() // 모든 접근 허용
                .requestMatchers("/admin").hasRole("ROOT")  // 특정권한을 가진자
                .requestMatchers("/my/**").hasAnyRole("ROOT", "NORMAL")
                .anyRequest().authenticated() // 지정한 요청 외의 나머지 모든 요청은 인증된 사람만
            );

        http // 권한이없는 유저인 경우 redirection
            .formLogin((auth) -> auth.loginPage("/login")
                .loginProcessingUrl("/loginRequest")
                .defaultSuccessUrl("/")
                .usernameParameter("email") // email로 login id 설정
                .passwordParameter("password")
                .permitAll()
            );

        http // security 자동설정 되어있는 사이트 위변조 방지 설정
            .csrf((auth) -> auth.disable());

        return http.build();
    }
}
