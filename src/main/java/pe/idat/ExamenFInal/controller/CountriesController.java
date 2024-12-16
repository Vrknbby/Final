package pe.idat.ExamenFInal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pe.idat.ExamenFInal.model.Countries;
import pe.idat.ExamenFInal.repository.CountriesRepository;

import java.util.List;

@Service
@Path("/api/countries")
public class CountriesController {
    @Autowired
    private CountriesRepository countriesRepository;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountries() {
        try{
            List<Countries> countries = countriesRepository.findAll();
            String json = objectMapper.writeValueAsString(countries);
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al obtener Clientes").build();
        }
    }

    @PUT
    @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCountries(@PathParam("name") String name, String Json){
        try{
            Countries countriesUpdate = objectMapper.readValue(Json, Countries.class);
            Countries countries = countriesRepository.findById(name).orElse(null);
            if (countries!= null){
                countries.setContinent(countriesUpdate.getContinent());
                countries.setLanguage(countriesUpdate.getLanguage());
                countriesRepository.save(countries);
                return Response.status(Response.Status.OK).entity("País actualizado correctamente").type(MediaType.APPLICATION_JSON).build();
            }
            return Response.status(Response.Status.NOT_FOUND).entity("País no encontrado").type(MediaType.APPLICATION_JSON).build();
        }catch (JsonProcessingException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al procesar la solicitud").type(MediaType.APPLICATION_JSON).build();
        }
    }

    @DELETE
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCountries(@PathParam("name") String name) {
        Countries countries = countriesRepository.findById(name).orElse(null);
        if (countries != null){
            countriesRepository.delete(countries);
            return Response.status(Response.Status.OK).entity("País eliminado correctamente").type(MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("País no encontrado").type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCountries(String json) {
        try {
            Countries newCountri = objectMapper.readValue(json, Countries.class);
            countriesRepository.save(newCountri);
            String createdJson = objectMapper.writeValueAsString(newCountri);
            return Response.status(Response.Status.CREATED)
                    .entity(createdJson)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("País creado correctamente")
                    .build();
        }
    }
}
