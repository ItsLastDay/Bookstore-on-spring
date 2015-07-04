package springapp.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springapp.library.dao.GenDAO;
import springapp.service.AuthorService;
 
@Controller
@Transactional(propagation = Propagation.REQUIRED)
public class AuthorsController {
    private GenDAO genDao;
    private AuthorService authorService;
    
    @Autowired
    public void setGenDao(GenDAO genDao) {
    	this.genDao = genDao;
    }
    
    @Autowired
    public void setAuthorService(AuthorService as) {
    	this.authorService = as;
    }
    
    @RequestMapping(value="/authors/{query}", method=RequestMethod.GET)
	public String firstPage(@PathVariable(value="query") String letter, Model model) {
    	if(!letter.equals("all"))
    		model.addAttribute("letter", letter);
    	List authors = authorService.getAuthorsByLetter(letter);
    	model.addAttribute("authors", authors);
    	return "authors";
	}
    
}