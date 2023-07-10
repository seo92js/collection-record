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
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 모든 요청 URL이 스프링 시큐리티의 제어를 받도록
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
                //.csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/login",
                        "/user/join",
                        "api/v1/**",
                        "/chatroom",
                        "/notification",
                        "/api/v1/**",
                        "/js/**",
                        "/img/**",
                        "/css/**",
                        "/templates/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        // authorizeRequest는 BCryptPasswordEncoder()는 패스워드를 암호화 할 떄 사용이 되는데, 객체를 직접 생성하는 방식보다는 빈으로 생성하여 사용하는 것이 유지보수에 좋다.
        return new BCryptPasswordEncoder();
    }
}