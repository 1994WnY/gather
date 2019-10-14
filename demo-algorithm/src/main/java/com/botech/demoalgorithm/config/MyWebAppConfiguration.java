package com.botech.demoalgorithm.config;

import java.io.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyWebAppConfiguration extends WebMvcConfigurationSupport {

  @Value("${image.path.cap-image}")
  private String capImagePath;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {

    registry.addResourceHandler("/static/**")
        .addResourceLocations("classpath:/static/");

    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");

    registry.addResourceHandler("/capture/getCapture/**")
        .addResourceLocations("file:" + capImagePath + File.separator);
    super.addResourceHandlers(registry);
  }
}