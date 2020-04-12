
package cc.serfer.ws.user.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateUserResponseModel {

    private String userId;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

}
