package pe.idat.ExamenFInal.config;

import org.glassfish.jersey.server.ResourceConfig;

import org.springframework.context.annotation.Configuration;
import pe.idat.ExamenFInal.controller.CountriesController;
import pe.idat.ExamenFInal.security.apiKeyAuthFilter;
@Configuration
public class ServerConfig extends ResourceConfig {

    public ServerConfig(){
        register(CountriesController.class);
        register(apiKeyAuthFilter.class);
    }
}
