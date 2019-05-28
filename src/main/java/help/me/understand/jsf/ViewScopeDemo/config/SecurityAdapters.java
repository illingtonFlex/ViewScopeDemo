package help.me.understand.jsf.ViewScopeDemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityAdapters {
    @Configuration
    public static class ActuatorWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {

            // Authenticate all actuator calls, require ACTUATOR role. This is configured in properties.
            http.antMatcher("/actuator/**")
                    .authorizeRequests()
                    .anyRequest().hasRole("ACTUATOR")
                    .and()
                    .httpBasic();

            // Disable csrf, this is required to expose shutdown endpoint.
            http.csrf().disable();
        }
    }
}
