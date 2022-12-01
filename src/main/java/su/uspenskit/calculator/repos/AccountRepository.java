package su.uspenskit.calculator.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import su.uspenskit.calculator.models.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
}
