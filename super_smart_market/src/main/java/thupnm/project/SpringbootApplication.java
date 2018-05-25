package thupnm.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
//@ComponentScan({"thupnm.project.mapper"})
public class SpringbootApplication {
//
//	@Bean
//	public Docket productAPI() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("thupnm.project.webrest"))
//				.paths(PathSelectors.regex("/furniture/*"))
//				.build()
//				.apiInfo(metaData());
//	}
//
//	private ApiInfo metaData() {
//		ApiInfo apiInfo = new ApiInfo(
//                "My REST API",
//                "Some custom description of API.",
//                "API TOS",
//                "Terms of service",
//                new Contact("John Doe", "www.example.com", "myeaddress@company.com"),
//                "License of API", "API license URL", Collections.emptyList());
//		return apiInfo;
//	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
