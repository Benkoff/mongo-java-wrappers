package io.github.benkoff.springbootmongojava.controllers;

import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ben Novikov on 2018 March 22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MongoClientZipControllerInegrationTest {

    @Autowired
    private MongoClientZipController zipController;

    @Test
    @ShouldMatchDataSet(location = "/datasets/Agawam.json")
    public void requestCorrectNameShouldReturnValidJson() {
        zipController.findAllByCityContains("Agawam");
    }

    @Test
    public void requestAbracadabraShouldReturnNotFound() {
        ResponseEntity expectedResponse = new ResponseEntity(HttpStatus.NOT_FOUND);

        assertEquals(expectedResponse, zipController.findAllByCityContains("Abrakadabra"));
    }
}
