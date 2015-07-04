package springapp.controller;
 
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import springapp.library.classes.Author;
import springapp.library.classes.AuthorMtMForm;
import springapp.library.classes.Book;
import springapp.library.classes.BookDTO;
import springapp.library.classes.Bookface;
import springapp.library.classes.Genre;
import springapp.library.classes.GenreMtMForm;
import springapp.library.classes.Publisher;
import springapp.library.dao.GenDAO;
import springapp.service.BookService;

import com.googlecode.genericdao.search.Search;
 
@Controller
@Transactional(propagation = Propagation.REQUIRED)
public class BookController {
	private GenDAO genDao;
    private BookService bookService;
    
    @Autowired
    public void setBookService(BookService bs) {
    	this.bookService = bs;
    }
    
    @Autowired
    public void setGenDao(GenDAO genDao) {
    	this.genDao = genDao;
    }
        
    @RequestMapping(value="/book/{id}", method=RequestMethod.GET)
	public String firstPage(@PathVariable(value="id") Integer id, Model model) {
    	Book b = bookService.getBookById(id);
    	model.addAttribute("book", b);
    	model.addAttribute("alsoBooks", bookService.getAlsoBooks(b));
    	return "book";
	}
    
    @RequestMapping(value="/book/{id}/edit", method=RequestMethod.GET)
	public String editPage(@PathVariable(value="id") Integer id, Model model) {
    	Book original = bookService.getBookById(id);
    	BookDTO b = bookService.populateDTO(original);
    	if(!model.containsAttribute("book"))
    		model.addAttribute("book", b);
    	model.addAttribute("bookId", id);
    	model.addAttribute("bookAuthors", original.getAuthors());
    	model.addAttribute("bookGenres", original.getGenres());
    	model.addAttribute("publishers", genDao.search(new Search(Publisher.class).addSort("name", false)));
    	model.addAttribute("authors", genDao.search(new Search(Author.class).addSort("lastName", false).
    			addSort("firstName", false)));
    	model.addAttribute("genres", genDao.search(new Search(Genre.class).addSort("name", false)));
    	model.addAttribute("bookfaces", genDao.search(new Search(Bookface.class)));
    	original = null;
    	return "book-edit";
	}
    
    @RequestMapping(value="/book/{id}/edit", method=RequestMethod.POST)
	public String editPageSubmit(@PathVariable(value="id") Integer id,
			@ModelAttribute("book") @Valid BookDTO book, BindingResult bind, 
			Model m, RedirectAttributes ra) {
    	if(bind.hasErrors()) {
    		ra.addFlashAttribute("book", book);
    		ra.addFlashAttribute("org.springframework.validation.BindingResult.book", bind);
    		return "redirect:/book/" + id.toString() + "/edit";
    	}
    	Book original = bookService.getBookById(id);
    	original = bookService.updateBookFromDTO(original, book);
    	genDao.update(original);
    	return "redirect:/book/" + id.toString();
	}
    
    @RequestMapping(value="/book/{id}/repub")
	public String repubPage(@PathVariable(value="id") Integer id, Model model) {
    	Book b = bookService.getBookById(id);
    	model.addAttribute("repubBooks", bookService.getRepublishedBooks(b));
    	return "book";
	}
    
    @RequestMapping(value="/book/{id}/delete")
	public String deletePage(@PathVariable(value="id") Integer id, RedirectAttributes ra) {
    	ra.addFlashAttribute("message", "Книга успешно удалена");
    	bookService.removeBookById(id);
    	return "redirect:/home";
	}
    
    @RequestMapping(value="/book/{id}/addAuthor", method=RequestMethod.GET)
	public String addAuthorPage(@PathVariable(value="id") Integer id, Model m) {
    	m.addAttribute("authors", genDao.search(new Search(Author.class).addSort("lastName", false).
    			addSort("firstName", false)));
    	m.addAttribute("authorSearcher", new AuthorMtMForm());
    	return "book-author-add";
	}
    
    @RequestMapping(value="/book/{id}/addAuthor", method=RequestMethod.POST)
	public String addAuthorDone(@PathVariable(value="id") Integer id, @Valid AuthorMtMForm authorSearcher,
			BindingResult bind, RedirectAttributes ra) {
    	if(bind.hasErrors()) {
    		ra.addFlashAttribute("org.springframework.validation.BindingResult.authorSearcher", bind);
    		return "redirect:/book/" + id.toString() + "/delete";
    	}
    	if(authorSearcher.isDelete()) 
    		bookService.deleteAuthor(id, authorSearcher.getAuthorId());
    	else 
    		bookService.addAuthor(id, authorSearcher.getAuthorId());
    	return "redirect:/book/" + id.toString() + "/edit";
	}
    
    @RequestMapping(value="/book/{id}/addGenre", method=RequestMethod.GET)
	public String addGenrePage(@PathVariable(value="id") Integer id, Model m) {
    	m.addAttribute("genres", genDao.search(new Search(Genre.class).addSort("name", false)));
    	m.addAttribute("genreSearcher", new GenreMtMForm());
    	return "book-genre-add";
	}
    
    @RequestMapping(value="/book/{id}/addGenre", method=RequestMethod.POST)
	public String addGenreDone(@PathVariable(value="id") Integer id, @Valid GenreMtMForm authorSearcher,
			BindingResult bind, RedirectAttributes ra) {
    	if(bind.hasErrors()) {
    		ra.addFlashAttribute("org.springframework.validation.BindingResult.authorSearcher", bind);
    		return "redirect:/book/" + id.toString() + "/delete";
    	}
    	if(authorSearcher.isDelete()) 
    		bookService.deleteGenre(id, authorSearcher.getGenreId());
    	else 
    		bookService.addGenre(id, authorSearcher.getGenreId());
    	return "redirect:/book/" + id.toString() + "/edit";
	}
    
    @RequestMapping(value="/book/new", method=RequestMethod.GET)
	public String addBookPage(Model m) {
    	m.addAttribute("publishers", genDao.search(new Search(Publisher.class).addSort("name", false)));
    	m.addAttribute("bookfaces", genDao.search(new Search(Bookface.class)));
    	if(!m.containsAttribute("book"))
    		m.addAttribute("book", new Book());
    	return "book-new";
	}
    
    @RequestMapping(value="/book/new", method=RequestMethod.POST)
	public String addBookDone(@Valid Book book, BindingResult bind, RedirectAttributes ra) {
    	if(bind.hasErrors()) {
    		ra.addFlashAttribute("book", book);
    		ra.addFlashAttribute("org.springframework.validation.BindingResult.book", bind);
    		return "redirect:/book/new";
    	}
    	genDao.refresh(book.getPublisher());
    	genDao.createOrUpdate(book);
    	return "redirect:/book/" + book.getId().toString();
	}
}