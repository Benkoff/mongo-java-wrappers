package io.github.benkoff.springbootmorphia.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.benkoff.springbootmorphia.domain.Zip;
import org.bson.types.ObjectId;
import org.geojson.Point;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ben Novikov on 2018 March 19
 */
public class ZipTest {

    @Test
    public void whenUsingJsonProperty_thenCorrect() throws IOException {
        double[] location = {-72.622739, 42.070206};
        Zip zip = new Zip(new String("01001"),
                "AGAWAM",
                location,
                15338L,
                "MA");

        String result = new ObjectMapper().writeValueAsString(zip);

        assertThat(result, containsString("01001"));
        assertThat(result, containsString("AGAWAM"));
        assertThat(result, containsString("-72.622739"));
        assertThat(result, containsString("42.070206"));
        assertThat(result, containsString("15338"));
        assertThat(result, containsString("MA"));

        Zip readValue = new ObjectMapper().readerFor(Zip.class).readValue(result);

        assertEquals(new String("01001"), readValue.getId());
        assertEquals("AGAWAM", readValue.getCity());
        assertTrue(Arrays.equals(new double[]{-72.622739, 42.070206}, readValue.getLocation()));
        assertEquals(15338L, readValue.getPopulation());
        assertEquals("MA", readValue.getState());
    }
}
