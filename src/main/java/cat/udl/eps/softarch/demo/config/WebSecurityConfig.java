package cat.udl.eps.softarch.demo.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Value("${allowed-origins}")
        String[] allowedOrigins;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/identity").authenticated()
                    .antMatchers(HttpMethod.POST, "/users").anonymous()
                    .antMatchers(HttpMethod.POST, "/users/*").denyAll()
                    .antMatchers(HttpMethod.POST, "/propagators").anonymous()
                    .antMatchers(HttpMethod.POST, "/propagators/*").denyAll()
                    .antMatchers(HttpMethod.POST, "/admins").anonymous()
                    .antMatchers(HttpMethod.POST, "/admins/*").denyAll()
                    .antMatchers(HttpMethod.POST, "/donors").anonymous()
                    .antMatchers(HttpMethod.POST, "/donors/*").denyAll()
                    .antMatchers(HttpMethod.GET, "/takes/*").authenticated()
                    .antMatchers(HttpMethod.POST, "/**/*").authenticated()
                    .antMatchers(HttpMethod.PUT, "/**/*").authenticated()
                    .antMatchers(HttpMethod.PATCH, "/**/*").authenticated()
                    .antMatchers(HttpMethod.DELETE, "/**/*").authenticated()
                    .anyRequest().permitAll()
                    .and()
                    .httpBasic().realmName("demo")
                    .and()
                    .cors()
                    .and()
                    .csrf().disable();
        }

        @Bean
        CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowedOriginPatterns(Arrays.asList(allowedOrigins));
            corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
            corsConfiguration.setAllowedHeaders(List.of("*"));
            corsConfiguration.setAllowCredentials(true);
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", corsConfiguration);
            return source;
        }

        @Bean
        public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
            return new SecurityEvaluationContextExtension();
        }
    }
