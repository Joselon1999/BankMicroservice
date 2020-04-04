package everis.bootcamp.bankMicroservice.Document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Document(value = "bank")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Bank {

    @Id
    private String id;
    @NotBlank(message = "'name' can't be blank")
    private String name;
    @NotBlank(message = "'name' can't be blank")
    private int transactionLeft;
    private Set<String> clientProfiles;
}
