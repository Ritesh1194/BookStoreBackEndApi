package in.yadav.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.yadav.springboot.model.User;
import in.yadav.springboot.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "users")
public class UserController {
	@Autowired
	UserRepository userRepository;

	@GetMapping(path = "/getUsers")
	public List<User> getUsers() {
		System.out.println("Get Users Controller");
		return userRepository.findAll();
	}

	@PostMapping(path = "/addUsers")
	public void createUser(@RequestBody User user) {
		userRepository.save(user);
	}

	@DeleteMapping(path = "/{id}")
	public User deleteUser(@PathVariable("id") long id) {
		User user = userRepository.getOne(id);
		userRepository.deleteById(id);
		return user;
	}
}