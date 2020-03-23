package com.bring.a.smile.config;

import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import javax.validation.Valid;

//TODO: Add application context path

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class BringASmileConfiguration extends Configuration {
    public SwaggerBundleConfiguration getSwagger() {
        return swagger;
    }

    @Valid
    private SwaggerBundleConfiguration swagger;




}
