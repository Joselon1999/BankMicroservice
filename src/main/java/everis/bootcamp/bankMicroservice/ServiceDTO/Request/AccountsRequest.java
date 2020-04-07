package everis.bootcamp.bankMicroservice.ServiceDTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountsRequest {

    private String id;
    private String serialNumber;
    private BankAccountType bankAccountType;
    private CreditAccountType creditAccountType;
    private String clientId;
    private String dni;
    private String bankId;
    private double ammount;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private class BankAccountType{
        private String id;
        private String name;
        private double minCreationAmmount;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private class CreditAccountType{
        private String id;
        private String name;
    }
}
