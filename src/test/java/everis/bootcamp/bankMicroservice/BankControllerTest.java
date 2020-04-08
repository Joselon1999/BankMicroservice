package everis.bootcamp.bankMicroservice;

import everis.bootcamp.bankMicroservice.Controller.BankController;
import everis.bootcamp.bankMicroservice.Document.Bank;
import everis.bootcamp.bankMicroservice.Repository.BankRepository;
import everis.bootcamp.bankMicroservice.Service.BankService;
import everis.bootcamp.bankMicroservice.ServiceDTO.Request.BankRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.Mockito.when;

//@SpringBootTest
@WebFluxTest
class BankControllerTest {

//    @MockBean
//    private BankRepository bankRepository;
//
//    @Autowired
//    private WebTestClient webTestClient;
//
//    //    @Test
//    void createBankTest(){
//        Bank bank = new Bank();
//        bank.setId("1");
//        bank.setName("BBVA");
//        bank.setClientProfiles(new HashSet<>(Arrays.asList("a", "b", "c")));
//        bank.setComision((double) 100);
//        bank.setTransactionLeft(5);
//
//        webTestClient.post()
//                .uri("http://localhost:8100/api/bank")
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(BodyInserters.fromObject(bank))
//                .exchange()
//                .expectStatus().isOk();
//    }
//    @Test
//    void readBankTest() {
//        Bank bank1 = new Bank("1", "BBVA", 5, (double) 10, new HashSet<>());
//        Bank bank2 = new Bank("2", "BCP", 5, (double) 10, new HashSet<>());
//
//
//        when(bankRepository.findAll()).thenReturn(Flux.just(bank1, bank2));
//
//        webTestClient.get()
//                .uri("/api/bank")
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isOk()
//                .expectBodyList(Bank.class)
//                .value(banks -> {
//                    Assertions.assertThat(banks.get(0).getId()).isEqualTo(1);
//                    Assertions.assertThat(banks.get(0).getName()).isEqualTo("BBVA");
//                    Assertions.assertThat(banks.get(0).getTransactionLeft()).isEqualTo((double) 10);
//                    Assertions.assertThat(banks.get(0).getClientProfiles()).isEqualTo(new HashSet<>());
//                    Assertions.assertThat(banks.get(1).getId()).isEqualTo(2);
//                    Assertions.assertThat(banks.get(1).getName()).isEqualTo("BCP");
//                    Assertions.assertThat(banks.get(1).getTransactionLeft()).isEqualTo((double) 10);
//                    Assertions.assertThat(banks.get(1).getClientProfiles()).isEqualTo(new HashSet<>());
//                        }
//                );
//    }
}
