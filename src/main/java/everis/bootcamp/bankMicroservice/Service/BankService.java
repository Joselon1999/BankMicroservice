package everis.bootcamp.bankMicroservice.Service;


import everis.bootcamp.bankMicroservice.Document.Bank;
import everis.bootcamp.bankMicroservice.ServiceDTO.Request.BankRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankService {
    Mono<Bank> create(BankRequest bankRequest);
    Mono<Bank> update(String id,BankRequest bankRequest);
    Flux<Bank> readAll();
    Mono<Bank> delete(String id);
    Mono<Bank> getOne(String id);

    Flux<Bank> allAccounts(String id);
}
