package everis.bootcamp.bankMicroservice.ServiceDTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditPaymentRequest {
    private Double ammount;
    private String idCreditAccount;
    private String idBank;
}
