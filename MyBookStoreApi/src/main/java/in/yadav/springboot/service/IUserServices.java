package in.yadav.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.yadav.springboot.dto.UserDto;
import in.yadav.springboot.model.Login;
import in.yadav.springboot.model.PasswordUpdate;
import in.yadav.springboot.model.User;

@Service
public interface IUserServices {

	public User login(Login information);

	public boolean register(UserDto ionformation);

	public boolean verify(String token) throws Exception;

	public boolean isUserExist(String email);

	public boolean update(PasswordUpdate information, String token);

	public List<User> getUsers();

	public User getSingleUser(String token);

	public User findByUserName(String name);
}