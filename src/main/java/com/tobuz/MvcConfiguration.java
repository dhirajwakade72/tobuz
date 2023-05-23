package com.tobuz;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan
public class MvcConfiguration extends WebMvcConfigurerAdapter
{
    @Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");
		//resolver.setViewClass(JstlView.class);
		registry.viewResolver(resolver);
	}
    
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    	registry
        .addResourceHandler("/css/**", "/tools/**", "/images/**","/js/**","/slick/**")
        .addResourceLocations("/WEB-INF/assets/css/", 
    "/WEB-INF/assets/tools/", 
    "/WEB-INF/assets/images/",
    "/WEB-INF/assets/js/",
    "/WEB-INF/assets/slick/");
    }
    
    
    @Bean
    WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> 
    enableDefaultServlet() {
        return (factory) -> factory.setRegisterDefaultServlet(true);
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new 
        InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void 
    configureDefaultServletHandling(DefaultServletHandlerConfigurer 
    configurer) {
        configurer.enable();
    }
    
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(true)
                .defaultContentType(MediaType.TEXT_HTML)
                .mediaType("jsp", MediaType.TEXT_HTML);
    }
    
   
  
}
