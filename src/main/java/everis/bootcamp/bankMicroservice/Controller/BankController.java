package everis.bootcamp.bankMicroservice.Controller;

import everis.bootcamp.bankMicroservice.Document.Bank;
import everis.bootcamp.bankMicroservice.Service.BankService;
import everis.bootcamp.bankMicroservice.ServiceDTO.Request.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/bank")
public class BankController {

    @Autowired
    BankService bankService;

    @ApiOperation(value = "Creates new accounts",
            notes = "Requires a BankRequest Params - Which are the same as  the bank Params" +
                    "excluding the ID")
    @PostMapping(value = "")
    public Mono<Bank> createBank(@Valid @RequestBody BankRequest bankRequest){
        return bankService.create(bankRequest);
    }
    /*UPDATE*/
    @ApiOperation(value = "Creates new accounts",
            notes = "Requires bankId and BankRequest Params ")
    @PutMapping(value = "/{bankId}")
    public Mono<Bank> updateBank(@PathVariable("bankId") String bankId,
                                                     @Valid @RequestBody BankRequest bankRequest) {
        return bankService.update(bankId,bankRequest);
    }
    /*READ*/
    @ApiOperation(value = "Gets all Banks created")
    @GetMapping
    public Flux<Bank> listBanks(){
        return bankService.readAll();
    }
    /*DELETE*/
    @ApiOperation(value = "Deletes a bank accounts",
            notes = "Requires Bank ID")
    @DeleteMapping(value = "/{bankId}")
    public Mono<Bank> deleteBank(@PathVariable(value = "bankId") String bankId){
        return bankService.delete(bankId);
    }
    /*READ BANK ACCOUNT*/
    @ApiOperation(value = "Gets all Banks ACC created")
    @GetMapping(value = "/BankAcc/{id}")
    public Flux<AccountsRequest> listBankAccounts(@PathVariable("id") String id){
        return bankService.allAccountsBankAccount(id);
    }
    /*READ CREDIT ACCOUNT*/
    @ApiOperation(value = "Gets all Credit ACC created")
    @GetMapping(value = "/CreditAcc/{id}")
    public Flux<AccountsRequest> listCreditAccounts(@PathVariable("id") String id){
        return bankService.allAccountsCreditAccount(id);
    }
    /*READ ALL ACCOUNT*/
    @ApiOperation(value = "Gets all ACC created")
    @GetMapping(value = "/AllAcc/{id}")
    public Flux<AccountsRequest> listAllAccounts(@PathVariable("id") String id){
        return bankService.allAccounts(id);
    }
    /*READ CLIENT PROFILES*/
    @ApiOperation(value = "Gets all  profiles")
    @GetMapping(value = "/profiles/{id}")
    public Mono<ClientProfilesRequest> listClientProfiles(@PathVariable("id") String id){
        return bankService.getClientProfiles(id);
    }
    /*READ ALL ACCOUNT*/
    @ApiOperation(value = "Gets all Credit ACC created by BankId")
    @GetMapping(value = "/allAccInBank/{id}/days/{days}")
    public Flux<AccountsRequest> listAllByBank(@PathVariable("id") String id,
                                               @PathVariable(value = "days") int days) {
        return bankService.allAccountsInTime(id,days);
    }
    /*TRANSFERENCES*/
    @ApiOperation(value = "REGISTER TRANSFERENCE OF MONEY",
            notes = "Requires BANKACCONTRANSFERENCE and will update and create an entity")
    @PutMapping(value = "/transferenceToBankAccount/{id}")
    public Mono<Bank> transferenceBankAccount(@PathVariable(value = "id") String id,
                                                             @Valid @RequestBody TransferenceRequest transferenceRequest) {
        return bankService.transfereByBank(id, transferenceRequest);
    }
    /*TRANSFERENCES*/
    @ApiOperation(value = "REGISTER TRANSFERENCE OF MONEY",
            notes = "Requires BANKACCONTRANSFERENCE and will update and create an entity")
    @PutMapping(value = "/transferenceToCreditAccount/{id}")
    public Mono<Bank> transferenceCreditAccount(@PathVariable(value = "id") String id,
                                              @Valid @RequestBody TransferenceRequest transferenceRequest) {
        return bankService.transfereByCredit(id, transferenceRequest);
    }
    /*TRANSFERENCE TO CREDITPAYMENT VIA BANK*/
    @ApiOperation(value = "REGISTER TRANSFERENCE OF MONEY TO CREDIT MICROSERVICE",
            notes = "Requires ID AND CREDITPAYMENTREQUEST and will update and create an entity")
    @PutMapping(value = "/toPayCredit/{id}")
    public Mono<Bank> transferenceCreditAccount(@PathVariable(value = "id") String id,
                                                @Valid @RequestBody CreditPaymentRequest creditPaymentRequest) {
        return bankService.payCredit(id, creditPaymentRequest);
    }
}
