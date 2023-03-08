/**
 * @author Lily H.
 */

package org.lilyhe.admin.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Lily H.
 * specify authentication, access control policies, and authorization rules for web app
 * critical part of Spring security responsible for setting up logins
 */

@Configuration
//  overrides the default configuration provided by Spring Security
@EnableWebSecurity
public class SecurityConfig {

    // returns a new instance of the class that implements userDetailService
    @Bean
    public UserDetailsService userDetailsService() {
        return new MealNannyUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Spring Security authentication provider
    public DaoAuthenticationProvider authenticationProvider() {
        // new instance
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //sets details service and pw encoder to the instance
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        // simple return
        return authenticationProvider;
    }

    // method built from HttpSecurity instance
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        // retrieves the AuthenticationManagerBuilder instance from the HttpSecurity instance.
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        //line sets the DaoAuthenticationProvider for the AuthenticationManagerBuilder instance
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        return authenticationManagerBuilder.build();
    }

    // method built from the HttpSecurity instance and returns it with chaining to allow for root url, require auth
    // for any other request, and that's it for authHttpRequest...
    // Goes to form based login with custom username params and that's it.
    // and then we build
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                // * is 1 level wild card
                // ** is everything under that path...
                .authorizeHttpRequests()
                //allow for main page to display but any other action will require member login
                .requestMatchers("/", "/error", "/register", "/accounts/check_email", "/accounts/save").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").usernameParameter("email").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .build();
    }

    // lambda accepts webSecurity object as param and calls ignore to see which requests to ignore. requests like
    // image and js and jar files can get pass security restrictions
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring()
                .requestMatchers("/images/**", "/js/**", "/webjars/**", "/*.css");
    }
}

