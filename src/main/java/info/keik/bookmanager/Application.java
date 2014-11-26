package info.keik.bookmanager;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.filter.CharacterEncodingFilter;

@Controller
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("/")
    public String welcome() {
        return "index.html";
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }

    @Bean
    public ApplicationSecurity applicationSecurity() {
        return new ApplicationSecurity();
    }

    protected static class ApplicationSecurity extends
            WebSecurityConfigurerAdapter {

        @Override
        public void configure(AuthenticationManagerBuilder auth)
                throws Exception {
            auth.inMemoryAuthentication().withUser("user").password("password")
                    .roles("USER").and().withUser("admin").password("password")
                    .roles("USER", "ADMIN");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().permitAll()

            .and().formLogin().loginProcessingUrl("/session")
                    .defaultSuccessUrl("/books").loginPage("/login")
                    .usernameParameter("id").passwordParameter("password")
                    .permitAll()

                    .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
        }
    }

}
