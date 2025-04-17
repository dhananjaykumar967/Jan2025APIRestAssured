package contactAPITests;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NonNull
public class UserPojoClass {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
