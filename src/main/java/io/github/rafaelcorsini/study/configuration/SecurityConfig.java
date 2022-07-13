package io.github.rafaelcorsini.study.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.rafaelcorsini.study.security.jwt.JwtAuthFilter;
import io.github.rafaelcorsini.study.security.jwt.JwtService;
import io.github.rafaelcorsini.study.service.impl.UsuarioServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	private JwtService jwtService;
		
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public OncePerRequestFilter jwtFilter() {
		return new JwtAuthFilter(jwtService, usuarioService);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/* auth.inMemoryAuthentication()
				.passwordEncoder(passwordEncoder())
				.withUser("fulano")
				.password(passwordEncoder().encode("123"))
				.roles("USER","ADMIN"); */
		auth.
			userDetailsService(usuarioService)
			.passwordEncoder(passwordEncoder());
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/api/clientes/**")
					.hasAnyRole("USER","ADMIN")
					//.hasAuthority("AUTHORITY_X")
					//.permitAll()	
					//.authenticated()
				.antMatchers("/api/produtos/**")
					.hasRole("ADMIN")
				.antMatchers("/api/pedidos/**")
					.hasAnyRole("USER","ADMIN")
				.antMatchers(HttpMethod.POST, "/api/usuarios/**")	
					.permitAll()
				.anyRequest().authenticated()
			.and()
				//.formLogin("/meu-login.html")
				//.formLogin();
				//.httpBasic(); // for basic authentication (with session user)
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // for JWT authentication
			.and()
				.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
	                "/v2/api-docs",
	                "/configuration/ui",
	                "/swagger-resources/**",
	                "/configuration/security",
	                "/swagger-ui.html",
	                "/webjars/**");
	}
	
	//Exemplo formulário de login customizado
	/*
	 * <form method="post"> 
	 * 	 <input type="text" name="username"> 
	 * 	 <input type="secret" name="password"> <button type="submit"...> </form>
	 */
}
