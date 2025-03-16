package xyz.kajih.xplorer.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xyz.kajih.xplorer.db.internal.ProductEntity;

import java.util.List;

@Repository
public interface DbRepository extends CrudRepository<ProductEntity, Long> {
    List<ProductEntity> findByLastName(String lastName);
    ProductEntity findById(long id);
}