package com.pranay.happ.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class ThymeleafConfiguration {

	  @Bean
	    public ClassLoaderTemplateResolver pdfTemplateResolver() {
	        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	        templateResolver.setPrefix("pdf-templates/");
	        templateResolver.setSuffix(".html");
	        templateResolver.setTemplateMode("HTML5");
	        templateResolver.setCharacterEncoding("UTF-8");
	        templateResolver.setOrder(1);
	        return templateResolver;
	    }

	    @Bean
	    public TemplateEngine templateEngine() {
	        TemplateEngine templateEngine = new TemplateEngine();
	        templateEngine.setTemplateResolver(pdfTemplateResolver());
	        return templateEngine;
	    }
}