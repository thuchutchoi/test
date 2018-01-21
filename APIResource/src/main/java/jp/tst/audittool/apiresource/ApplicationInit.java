package jp.tst.audittool.apiresource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * The Class RequestIntercepter.
 */
@WebListener
@EnableWebMvc
@Configuration
@ComponentScan("jp.tst.audittool.apiresource")
public class ApplicationInit extends WebMvcConfigurerAdapter {

  @Bean
  public RequestIntercepter requestIntercepter() {
    RequestIntercepter req = new RequestIntercepter();
    return req;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new RequestIntercepter()).addPathPatterns("/**")
        .excludePathPatterns("/logout/**");
  }
}
