package everis.bootcamp.bankMicroservice.Repository;

import everis.bootcamp.bankMicroservice.Document.Bank;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends ReactiveMongoRepository<Bank,String> {
}
