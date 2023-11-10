package pl.edu.pw.pamiwmvc;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring6.SpringTemplateEngine;

@SpringBootApplication
public class PamiwMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PamiwMvcApplication.class, args);
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        var engine = new SpringTemplateEngine();

        engine.addDialect(new LayoutDialect());

        return engine;
    }
}
