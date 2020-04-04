package everis.bootcamp.bankMicroservice.Service;

import everis.bootcamp.bankMicroservice.Document.Bank;
import everis.bootcamp.bankMicroservice.Repository.BankRepository;
import everis.bootcamp.bankMicroservice.ServiceDTO.Request.AccountsRequest;
import everis.bootcamp.bankMicroservice.ServiceDTO.Request.BankRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    BankRepository bankRepository;

    @Override
    public Mono<Bank> create(BankRequest bankRequest) {
        Bank bank = new Bank();
        bank.setName(bankRequest.getName());
        bank.setClientProfiles(bankRequest.getClientProfiles());
        bank.setTransactionLeft(bankRequest.getTransactionLeft());
        return bankRepository.save(bank);
    }

    @Override
    public Mono<Bank> update(String id, BankRequest bankRequest) {

        return bankRepository.findById(id).flatMap(bank -> {
            bank.setName(bankRequest.getName());
            bank.setClientProfiles(bankRequest.getClientProfiles());
            bank.setTransactionLeft(bankRequest.getTransactionLeft());
            return bankRepository.save(bank);
        });
    }

    @Override
    public Flux<Bank> readAll() {
        return bankRepository.findAll();
    }

    @Override
    public Mono<Bank> delete(String id) {
        return getOne(id).switchIfEmpty(Mono.empty()).filter(Objects::nonNull)
                .flatMap(bank -> bankRepository.delete(bank).then(Mono.just(bank)));
    }

    @Override
    public Mono<Bank> getOne(String id) {
        return bankRepository.findById(id);
    }

    @Override
    public Flux<Bank> allAccounts(String id) {
        return Flux.create(bankFlux -> {
            String url = "http://localhost:8010/api/bankAccounts/" + id;
            Flux<AccountsRequest> client = WebClient.create()
                    .get()
                    .uri(url)
                    .retrieve()
                    .bodyToFlux(AccountsRequest.class);
            return null;
        });
    }
}
