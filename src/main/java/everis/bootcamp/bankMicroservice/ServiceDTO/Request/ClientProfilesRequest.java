package everis.bootcamp.bankMicroservice.ServiceDTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientProfilesRequest {
    private Set<String> clientProfiles;
}
