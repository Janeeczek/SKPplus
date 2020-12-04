package com.JanCode.SKPplus.config;

import com.JanCode.SKPplus.Authentication.MyDaoAuthenticationProvider;
import com.JanCode.SKPplus.Listeners.LogoutListener;
import com.JanCode.SKPplus.Listeners.MyHttpSessionEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    public SecurityConfiguration() {
        super();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new MyHttpSessionEventPublisher();
    }
    @Bean
    public ApplicationListener<SessionDestroyedEvent> logoutListener() {
        return new LogoutListener();
    }
    @Bean
    public SessionRegistry sessionRegistry() { return new SessionRegistryImpl(); }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/login*","/reset","/register*","/css/**", "/img/**", "/js/**", "/vendor/**", "/scss/**","/layouts/**","/fragments/**")
                .permitAll();
        http.sessionManagement().maximumSessions(-1).sessionRegistry(sessionRegistry());
        http.authorizeRequests()
                .antMatchers("/user/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
        .and().formLogin()
                .failureUrl("/login?error")
                .loginPage("/login*")
                .defaultSuccessUrl("/",true)

        .and().logout()
                //.invalidateHttpSession(true)
                //.clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout");




        /*http.sessionManagement()
                .sessionFixation().migrateSession();

         */
        //http.authorizeRequests().antMatchers("/webjars/**").permitAll();

        http.exceptionHandling().accessDeniedPage("/error?accesdenied");
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public MyDaoAuthenticationProvider myAuthProvider() throws Exception {
        MyDaoAuthenticationProvider provider = new MyDaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthProvider());
    }

}
