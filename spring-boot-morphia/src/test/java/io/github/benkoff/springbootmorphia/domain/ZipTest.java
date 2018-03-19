package io.github.benkoff.springbootmorphia.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.benkoff.springbootmorphia.domain.Zip;
import org.geojson.Point;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by Ben Novikov on 2018 March 19
 */
public class ZipTest {

    @Test
    public void whenUsingJsonProperty_thenCorrect() throws IOException {
        Zip zip = new Zip("01001",
                "AGAWAM",
                new Point(-72.622739, 42.070206),
                new BigDecimal(15338),
                "MA");

        String result = new ObjectMapper().writeValueAsString(zip);

        assertThat(result, containsString("01001"));
        assertThat(result, containsString("AGAWAM"));
        assertThat(result, containsString("-72.622739"));
        assertThat(result, containsString("42.070206"));
        assertThat(result, containsString("15338"));
        assertThat(result, containsString("MA"));

        Zip readValue = new ObjectMapper().readerFor(Zip.class).readValue(result);

        assertEquals("01001", readValue.getId());
        assertEquals("AGAWAM", readValue.getCity());
        assertEquals(new Point(-72.622739, 42.070206), readValue.getLocation());
        assertEquals(new BigDecimal(15338), readValue.getPopulation());
        assertEquals("MA", readValue.getState());
    }
}
