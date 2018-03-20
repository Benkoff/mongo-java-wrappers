package io.github.benkoff.springbootmorphia.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.geojson.Point;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.utils.IndexType;

import java.math.BigDecimal;

/**
 * Created by Ben Novikov on 2018 March 19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity("zips")
@Indexes(@Index(fields = @Field(value = "$**", type = IndexType.TEXT)))
public class Zip {
    @Id
    @JsonProperty("id")
    private String id;
    @JsonProperty("city")
    private String city;
    @JsonProperty("loc")
    @Embedded
    private Point location;
    @JsonProperty("pop")
    private BigDecimal population;
    @JsonProperty("state")
    private String state;
}
