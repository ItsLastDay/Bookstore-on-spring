package springapp.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springapp.library.classes.Client;
import springapp.library.dao.GenDAO;
import springapp.service.AuthorService;

import com.googlecode.genericdao.search.Search;
 
@Controller
@Transactional(propagation = Propagation.REQUIRED)
public class ClientsController {
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
    
    @RequestMapping(value="/clients/all", method=RequestMethod.GET)
	public String firstPage(Model model) {
    	model.addAttribute("clients", genDao.search(new Search(Client.class).addSort("login", false)));
    	return "clients";
	}
    
}