package everis.bootcamp.bankMicroservice.Controller;

import everis.bootcamp.bankMicroservice.Document.Bank;
import everis.bootcamp.bankMicroservice.Service.BankService;
import everis.bootcamp.bankMicroservice.ServiceDTO.Request.BankRequest;
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
}
