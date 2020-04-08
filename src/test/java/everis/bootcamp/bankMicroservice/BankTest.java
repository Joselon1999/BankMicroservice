package everis.bootcamp.bankMicroservice;

import everis.bootcamp.bankMicroservice.Document.Bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootTest
class BankTest {

    @Test
    void bankTest(){
        Bank bank1 = new Bank("1","BBVA",5,(double) 100, new HashSet<>(Arrays.asList("a", "b", "c")));
        Bank bank = new Bank();
        bank.setId("1");
        bank.setName("BBVA");
        bank.setClientProfiles(new HashSet<>(Arrays.asList("a", "b", "c")));
        bank.setComision((double) 100);
        bank.setTransactionLeft(5);
        Assertions.assertEquals("1", bank.getId());
        Assertions.assertEquals("BBVA", bank.getName());
        Assertions.assertEquals(new HashSet<>(Arrays.asList("a", "b", "c")), bank.getClientProfiles());
        Assertions.assertEquals((double) 100, bank.getComision());
        Assertions.assertEquals(5, bank.getTransactionLeft());
        Assertions.assertEquals(bank,bank);


    }

}
