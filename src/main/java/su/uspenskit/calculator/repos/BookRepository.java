package su.uspenskit.calculator.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import su.uspenskit.calculator.models.Author;
import su.uspenskit.calculator.models.Book;

public interface BookRepository  extends CrudRepository<Book, Long> {
    @Query("select b from Book b where b.title = :query or b.description = :query")
    Iterable<Book> findByQueryExact(@Param("query") String query);

    @Query("select b from Book b where " +
            "lower(b.title) like concat('%', lower(:query), '%')  or " +
            "lower(b.description) like concat('%', lower(:query), '%' )")
    Iterable<Book> findByQueryInexact(@Param("query") String query);
}
