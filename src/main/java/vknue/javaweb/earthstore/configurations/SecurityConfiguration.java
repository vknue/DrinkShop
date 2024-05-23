package vknue.javaweb.earthstore.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.annotation.SessionScope;
import vknue.javaweb.earthstore.models.cart.Cart;
import vknue.javaweb.earthstore.models.filters.IpLoggingFilter;
import vknue.javaweb.earthstore.repositories.IUserRepository;
import vknue.javaweb.earthstore.services.UserDetailService;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserDetailService userService;


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService).passwordEncoder(passwordEncoder());

    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new IpLoggingFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((authorization) -> authorization
                        .requestMatchers(
                                "/h2-console/**",
                                "/css/*.css",
                                "/error",
                                "/cart",
                                "/cart/myCart",
                                "/cart/addOne",
                                "/cart/removeOne",
                                "/cart/removeAll",
                                "/shop/drinks",
                                "/shop/addItemToCart").permitAll()
                        .requestMatchers(
                                "/transactions/index",
                                "/transactions/details",
                                "/cart/checkout",
                                "/checkOut/pay",
                                "/payment/payPayPal",
                                "/payment/success",
                                "/payment/error",
                                "/payment/cancel")
                        .authenticated()
                        .requestMatchers(
                                "/category/index",
                                "/category/add",
                                "/category/addCategory",
                                "/category/delete",
                                "/transactions/admin/index",
                                "/log/index",
                                "/drink/index",
                                "/drink/get/add",
                                "/drink/get/edit",
                                "/drink/edit",
                                "/drink/add",
                                "/drink/delete"
                        ).hasAuthority("ADMIN")

                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                )
                .formLogin(
                        (form) -> form
                                .defaultSuccessUrl("/shop/drinks", true)
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/").permitAll()
                );

        return http.build();
    }

    @Bean
    @SessionScope
    public Cart cart() {
        return new Cart();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
