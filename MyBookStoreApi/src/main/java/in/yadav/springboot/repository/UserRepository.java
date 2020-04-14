package in.yadav.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.yadav.springboot.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}