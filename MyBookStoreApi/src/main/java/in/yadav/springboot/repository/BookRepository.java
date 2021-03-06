package in.yadav.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.yadav.springboot.model.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}