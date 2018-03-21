package io.github.benkoff.springbootdatamongodb.repositories;

import io.github.benkoff.springbootdatamongodb.domain.Zip;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ben Novikov on 2018 March 21
 */
@Repository
public interface ZipRepository extends MongoRepository<Zip, String> {
    public List<Zip> findAll();
    public List<Zip> findAllByCityContains(String city);
}
