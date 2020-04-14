package in.yadav.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.yadav.springboot.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}