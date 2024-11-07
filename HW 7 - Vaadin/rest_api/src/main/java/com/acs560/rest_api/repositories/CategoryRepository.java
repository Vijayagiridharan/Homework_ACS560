package com.acs560.rest_api.repositories;

import com.acs560.rest_api.entities.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link CategoryEntity} entities.
 * This interface extends {@link CrudRepository} to provide CRUD operations.
 */
@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

    /**
     * Retrieves a category by its name.
     *
     * @param categoryName the name of the category to retrieve.
     * @return an Optional of {@link CategoryEntity} if found, otherwise empty.
     */
    Optional<CategoryEntity> findByCategoryNameIgnoreCase(String categoryName);
    
    /**
     * Checks if a category exists by its name.
     *
     * @param categoryName the name of the category.
     * @return true if a category with the given name exists, false otherwise.
     */
    boolean existsByCategoryNameIgnoreCase(String categoryName);

	void deleteById(Long id);
}
