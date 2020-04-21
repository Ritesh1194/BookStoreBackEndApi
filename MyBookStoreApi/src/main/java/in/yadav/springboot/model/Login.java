package in.yadav.springboot.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class Login {
	@Email
	String username;
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\." + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
			+ "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
	String password;
}