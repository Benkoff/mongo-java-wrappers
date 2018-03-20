package io.github.benkoff.springbootmorphia.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.utils.IndexType;

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
    @Property("id")
    private String id;
    @Property("city")
    private String city;
    @Property("loc")
    private double[] location;
    @Property("pop")
    private long population;
    @Property("state")
    private String state;
}
