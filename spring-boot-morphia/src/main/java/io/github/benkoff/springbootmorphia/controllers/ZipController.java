package io.github.benkoff.springbootmorphia.controllers;

import com.mongodb.MongoClient;
import io.github.benkoff.springbootmorphia.domain.Zip;
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
@RestController
@RequestMapping("/zips")
public class ZipController {
    private final Datastore datastore;

    public ZipController(MongoClient mongoClient) {
        Morphia morphia = new Morphia();
        morphia.map(Zip.class);
        this.datastore = morphia.createDatastore(mongoClient, "agg");
        datastore.ensureIndexes();
    }

    @RequestMapping(value = "/", method = GET)
    public Integer count() {
        return datastore.find(Zip.class).asList().size();
    }

//    public List<Zip> findAll() {
//        return datastore.find(Zip.class).asList();
//    }

    @RequestMapping(value = "/search/{text}", method = GET)
    ResponseEntity searchByText(@PathVariable String text) {
        Query<Zip> query = datastore.createQuery(Zip.class);
        List<Zip> found = query.search(text).asList();

        try {
            if(found != null) {
                return new ResponseEntity(found, HttpStatus.OK);
            }
            else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
