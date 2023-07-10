package side.collectionrecord.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*        http
                .csrf().disable()
                .authorizeRequests()
                //.antMatchers("/css/**", "/js/**", "/images/**", "/template/**").permitAll() // 정적 리소스에 대한 접근 허용
                .antMatchers(
                        "/chatroom",
                        "/notification",
                        "/",
                        "/login",
                        "/user/join",
                        "/api/v1/**"
                ).permitAll()
                .antMatchers(
                        "/js/**",
                        "/img/**",
                        "/css/**",
                        "/templates/**"
                ).permitAll()
                .anyRequest().authenticated() // 모든 요청에 대해 인증 필요
                .and()
                .formLogin()
                .loginPage("/login") // 로그인 페이지 경로
                .defaultSuccessUrl("/") // 로그인 성공 후 이동할 경로
                .and()
                .logout()
                .logoutUrl("/logout") // 로그아웃 경로
                .logoutSuccessUrl("/login") // 로그아웃 후 이동할 경로
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");*/
        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/login",
                        "/user/join",
                        "/js/**",
                        "/img/**",
                        "/css/**",
                        "/templates/**",
                        "/api/v1/**"
                ).permitAll()
                .anyRequest().authenticated();
        http
                .formLogin()
                .loginPage("/login") //login form을 보여 줌
                .loginProcessingUrl("/api/v1/login") //POST 요청
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/"); //성공시

        http
                .logout()
                .logoutUrl("/api/v1/logout")
                .logoutSuccessUrl("/"); //성공시
    }
}
