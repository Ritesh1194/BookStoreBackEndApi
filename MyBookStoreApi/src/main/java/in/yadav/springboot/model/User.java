package in.yadav.springboot.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Entity
@Table(name = "User")
@Component
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;

	private String firstname;

	private String lastname;

	private String email;

	private Long mobileNumber;

	private String password;

	private LocalDateTime createdDate;

	private boolean isVerified;

	private boolean isUser;
}