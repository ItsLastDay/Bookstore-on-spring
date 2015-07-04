package springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springapp.library.classes.Author;
import springapp.library.dao.GenDAO;

import com.googlecode.genericdao.search.Search;

@Repository("authorService")
public class AuthorService {
	private GenDAO genDao;
    
    @Autowired
    public void setGenDao(GenDAO genDao) {
    	this.genDao = genDao;
    }
    
    public List getAuthorsByLetter(String query) {
    	Search s = new Search(Author.class);
    	
    	if(!query.equals("all"))
    		s.addFilterLike("lastName", query + "%");
    	
    	s.addSort("lastName", false);
    	s.addSort("firstName", false);
    	
    	return genDao.search(s);	
    }
}
