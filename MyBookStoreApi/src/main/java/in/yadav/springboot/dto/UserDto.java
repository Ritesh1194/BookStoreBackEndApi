package in.yadav.springboot.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDto implements Serializable {
	private static final long serialVersionUID = 2999449587418137835L;

	@NotNull
	@Size(min = 1, max = 16)
	private String firstname;
	@NotNull
	@Size(min = 1, max = 16)
	private String lastname;
	@Email
	@NotEmpty
	private String email;
	@NotEmpty
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\." + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
			+ "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
	private String password;
	@Size(min = 10, max = 10)
	private Long mobileNumber;
}