package maciejsosnowski.booksranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookRepository booksRepository;
    @GetMapping("/books")
    public List<Book> getAll(){
        return booksRepository.getAll();
    }
    @GetMapping("/books/{id}")
    public Book getById(@PathVariable("id") int id){
        return booksRepository.getById(id);
    }
    @PostMapping("/books")
    public int add(@RequestBody List<Book> books){
        return booksRepository.add(books);
    }
    @PutMapping("/books/{id}")
    public int Update(@PathVariable("id") int id, @RequestBody Book updatedBook){
        Book book = booksRepository.getById(id);
        if(book != null){
            book.setName(updatedBook.getName());
            book.setRating(updatedBook.getRating());
            booksRepository.update(book);
            return 1;
        }else{
            return -1;
        }
    }
    @PatchMapping("/books/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Book updatedBook) {
        Book book = booksRepository.getById(id);
        if(book != null){
            if(updatedBook.getName() != null) book.setName(updatedBook.getName());
            if(updatedBook.getRating() > 0) book.setRating(updatedBook.getRating());
            booksRepository.update(book);
            return 1;
        }else{
            return -1;
        }
    }
    @DeleteMapping("/books/{id}")
    public int delete(@PathVariable("id") int id){
        return booksRepository.delete(id);
    }
}
