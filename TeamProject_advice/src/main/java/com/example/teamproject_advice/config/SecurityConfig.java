package com.example.teamproject_advice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;      // application.properfile 의 srping.datasource를 사용할 수 있게 함

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 로그인 페이지 정의
        http.formLogin()
                .loginPage("/user/login").permitAll()               // 권한 없는 페이지를 조회하면 자동으로 이곳에
                .and()
                .logout().permitAll()

        .and().authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/css/**", "/js/**", "/layout/**", "/img/**").permitAll()
                .antMatchers("/").permitAll()               // 홈 경로 모두 공개
                .antMatchers("/user/login", "/user/register", "/board/list", "/board/detail").permitAll()     // 로그인 페이지 모두 공개
                .anyRequest().authenticated()

                .and().csrf().disable();                            // 토큰 비활성화 403때문에
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select account, password, enabled " +
                        "from user " +
                        "where account = ?")
                .authoritiesByUsernameQuery("select account, name " +
                        "from user_role ur inner join user u on ur.user_id = u.id " +
                        "inner join role r on ur.role_id = r.id " +
                        "where u.account = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
