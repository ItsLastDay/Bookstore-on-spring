package springapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springapp.library.classes.Author;
import springapp.library.classes.Book;
import springapp.library.classes.BookDTO;
import springapp.library.classes.Bookface;
import springapp.library.classes.Genre;
import springapp.library.classes.Order;
import springapp.library.classes.Publisher;
import springapp.library.classes.SearchForm;
import springapp.library.dao.GenDAO;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;

@Repository("bookService")
public class BookService {
    private GenDAO genDao;

	@Autowired
    public void setGenDao(GenDAO genDao) {
    	this.genDao = genDao;
    }
	
	public Book getBookById(Integer id) {
		return (Book) genDao.searchUnique(new Search(Book.class).addFilterEqual("id", id));
	}
	
	public BookDTO populateDTO(Book b) {
		BookDTO ret = new BookDTO();
		ret.setBookface(b.getBookface());
		ret.setDescription(b.getDescription());
		ret.setNumPages(b.getNumPages());
		ret.setPrice(b.getPrice());
		ret.setPublisher(b.getPublisher());
		ret.setQuantity(b.getQuantity());
		ret.setTitle(b.getTitle());
		ret.setYear(b.getYear());
		return ret;
	}
	
	public Book updateBookFromDTO(Book ret, BookDTO b) {
		ret.setBookface((Bookface) genDao.searchUnique(new Search(Bookface.class).addFilterEqual("type", 
				b.getBookface().getType())));
		ret.setDescription(b.getDescription());
		ret.setNumPages(b.getNumPages());
		ret.setPrice(b.getPrice());
		ret.setPublisher((Publisher) genDao.searchUnique(new Search(Publisher.class).addFilterEqual("id", 
				b.getPublisher().getId())));
		ret.setQuantity(b.getQuantity());
		ret.setTitle(b.getTitle());
		ret.setYear(b.getYear());
		return ret;
	}
	
	public List getRepublishedBooks(Book b) {
    	Search s = new Search(Book.class);
    	
    	s.addFilterEqual("title", b.getTitle());
    	s.addFilterAll("authors", Filter.in(Filter.ROOT_ENTITY, b.getAuthors()));
    	
    	return genDao.search(s);
    }
    
	public List getAlsoBooks(Book b) {
    	Search s = new Search(Order.class);
    	
    	List<Order> orders = genDao.search(s);
    	Set<Book> ret = new HashSet<Book>();
    	for(Order o: orders) {
    		if(o.getBooks().containsKey(b))
    			ret.addAll(o.getBooks().keySet());
    		if(ret.size() > 5)
    			break;
    	}
    	ret.remove(b);
    	List t = new ArrayList();
    	t.addAll(ret);
    	return t;
    }
	
	public List getBooksBySearch(SearchForm search) {
    	Search s = new Search(Book.class);
    	
    	if(search.getPresented().equals(new Boolean(true))) {
    		s.addFilterGreaterThan("quantity", new Integer(0));
    	}
    	
    	if(search.getTitle() != null) {
    		s.addFilterLike("title", search.getTitle().concat("%"));
    	}
    	
    	if(search.getGenreId() != null) {
    		s.addFilterSome("genres", Filter.equal("id", search.getGenreId()));
    	}
    	
    	if(search.getOrder().equals("price")) {
    		s.addSort("price", true);
    	} else if(search.getOrder().equals("new")) {
    		s.addSort("year", true);
    	}
    	
    	if(search.getPublisherId() != null) {
    		s.addFilterEqual("publisher.id", search.getPublisherId());
    	} 
    	
    	if(search.getBookface() != null && !search.getBookface().equals("")) {
    		s.addFilterEqual("bookface.type", search.getBookface());
    	}
   
    	if(search.getYear() != null) {
    		s.addFilterEqual("year", search.getYear());
    	}
    	
    	s.setMaxResults(search.getNumberOfBooks());
    	if(search.getPage() == null)
    		search.setPage(new Integer(0));
    	s.setPage(search.getPage());
    	return genDao.search(s);
    }
	
	public Integer countBooksBySearch(SearchForm search) {
    	Search s = new Search(Book.class);
    	
    	if(search.getPresented().equals(new Boolean(true))) {
    		s.addFilterGreaterThan("quantity", new Integer(0));
    	}
    	
    	if(search.getTitle() != null) {
    		s.addFilterLike("title", search.getTitle().concat("%"));
    	}
    	
    	if(search.getGenreId() != null) {
    		s.addFilterSome("genres", Filter.equal("id", search.getGenreId()));
    	}
    	
    	if(search.getOrder().equals("price")) {
    		s.addSort("price", true);
    	} else if(search.getOrder().equals("new")) {
    		s.addSort("year", true);
    	}
    	
    	if(search.getPublisherId() != null) {
    		s.addFilterEqual("publisher.id", search.getPublisherId());
    	} 
    	
    	if(search.getBookface() != null && !search.getBookface().equals("")) {
    		s.addFilterEqual("bookface.type", search.getBookface());
    	}
   
    	if(search.getYear() != null) {
    		s.addFilterEqual("year", search.getYear());
    	}
    	
    	s.setMaxResults(search.getNumberOfBooks());
    	if(search.getPage() == null)
    		search.setPage(new Integer(0));
    	s.setPage(search.getPage());
    	return genDao.count(s);
    }

	public void removeBookById(Integer id) {
		Book b = (Book) genDao.searchUnique(new Search(Book.class).addFilterEqual("id", id));
    	for(Genre g: b.getGenres()) {
    		g.getBooks().remove(b);
    	}
    	for(Author a: b.getAuthors()) {
    		a.getBooks().remove(b);
    	}
    	genDao.deleteById(Book.class, id);
	}

	public Book getDTOLikeBook(Integer id) {
		Book ret = new Book();
		ret.setPrice((Double) genDao.searchUnique(new Search(Book.class).addFilterEqual("id", id)
				.addField("price")));
		ret.setQuantity((Integer) genDao.searchUnique(new Search(Book.class).addFilterEqual("id", id)
				.addField("quantity")));
		ret.setYear((Integer)genDao.searchUnique(new Search(Book.class).addFilterEqual("id", id)
				.addField("year")));
		ret.setDescription((String)genDao.searchUnique(new Search(Book.class).addFilterEqual("id", id)
				.addField("description")));
		ret.setNumPages((Integer)genDao.searchUnique(new Search(Book.class).addFilterEqual("id", id)
				.addField("numPages")));
		ret.setTitle((String)genDao.searchUnique(new Search(Book.class).addFilterEqual("id", id)
				.addField("title")));
		ret.setAuthors(new HashSet<Author>((Collection<? extends Author>) genDao.search(new Search(Book.class).addFilterEqual("id", id)
				.addField("authors").setResultMode(Search.RESULT_LIST))));
		ret.setGenres(new HashSet<Genre>((Collection<? extends Genre>) genDao.search(new Search(Book.class).addFilterEqual("id", id)
				.addField("genres").setResultMode(Search.RESULT_LIST))));
		return ret;
	}

	public void deleteAuthor(Integer bookId, Integer authorId) {
		Author a = (Author) genDao.searchUnique(new Search(Author.class).addFilterEqual("id", authorId));
		Book b = this.getBookById(bookId);
		a.getBooks().remove(b);
		b.getAuthors().remove(a);
	}

	public void addAuthor(Integer bookId, Integer authorId) {
		Author a = (Author) genDao.searchUnique(new Search(Author.class).addFilterEqual("id", authorId));
		Book b = this.getBookById(bookId);
		a.getBooks().add(b);
		b.getAuthors().add(a);
	}
	
	public void deleteGenre(Integer bookId, Integer genreId) {
		Genre a = (Genre) genDao.searchUnique(new Search(Genre.class).addFilterEqual("id", genreId));
		Book b = this.getBookById(bookId);
		a.getBooks().remove(b);
		b.getGenres().remove(a);
	}

	public void addGenre(Integer bookId, Integer genreId) {
		Genre a = (Genre) genDao.searchUnique(new Search(Genre.class).addFilterEqual("id", genreId));
		Book b = this.getBookById(bookId);
		a.getBooks().add(b);
		b.getGenres().add(a);
	}
}
