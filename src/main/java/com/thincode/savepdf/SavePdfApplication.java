package com.thincode.savepdf;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author carpinteyror
 * @version 1.0
 * @date 15 oct. 2020
 */
@SpringBootApplication
public class SavePdfApplication {

  public static void main(String[] args) {
    SpringApplication.run(SavePdfApplication.class, args);
  }


  /**
   * Cors filter.
   *
   * @return the filter registration bean
   */
  @Bean
  public FilterRegistrationBean<Filter> corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration apiCorsConfig = new CorsConfiguration();
    apiCorsConfig.addAllowedOrigin(CorsConfiguration.ALL);
    apiCorsConfig.addAllowedHeader(CorsConfiguration.ALL);
    apiCorsConfig.addAllowedMethod(CorsConfiguration.ALL);
    apiCorsConfig.setMaxAge(3600L);
    source.registerCorsConfiguration("/api/**", apiCorsConfig);

    CorsConfiguration allCorsConfig = new CorsConfiguration();
    allCorsConfig.addAllowedOrigin(CorsConfiguration.ALL);
    allCorsConfig.addAllowedHeader(CorsConfiguration.ALL);
    allCorsConfig.addAllowedMethod(CorsConfiguration.ALL);
    allCorsConfig.setMaxAge(3600L);
    source.registerCorsConfiguration("/**", allCorsConfig);

    FilterRegistrationBean<Filter> filterRegistrationBean =
        new FilterRegistrationBean<>(new CorsFilter(source));
    filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return filterRegistrationBean;
  }

}
