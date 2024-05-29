package  com.capstone.petcare2.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Request {
  
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    
}
