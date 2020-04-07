package everis.bootcamp.bankMicroservice.ServiceDTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferenceRequest {
    private double transferenceAmount;
    private String idBank;
}
