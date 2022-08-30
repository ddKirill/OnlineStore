package com.ddkirill.strore.repository;


import com.ddkirill.strore.entity.ProductEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    @Modifying
    @Query("DELETE FROM product WHERE title=:title")
    void deleteByTitle(@Param("title") String title);
    
}
