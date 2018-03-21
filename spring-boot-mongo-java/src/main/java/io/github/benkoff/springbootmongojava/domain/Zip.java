package io.github.benkoff.springbootmongojava.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Ben Novikov on 2018 March 21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zip {

    private String id;
    private String city;
    private double[] location;
    private long population;
    private String state;
}
