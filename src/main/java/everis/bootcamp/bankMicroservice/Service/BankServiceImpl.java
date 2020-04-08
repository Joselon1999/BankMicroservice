package everis.bootcamp.bankMicroservice.Service;

import everis.bootcamp.bankMicroservice.Document.Bank;
import everis.bootcamp.bankMicroservice.Repository.BankRepository;
import everis.bootcamp.bankMicroservice.ServiceDTO.Request.*;
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
    public Mono<Bank> create(Bank bankRequest) {
        Bank bank = new Bank();
        bank.setName(bankRequest.getName());
        bank.setClientProfiles(bankRequest.getClientProfiles());
        bank.setTransactionLeft(bankRequest.getTransactionLeft());
        bank.setComision(bankRequest.getComision());
        return bankRepository.save(bank);
    }

    @Override
    public Mono<Bank> update(String id, BankRequest bankRequest) {

        return bankRepository.findById(id).flatMap(bank -> {
            bank.setName(bankRequest.getName());
            bank.setClientProfiles(bankRequest.getClientProfiles());
            bank.setTransactionLeft(bankRequest.getTransactionLeft());
            bank.setComision(bankRequest.getComision());
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
    public Flux<AccountsRequest> allAccountsBankAccount(String id) {
        return WebClient.create()
                .get()
                .uri("http://localhost:8010/api/bankAccounts/" + id)
                .retrieve()
                .bodyToFlux(AccountsRequest.class);

    }

    @Override
    public Flux<AccountsRequest> allAccountsCreditAccount(String id) {
        return WebClient.create()
                .get()
                .uri("http://localhost:8020/api/creditAccounts/" + id)
                .retrieve()
                .bodyToFlux(AccountsRequest.class);

    }

    @Override
    public Flux<AccountsRequest> allAccounts(String id) {
        return WebClient.create()
                .get()
                .uri("http://localhost:8010/api/bankAccounts/" + id)
                .retrieve()
                .bodyToFlux(AccountsRequest.class)
                .concatWith(
                        WebClient.create()
                                .get()
                                .uri("http://localhost:8020/api/creditAccounts/" + id)
                                .retrieve()
                                .bodyToFlux(AccountsRequest.class));

    }

    @Override
    public Mono<ClientProfilesRequest> getClientProfiles(String id) {
        return bankRepository.findById(id).map(bank -> {
            return new ClientProfilesRequest(bank.getClientProfiles());
        })
                .switchIfEmpty(Mono.error(new Exception("NO EXISTE EL BANCO INGRESADO")));
    }

    @Override
    public Flux<AccountsRequest> allAccountsInTime(String bankId, int days) {
        return WebClient.create()
                .get()
                .uri("http://localhost:8010/api/bankAccounts/bank/" + bankId + "/days/" + days)
                .retrieve()
                .bodyToFlux(AccountsRequest.class)
                .concatWith(
                        WebClient.create()
                                .get()
                                .uri("http://localhost:8020/api/creditAccounts/bank/" + bankId + "/days/" + days)
                                .retrieve()
                                .bodyToFlux(AccountsRequest.class));


    }

    @Override
    public Mono<Bank> transfereByBank(String id, TransferenceRequest transferenceRequest) {
        return bankRepository.findById(transferenceRequest.getIdBank()).flatMap(bank1 -> {
            Double transferenceFinal = transferenceRequest.getTransferenceAmount();
            if (bank1.getTransactionLeft() <= 0) {
                transferenceRequest.setTransferenceAmount(transferenceFinal - bank1.getComision());
                System.out.println("DESCONTADO");
            }
            sendBank(id, transferenceRequest).subscribe();
            return updateTransaction(transferenceRequest.getIdBank());
        });

    }

    private Mono<TransferenceRequest> sendBank(String id, TransferenceRequest transferenceRequest) {
        return WebClient.create()
                .put()
                .uri("http://localhost:8010/api/bankAccounts/transference/" + id)
                .body(Mono.just(transferenceRequest), TransferenceRequest.class)
                .retrieve()
                .bodyToMono(TransferenceRequest.class);
    }

    @Override
    public Mono<Bank> transfereByCredit(String id, TransferenceRequest transferenceRequest) {
        return bankRepository.findById(transferenceRequest.getIdBank()).flatMap(bank1 -> {
            Double transferenceFinal = transferenceRequest.getTransferenceAmount();
            if (bank1.getTransactionLeft() <= 0) {
                transferenceRequest.setTransferenceAmount(transferenceFinal + bank1.getComision());
                System.out.println("DESCONTADO");
            }
            sendCredit(id, transferenceRequest).subscribe();
            return updateTransaction(transferenceRequest.getIdBank());
        });

    }

    private Mono<TransferenceRequest> sendCredit(String id, TransferenceRequest transferenceRequest) {
        return WebClient.create()
                .put()
                .uri("http://localhost:8020/api/creditAccounts/transference/" + id)
                .body(Mono.just(transferenceRequest), TransferenceRequest.class)
                .retrieve()
                .bodyToMono(TransferenceRequest.class);
    }


    //TODO Validar cancelar todo si error
    @Override
    public Mono<Bank> payCredit(String id, CreditPaymentRequest creditPaymentRequest) {
        try {
        return bankRepository.findById(creditPaymentRequest.getIdBank()).flatMap(bank1 -> {
            Double transferenceFinal = creditPaymentRequest.getAmmount();
            if (bank1.getTransactionLeft() <= 0) {
                creditPaymentRequest.setAmmount(transferenceFinal - bank1.getComision());
                System.out.println("DESCONTADO");
            }
            sendCreditPayment(id, creditPaymentRequest).subscribe();
            return updateTransaction(creditPaymentRequest.getIdBank());
        });
        }catch (Exception e){
            return Mono.error(e);
        }
    }
    private Mono<CreditPaymentRequest> sendCreditPayment(String id, CreditPaymentRequest creditPaymentRequest) {
        try {
        return WebClient.create()
                .put()
                .uri("http://localhost:8010/api/bankAccounts/toPayCredit/" + id)
                .body(Mono.just(creditPaymentRequest), CreditPaymentRequest.class)
                .retrieve()
                .bodyToMono(CreditPaymentRequest.class);
        }catch (Exception e){
            return Mono.error(e);
        }
    }

    private Mono<Bank> updateTransaction(String id) {
        return bankRepository.findById(id).flatMap(bank -> {
            bank.setName(bank.getName());
            bank.setClientProfiles(bank.getClientProfiles());
            bank.setTransactionLeft(bank.getTransactionLeft()- 1);
            bank.setComision(bank.getComision());
            return bankRepository.save(bank);
        });
}
}
