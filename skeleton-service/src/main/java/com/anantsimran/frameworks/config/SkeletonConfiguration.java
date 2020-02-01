package com.anantsimran.frameworks.config;

import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.Valid;


@NoArgsConstructor
@Data
public class SkeletonConfiguration extends Configuration {
    public SwaggerBundleConfiguration getSwagger() {
        return swagger;
    }

    @Valid
    private SwaggerBundleConfiguration swagger;


}
