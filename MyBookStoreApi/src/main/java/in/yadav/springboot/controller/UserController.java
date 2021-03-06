package in.yadav.springboot.controller;

import java.util.List;

import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.yadav.springboot.dto.UserDto;
import in.yadav.springboot.model.Login;
import in.yadav.springboot.model.PasswordUpdate;
import in.yadav.springboot.model.User;
import in.yadav.springboot.responses.Response;
import in.yadav.springboot.responses.UsersDetail;
import in.yadav.springboot.service.IUserServices;
import in.yadav.springboot.util.JwtGenerator;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
@CrossOrigin(allowedHeaders = "*", origins = "*", exposedHeaders = { "jwtToken" }, maxAge = 3600)
public class UserController {
	@Autowired
	private IUserServices userService;
	@Autowired
	private JwtGenerator generate;

	@PostMapping("/registration")
	public ResponseEntity<Response> registration(@RequestBody UserDto information) {
		// System.out.println("email in controller" + information.getEmail());
		boolean result = userService.register(information);
		if (result) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Response("registration successfull", 201, information));

		} else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
					.body(new Response("user already exist", 208, information));
		}
	}

	@GetMapping("/verify/{token}")
	public ResponseEntity<Response> userVerification(@PathVariable("token") String token) throws Exception {
		// log.info("token for verification" + token);

		boolean update = userService.verify(token);
		if (update) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("verified", 202, token));
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("not verified", 202, token));
		}
	}

	@PostMapping("/login")
	public ResponseEntity<UsersDetail> login(@RequestBody Login information) {

		User userInformation = userService.login(information);
		if (userInformation != null) {
			String token = generate.jwtToken(userInformation.getUserId());
			System.out.println(token);
			return ResponseEntity.status(HttpStatus.ACCEPTED).header("login successfull", information.getUsername())
					.body(new UsersDetail(token, 200, information));
		} else {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new UsersDetail("Login failed", 400, information));
		}
	}

	@PostMapping("/forgotpassword")
	public ResponseEntity<Response> forgotPassword(@RequestBody Email email) {
		// log.info(email.getEmail());
		boolean result = userService.isUserExist(((UserDto) email).getEmail());
		if (result) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("user exist", 202, email));
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new Response("user does not exist with given email id", 202, email));
		}

	}

	@PutMapping("/update/{token}")
	public ResponseEntity<Response> update(@PathVariable("token") String token, @RequestBody PasswordUpdate update) {
		// log.info("inside controller " + update.getConfirmPassword());
		// log.info("inside controller " + token);
		boolean result = userService.update(update, token);

		if (result) {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new Response("password updated successfully", 202, update));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response("passwordhttp://192.168.1.189:9050 doesn't match", 400, update));
		}
	}

	@GetMapping("/getusers")
	public ResponseEntity<Response> getUsers() {
		List<User> users = userService.getUsers();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("The user's are", 202, users));
	}

	@GetMapping("/getOneUser")
	public ResponseEntity<Response> getOneUsers(@RequestHeader("token") String token) {
		User user = userService.getSingleUser(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("user is", 202, user));
	}
}