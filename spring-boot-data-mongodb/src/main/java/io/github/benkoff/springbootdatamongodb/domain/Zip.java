package io.github.benkoff.springbootdatamongodb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by Ben Novikov on 2018 March 21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "zips")
public class Zip {
    @Id
    private String id;
    private String city;
    @Field("loc")
    private double[] location;
    @Field("pop")
    private long population;
    private String state;

}

