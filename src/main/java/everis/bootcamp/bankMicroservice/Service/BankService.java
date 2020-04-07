package everis.bootcamp.bankMicroservice.Service;


import everis.bootcamp.bankMicroservice.Document.Bank;
import everis.bootcamp.bankMicroservice.ServiceDTO.Request.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankService {
    Mono<Bank> create(BankRequest bankRequest);
    Mono<Bank> update(String id,BankRequest bankRequest);
    Flux<Bank> readAll();
    Mono<Bank> delete(String id);
    Mono<Bank> getOne(String id);

    Flux<AccountsRequest> allAccountsBankAccount(String id);
    Flux<AccountsRequest> allAccountsCreditAccount(String id);
    Flux<AccountsRequest> allAccounts(String id);
    Mono<ClientProfilesRequest> getClientProfiles(String id);
    Flux<AccountsRequest> allAccountsInTime(String bankId, int days);

    Mono<Bank> transfereByBank(String id, TransferenceRequest transferenceRequest);

    Mono<Bank> transfereByCredit(String id, TransferenceRequest transferenceRequest);

    Mono<Bank> payCredit(String id, CreditPaymentRequest creditPaymentRequest);
}
