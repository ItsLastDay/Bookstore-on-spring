package springapp.controller;
 
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import springapp.library.classes.Book;
import springapp.library.classes.BookMtMForm;
import springapp.library.classes.Client;
import springapp.library.classes.Order;
import springapp.library.classes.OrderStatus;
import springapp.library.dao.GenDAO;
import springapp.service.BookService;

import com.googlecode.genericdao.search.Search;
 
@Controller
@Transactional(propagation = Propagation.REQUIRED)
public class OrderController {
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
    
    @RequestMapping(value="/order/all")
	public String allOrdersPage(Model model) {
    	List orders = genDao.search(new Search(Order.class));
    	model.addAttribute("orders", orders);
    	return "orders";
	}
    
    @RequestMapping(value="/order/new", method=RequestMethod.GET)
    public String newOrderPage(Model model) {
    	if(!model.containsAttribute("order")) {
    		Order o = new Order();
    		o.setClient(genDao.fetch(Client.class, new Integer(1)));
    		o.setStatus((OrderStatus) genDao.fetchAll(OrderStatus.class).get(0));
    		o.setWhenMade(new Date());
    		genDao.createOrUpdate(o);
    		model.addAttribute("orderId", o.getId());
    		model.addAttribute("order", o);
    	}
    	model.addAttribute("clients", genDao.fetchAll(Client.class));
    	return "order-new";
    }
    
    @RequestMapping(value="/order/new", method=RequestMethod.POST)
    public String newOrderDone(@Valid Order order, BindingResult bind, 
    		RedirectAttributes ra, Model model) {
    	if(bind.hasErrors()) {
    		ra.addFlashAttribute("order", order);
    		ra.addFlashAttribute("org.springframework.validation.BindingResult.order", bind);
    		return "redirect:/order/new";
    	}
		order.setStatus((OrderStatus) genDao.fetchAll(OrderStatus.class).get(0));
    	genDao.refresh(order.getClient());
    	genDao.createOrUpdate(order);
    	ra.addFlashAttribute("message", "Заказ успешно добавлен!");
    	return "redirect:/order/all";
    }
    
    @RequestMapping(value="/order/{id}/addBook", method=RequestMethod.GET)
	public String addBookPage(@PathVariable(value="id") Integer id, Model m) {
    	m.addAttribute("books", genDao.search(new Search(Book.class).addSort("title", false)));
    	m.addAttribute("bookSearcher", new BookMtMForm());
    	return "order-book-add";
	}
    
    @RequestMapping(value="/order/{id}/addBook", method=RequestMethod.POST)
	public String addGenreDone(@PathVariable(value="id") Integer id, @Valid BookMtMForm bookSearcher,
			BindingResult bind, RedirectAttributes ra) {
    	if(bind.hasErrors()) {
    		ra.addFlashAttribute("org.springframework.validation.BindingResult.bookSearcher", bind);
    		return "redirect:/order/" + id.toString() + "/addBook";
    	}
    	Order o = genDao.fetch(Order.class, id);
    	if(bookSearcher.isDelete()) 
    		o.getBooks().remove(genDao.fetch(Book.class, bookSearcher.getBookId()));
    	else 
    		o.getBooks().put(genDao.fetch(Book.class,  bookSearcher.getBookId()), 
    				bookSearcher.getQuantity());
    	genDao.update(o);
    	ra.addFlashAttribute("order", o);
    	ra.addFlashAttribute("orderId", o.getId());
    	return "redirect:/order/new";
	}
}