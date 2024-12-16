package pe.idat.ExamenFInal.security;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class apiKeyAuthFilter implements ContainerRequestFilter {
    private static final String SUBSCRIPTION_KEY_HEADER = "Evaluacion-Final";
    private static final String VALID_API_KEY = "123e4567-e89b-12d3-a456-426614174000";

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String apikey = containerRequestContext.getHeaderString(SUBSCRIPTION_KEY_HEADER);
        if (apikey ==null || !isValidApiKey(apikey)){
            abortWithUnauthorized(containerRequestContext);
        }
    }

    public boolean isValidApiKey(String apiKey){
        return VALID_API_KEY.equals(apiKey);
    }

    private void abortWithUnauthorized(ContainerRequestContext containerRequestContext){
        containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Apikey no autorizada").build());
    }
}