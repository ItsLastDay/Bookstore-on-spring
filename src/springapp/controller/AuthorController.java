package springapp.controller;
 
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import springapp.library.classes.Author;
import springapp.library.dao.GenDAO;

import com.googlecode.genericdao.search.Search;
 
@Controller
@Transactional(propagation = Propagation.REQUIRED)
public class AuthorController {
    private GenDAO genDao;
    
    @Autowired
    public void setGenDao(GenDAO genDao) {
    	this.genDao = genDao;
    }
    
    @RequestMapping(value="/author/{id}", method=RequestMethod.GET)
	public String authorPage(@PathVariable(value="id") Integer id, Model model) {
    	Author b = (Author) genDao.searchUnique(new Search(Author.class).addFilterEqual("id", id));
    	model.addAttribute("author", b);
    	return "author";
	}
    
    @RequestMapping(value="/author/{id}/books")
	public String repubPage(@PathVariable(value="id") Integer id, Model model) {
    	Author b = (Author) genDao.searchUnique(new Search(Author.class).addFilterEqual("id", id));
    	model.addAttribute("allBooks", b.getBooks());
    	return "author";
	}
    
    
    @RequestMapping(value="/author/new", method=RequestMethod.GET)
    public String newAuthorPage(Model m) {
    	if(!m.containsAttribute("author"))
    		m.addAttribute("author", new Author());
    	return "author-new";
    }
    
    @RequestMapping(value="/author/new", method=RequestMethod.POST)
    public String newAuthorDone(@Valid Author author, BindingResult bind, RedirectAttributes ra) {
    	if(bind.hasErrors()) {
    		ra.addFlashAttribute("author", author);
    		ra.addFlashAttribute("org.springframework.validation.BindingResult.author", bind);
    		return "redirect:/author/new";
    	}
    	genDao.createOrUpdate(author);
    	ra.addFlashAttribute("message", "Автор успешно добавлен!");
    	return "redirect:/home";
    }
}