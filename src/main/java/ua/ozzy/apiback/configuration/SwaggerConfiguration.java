package ua.ozzy.apiback.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import ua.ozzy.apiback.configuration.properties.SwaggerApiInfoProperties;

import static java.util.Collections.emptySet;
import static springfox.documentation.builders.PathSelectors.ant;
import static springfox.documentation.builders.RequestHandlerSelectors.any;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket springfoxDocket(ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(getIgnoredParamTypes())
                .select()
                .apis(any())
                .paths(ant("/api/**"))
                .build().apiInfo(apiInfo);
    }

    private Class<?>[] getIgnoredParamTypes() {
        return new Class<?>[] { Pageable.class };
    }

    @Bean
    ApiInfo swaggerApiInfo(SwaggerApiInfoProperties apiInfoProps) {
        return new ApiInfo(apiInfoProps.getName(), apiInfoProps.getDescription(), apiInfoProps.getVersion(),
                apiInfoProps.getTermsOfServiceUrl(), apiInfoProps.getContact(), apiInfoProps.getLicence(),
                apiInfoProps.getLicenceUrl(), emptySet());
    }

}
