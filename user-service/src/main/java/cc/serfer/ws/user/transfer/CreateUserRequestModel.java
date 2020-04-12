
package cc.serfer.ws.user.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateUserRequestModel {

    @NotNull(message = "First name cannot be null")
    @Size(min = 2, message = "First name must not be less that two characters ")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, message = "Last name must not be less that two characters ")
    private String lastName;

    @NotNull(message = "Password cannot be null")
    @Size(min = 6, max = 16,message = "Password must be equal or grate that 8 characters and less then 16 characters ")
    private String password;

    @NotNull(message = "Email cannot be null")
    @Email
    private String email;

}
