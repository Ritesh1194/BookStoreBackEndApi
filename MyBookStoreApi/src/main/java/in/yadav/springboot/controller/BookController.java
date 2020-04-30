package in.yadav.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.yadav.springboot.model.Book;
import in.yadav.springboot.service.BookService;

@RestController
@RequestMapping(path = "books")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

	private BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	
	@PostMapping("/createBook")
	// @Secured(SecurityConstants.ADMIN)
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		if (book.getBookId() != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Book resultBook = bookService.saveBook(book);
		return new ResponseEntity<>(resultBook, HttpStatus.CREATED);
	}

	@PutMapping("/updateBook")
	// @Secured(SecurityConstants.ADMIN)
	public ResponseEntity<Book> updateBook(@RequestBody Book book) {
		if (book.getBookId() == null) {
			return createBook(book);
		}
		Book resultBook = bookService.updateBook(book);
		return new ResponseEntity<>(resultBook, HttpStatus.OK);
	}

	@GetMapping("/getAllBooks")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> books = bookService.findAllBooks();
		if (books.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	
//    @GetMapping("/books/{id}")
//    public ResponseEntity<Book> getBook(@PathVariable Long id) {
//        Book book = bookService.findBookById(id);
//        if (book == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(book, HttpStatus.OK);
//    }

//    @DeleteMapping("/books/{id}")
//    @Secured(SecurityConstants.ADMIN)
//    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
//        Book book = bookService.findBookById(id);
//        if (book == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        bookService.deleteBook(book);
//        return new ResponseEntity<>(book, HttpStatus.OK);
//    }
}