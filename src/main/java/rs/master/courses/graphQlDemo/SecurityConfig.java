package rs.master.courses.graphQlDemo;

import org.springframework.context.annotation.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable());

		http.authorizeHttpRequests(auth -> auth.requestMatchers("/graphql").permitAll().anyRequest().permitAll())
				.httpBasic(Customizer.withDefaults());
		

		return http.build();
	}

	@Bean
	public UserDetailsService users() {
		UserDetails admin = User.withUsername("admin").password("{noop}admin123").roles("ADMIN").build();

		return new InMemoryUserDetailsManager(admin);
	}
}