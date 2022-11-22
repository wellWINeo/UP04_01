package su.uspenskit.calculator.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import su.uspenskit.calculator.models.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    @Query("select a from Author a where a.firstName = :query or a.middleName = :query or a.lastName = :query")
    Iterable<Author> findByQueryExact(@Param("query") String query);

    @Query("select a from Author a where " +
            "lower(a.firstName) like concat('%', lower(:query), '%')" +
            "or lower(a.middleName) like concat('%', lower(:query), '%')" +
            "or lower(a.lastName) like concat('%', lower(:query), '%')")
    Iterable<Author> findByQueryInexact(@Param("query") String query);
}
