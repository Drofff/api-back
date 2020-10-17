package ua.ozzy.apiback.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import ua.ozzy.apiback.configuration.properties.SwaggerApiInfoProperties;

import static java.util.Collections.emptySet;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket springfoxDocket(ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo);
    }

    @Bean
    ApiInfo swaggerApiInfo(SwaggerApiInfoProperties apiInfoProps) {
        return new ApiInfo(apiInfoProps.getName(), apiInfoProps.getDescription(), apiInfoProps.getVersion(),
                apiInfoProps.getTermsOfServiceUrl(), apiInfoProps.getContact(), apiInfoProps.getLicence(),
                apiInfoProps.getLicenceUrl(), emptySet());
    }

}
