package co.copper.test.storage;

import co.copper.test.datamodel.CopperPerson;
import co.copper.test.datamodel.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CopperPersonRepository extends CrudRepository<CopperPerson, Long> {

    List<CopperPerson> findAll();
}
