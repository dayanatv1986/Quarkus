package org.day;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;


import java.math.BigDecimal;

@MongoEntity(collection = "game_data")
public class GameEntity extends PanacheMongoEntity {
    @NotNull
    @Size(min = 2, max = 100)
    public String nombre;

    @NotNull
    public String descripcion;

    @NotNull
    @Min(1)
    public int precio;

    @NotNull
    public String categoria;

    @NotNull
    @Min(1980)
    public int anio;



}
