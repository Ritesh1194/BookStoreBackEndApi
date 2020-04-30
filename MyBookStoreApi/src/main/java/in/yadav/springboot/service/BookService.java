package in.yadav.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import in.yadav.springboot.model.Book;

@Service
public interface BookService {

	List<Book> findAllBooks();

	Optional<Book> findBookById(Long bookId);

	Book updateBook(Book book);

	void deleteBook(Book book);

	Book saveBook(Book book);
}