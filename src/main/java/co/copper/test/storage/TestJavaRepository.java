package co.copper.test.storage;

import co.copper.test.datamodel.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestJavaRepository extends CrudRepository<Test, Long> {

}
