package com.alphabare.security;


import java.util.Arrays;
import java.util.Collections;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import jakarta.servlet.http.HttpServletRequest;


@Configuration
public class AppConfig {

	@Bean
	public SecurityFilterChain configuration(HttpSecurity http) throws Exception {
		
		
		http.sessionManagement(se -> se.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
		cors(cors ->{
			cors.configurationSource(new org.springframework.web.cors.CorsConfigurationSource(){
				
				@Override
				public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration configuration= new CorsConfiguration();
					configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
					configuration.setAllowedMethods(Collections.singletonList("*"));
					configuration.setAllowCredentials(true);
					configuration.setAllowedHeaders(Collections.singletonList("*"));
					configuration.setExposedHeaders(Arrays.asList("Authorization"));
					return configuration;
				}
			});
		}) .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST , "/api/user/registers").permitAll()
				.requestMatchers("/swagger-ui*/**","/v3/api-docs/**").permitAll()
				.requestMatchers("api/user/loginuser").hasAnyRole("ADMIN","CUSTOMER")
				.requestMatchers(HttpMethod.POST, "/api/blog/post","/api/comment/addcomment/{blogId}").hasAnyRole("ADMIN","CUSTOMER")
				.requestMatchers(HttpMethod.GET,"/api/comment/bypostId/{postId}","/api/blog/searchblogByHeading","/api/blog/get/allblog","/api/blog/searchblogByCategory","/api/user/getAllUserByEmail/{email}").hasAnyRole("ADMIN","CUSTOMER")
		        .requestMatchers(HttpMethod.PATCH,"/api/blog/{blogId}/update","/api/comment/updatecommentsByCommentId/{commentId}").hasAnyRole("CUSTOMER","ADMIN")
				.anyRequest().hasRole("ADMIN"))
				.csrf(csrf -> csrf.disable())
				.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
				.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
		        .formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults());
		
		return http.build();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder() ;
	}
	
	 

}
