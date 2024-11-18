/* Italy Company - Fast Team(C) 2023 */
package it.nowprj.security;

import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.Customizer.withDefaults;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(WebSecurityConfig.class);

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    log.info("* SecurityFilterChain configured");
    // @spotless:off
        http
                .authorizeHttpRequests(
                        authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers(OPTIONS, "/**").permitAll()
                                        .requestMatchers(POST, "/**").permitAll()
                                        .anyRequest().authenticated())
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers ->
                        headers.addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy", "default-src 'self'"))
                                .httpStrictTransportSecurity(security -> security.includeSubDomains(true).maxAgeInSeconds(31536000).preload(true))
                                .xssProtection(withDefaults()))
                .csrf(AbstractHttpConfigurer::disable)
        ;
        // @spotless:on
    return http.build();
  }

  @Bean
  @Profile("dev")
  public WebMvcConfigurer corsConfigurer() {
    log.info("CORS allowed from all origins");
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/v1/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTION")
            .allowedHeaders("*");
      }
    };
  }
}
