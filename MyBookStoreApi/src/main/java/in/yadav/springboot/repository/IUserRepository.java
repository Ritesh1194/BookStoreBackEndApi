package in.yadav.springboot.repository;

import java.util.List;
import in.yadav.springboot.model.PasswordUpdate;
import in.yadav.springboot.model.User;

public interface IUserRepository {

	public User save(User userInformation);

	public User getUser(String email);

	boolean verify(Long id);

	boolean upDate(PasswordUpdate information, Long token);

	User getUserById(Long id);

	List<User> getUsers();
}