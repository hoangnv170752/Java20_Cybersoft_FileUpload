package com.cybersoft.osahaneat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomConfigSecurity {

    @Autowired
    CustomAuthentication authProvider;

    @Autowired
    CustomFilterJwt customFilterJwt;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    Author : C?? quy???n truy c???p m???t link c??? th??? n??o ???? kh??ng
//    Role : role th?? l??c t???o user th?? ph???i s??? d???ng .roles v?? gi?? tr??? c???a roles ph???i
//    lu??n lu??n c?? prefix ROLE_t??ng??c??ng???????c
//    - th?????ng m?? t??? cho vi???c c?? quy???n ????? l??m m???t ch???c n??ng n??o ???? v?? d??? : th??m, x??a
//    s???a....
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                    .antMatchers("/login/signin")
                    .permitAll()
//                    .antMatchers("/login/signup")
//                    .hasAnyRole("ROLE_ADMIN","ROLE_USER")
//                    .hasAuthority("ADMIN")
                    .antMatchers("/menu/files/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated();

        http.addFilterBefore(customFilterJwt, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
