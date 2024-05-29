package  com.capstone.petcare2.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;  
}
