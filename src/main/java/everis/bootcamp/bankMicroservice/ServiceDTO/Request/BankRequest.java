package everis.bootcamp.bankMicroservice.ServiceDTO.Request;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BankRequest {
    private String id;
    private String name;
    private int transactionLeft;
    private Double comision;
    private Set<String> clientProfiles;
}
