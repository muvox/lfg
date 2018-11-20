package com.a1704471.lookingforgamer.security;

import com.a1704471.lookingforgamer.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;

import java.text.Normalizer;

import static com.a1704471.lookingforgamer.security.SecurityConstants.SIGN_UP_URL;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    /**
     * FormLogin security Configuration
     */
    @Configuration
    @Order(1)
    public static class FormLoginWebSecurityConfig extends WebSecurityConfigurerAdapter{
        public FormLoginWebSecurityConfig(){
            super();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception{
            http
                    .antMatcher("/web/**")
                    .authorizeRequests()
                    .antMatchers("/web/signup", "/web/sign-up","/web/login","/web/appLogin").permitAll()
                    .and()
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/web/login").permitAll()
                    .loginProcessingUrl("/web/appLogin")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/web/")
                    .and()
                    .logout()
                    .logoutUrl("/web/logout")
                    .logoutSuccessUrl("/web/login")
                    .permitAll();

        }
    }

    /**
     * Api Security config
     */
    @Configuration
    @Order(2)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter{
        public ApiWebSecurityConfigurationAdapter(){
            super();
        }

        @Autowired
        private UserDetailsServiceImpl userDetailsService;
        @Autowired
        private BCryptPasswordEncoder bencoder;


        @Override
        protected void configure(HttpSecurity http) throws Exception{
            http
                    .requestMatchers()
                        .antMatchers("/login","/users/**","/api/**")
                    .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                    .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().csrf().disable();

//            http
//                    .antMatcher("/users/**")
//                    .cors()
//                    .and()
//                    .authorizeRequests()
//                    .antMatchers("/web/signup", "/web/sign-up","/web/login","/web/appLogin").permitAll()
//                    .and()
//                    .authorizeRequests()
//                    .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                    .addFilter(new JWTAuthenticationFilter(authenticationManager()))
//                    .addFilter(new JWTAuthorizationFilter(authenticationManager()))
//                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                    .and() // Make sure to use .and() to add the .csrf()
//                    .csrf().disable();
        }


        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(bencoder);
        }
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}
