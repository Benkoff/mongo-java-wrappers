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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Ben Novikov on 2018 March 19
 */
@Slf4j
@RestController
@RequestMapping("/zips")
public class MorphiaZipController {
    private final Datastore datastore;
    private Zip zips;

    public MorphiaZipController() {
        Morphia morphia = new Morphia();
        // tell Morphia where to find your classes
        morphia.mapPackage("io.github.benkoff.springbootmorphia.domain");
        // create the Datastore connecting to the default port on the local host
        this.datastore = morphia.createDatastore(new MongoClient(), "drivers");
        datastore.ensureIndexes();
    }

    @RequestMapping(value = "/", method = GET)
    @ResponseBody
    public ResponseEntity findAll() {
        long startTime = System.nanoTime();
        log.info("Searching all documents...");

        try {
            final List<Zip> allZips;
            final Query<Zip> query = datastore.createQuery(Zip.class);
            allZips = query.asList();
            long duration = (System.nanoTime() - startTime)/1000000;

            if (!allZips.isEmpty()) {
                log.info("...found: {} results in {} ms", allZips.size(), duration);

                return new ResponseEntity(allZips, HttpStatus.OK);
            } else {
                log.error("...something wrong happend: Nothing returned in {} ms", duration);

                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{val}", method = GET)
    public ResponseEntity findAllByCityContains(@PathVariable String val) {
        long startTime = System.nanoTime();
        log.info("Searching given text...");

        try {
            final Query<Zip> query = datastore.createQuery(Zip.class);
            final List<Zip> found = query.field("city").contains(val).asList();
            long duration = (System.nanoTime() - startTime)/1000000;

            if(!found.isEmpty()) {
                log.info("...found: {} documents in {} ms", found.size(), duration);

                return new ResponseEntity(found, HttpStatus.OK);
            } else {
                log.error("...Nothing found in {} ms", duration);

                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
