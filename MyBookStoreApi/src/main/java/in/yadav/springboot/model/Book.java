package in.yadav.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="book")
public class Book {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long id;

	@Column(name="name")
	private String name;
	
	@Column(name="author")
	private String author;
	
	@Column(name="price")
	private String price;
	
	@Column(name = "picByte", length = 1000)
	private byte[] picByte;	
}