package springapp.controller;
 
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import springapp.library.classes.Bookface;
import springapp.library.classes.Genre;
import springapp.library.classes.Publisher;
import springapp.library.classes.SearchForm;
import springapp.library.dao.GenDAO;
import springapp.service.BookService;

import com.googlecode.genericdao.search.Search;
 
@Controller
@SessionAttributes("search")
@Transactional(propagation = Propagation.REQUIRED)
public class MainPageController {
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
    
    @RequestMapping(value="/home", method=RequestMethod.GET)
	public String firstPage(Model model) {
    	SearchForm def;
    	if(!model.containsAttribute("search")) {
    		def = new SearchForm();
        	def.setOrder("price");
        	def.setPresented(true);
        	def.setNumberOfBooks(new Integer(10));
        	def.setPage(new Integer(0));
    		model.addAttribute("search", def);
    	} else {
    		def = (SearchForm) model.asMap().get("search");
    	}
    	List books = bookService.getBooksBySearch(def);
    	model.addAttribute("books", books); 
    	def.setNumPages((bookService.countBooksBySearch(def) + def.getNumberOfBooks() - 1) / def.getNumberOfBooks());
    	model.addAttribute("genres", genDao.search(new Search(Genre.class).addSort("name", false)));
    	model.addAttribute("publishers", genDao.search(new Search(Publisher.class).addSort("name", false)));
    	model.addAttribute("bookfaces", genDao.search(new Search(Bookface.class)));
		return "home";
	}
    
    @RequestMapping(value="/home", method=RequestMethod.POST)
    public String goSearch(@ModelAttribute @Valid SearchForm search, BindingResult bind, RedirectAttributes ra) {
    	ra.addFlashAttribute("search", search);
    	if(bind.hasErrors()) {
    		ra.addFlashAttribute("org.springframework.validation.BindingResult.search", bind);
    	}
    	return "redirect:/home";
    }
}