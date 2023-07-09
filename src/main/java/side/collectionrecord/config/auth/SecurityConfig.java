package side.collectionrecord.config.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //.antMatchers("/css/**", "/js/**", "/images/**", "/template/**").permitAll() // 정적 리소스에 대한 접근 허용
                .antMatchers("/resources/**").permitAll() // 정적 리소스에 대한 접근 허용
                .antMatchers("/login").permitAll() // 로그인 페이지 접근 허용
                .antMatchers("/").permitAll() // 로그인 페이지 접근 허용
                .antMatchers("/user/join").permitAll() // 로그인 페이지 접근 허용
                .antMatchers("/api/v1/user-join").permitAll()
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
                .deleteCookies("JSESSIONID");
    }

/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> userRepository.findByUsername(username));
    }*/
}
