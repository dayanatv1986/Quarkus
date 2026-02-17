package org.day;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

import java.util.Optional;

import static io.quarkus.vertx.http.runtime.filters.OriginalRequestContext.isPresent;

@Path("/game")
public class GameResource {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @POST
    @Path("/save")
    public Response saveGame(GameEntity game) {
        game.persist();
        return Response.ok("Game saved successfully").build();
    }

    @GET
    @Path("/all")
    public Response getAllGames() {
        return Response.ok(GameEntity.listAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public GameEntity getById(@PathParam("id") String id) {
        return GameEntity.findById(new ObjectId(id));
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GameEntity update(@PathParam("id") String id, GameEntity game) {
        GameEntity entity = GameEntity.findById(new ObjectId(id));
        if (entity == null) {
            throw new WebApplicationException("Game not found", 404);
        }
        entity.nombre = game.nombre;
        entity.descripcion = game.descripcion;
        entity.precio = game.precio;
        entity.categoria = game.categoria;
        entity.anio = game.anio;
        entity.update();
        return entity;
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteGame(@PathParam("id") String id) {
        Optional<GameEntity> optionalGame = GameEntity.findByIdOptional(new ObjectId(id));
        if (optionalGame.isPresent()) {
            optionalGame.get().delete();
            return Response.ok("Game deleted successfully").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
