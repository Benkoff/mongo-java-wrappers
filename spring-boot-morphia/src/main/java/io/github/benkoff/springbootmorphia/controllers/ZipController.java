package io.github.benkoff.springbootmorphia.controllers;

import com.mongodb.MongoClient;
import io.github.benkoff.springbootmorphia.domain.Zip;
import lombok.extern.slf4j.Slf4j;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Ben Novikov on 2018 March 19
 */
@Slf4j
@RestController
@RequestMapping("/zips")
public class ZipController {
    private final Datastore datastore;
    private Zip zips;

    public ZipController() {
        Morphia morphia = new Morphia();
        // tell Morphia where to find your classes
        // can be called multiple times with different packages or classes
        morphia.mapPackage("io.github.benkoff.springbootmorphia.domain");
        // create the Datastore connecting to the default port on the local host
        this.datastore = morphia.createDatastore(new MongoClient(), "drivers");
        datastore.ensureIndexes();
    }

    @RequestMapping(value = "/", method = GET)
    public List<Zip> findAll() {
        long startTime = System.nanoTime();
        log.info("Searching all documents...");

        final Query<Zip> query = datastore.createQuery(Zip.class);
        final List<Zip> allZips = query.asList();
        int result = allZips.size();

        long duration = (System.nanoTime() - startTime)/1000000;
        log.info("...found: {} results in {} ms", result, duration);

        return allZips;
    }

    @RequestMapping(value = "/{text}", method = GET)
    ResponseEntity searchByText(@PathVariable String text) {
        long startTime = System.nanoTime();
        log.info("Searching given text...");

        try {
            Query<Zip> query = datastore.createQuery(Zip.class);
            List<Zip> found = query.search(text).asList();
            long duration = (System.nanoTime() - startTime)/1000000;
            if(found != null) {
                log.info("...found: {} documents in {} ms", found.size(), duration);

                return new ResponseEntity(found, HttpStatus.OK);
            }
            else {
                log.info("...something wrong happend: NULL returned in {} ms", duration);

                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
