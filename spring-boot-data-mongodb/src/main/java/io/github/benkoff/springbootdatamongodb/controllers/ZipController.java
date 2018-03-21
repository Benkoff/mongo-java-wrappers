package io.github.benkoff.springbootdatamongodb.controllers;

import io.github.benkoff.springbootdatamongodb.domain.Zip;
import io.github.benkoff.springbootdatamongodb.repositories.ZipRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Ben Novikov on 2018 March 21
 */
@Slf4j
@RestController
@RequestMapping("/zips")
public class ZipController {

    private Zip zips;
    private ZipRepository repository;

    public ZipController(ZipRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/", method = GET)
    @ResponseBody
    public ResponseEntity findAll() {
        long startTime = System.nanoTime();
        log.info("Searching all documents...");

        try {
            final List<Zip> allZips = repository.findAll();
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

    @RequestMapping(value = "/{text}", method = GET)
    public ResponseEntity findAllByCityContains(@PathVariable String text) {
        long startTime = System.nanoTime();
        log.info("Searching given text...");

        try {
            List<Zip> found = repository.findAllByCityContains(text);
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
