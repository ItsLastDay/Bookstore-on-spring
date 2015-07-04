package springapp.library.test;

import java.io.File;
import java.util.Date;
import java.util.Map;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import springapp.library.classes.Author;
import springapp.library.classes.Book;
import springapp.library.classes.Bookface;
import springapp.library.classes.City;
import springapp.library.classes.Client;
import springapp.library.classes.Genre;
import springapp.library.classes.Order;
import springapp.library.classes.OrderStatus;
import springapp.library.classes.Publisher;
import springapp.library.dao.GenDAO;
import springapp.library.impl.GenDAOImpl;

import com.googlecode.genericdao.search.Search;

public class LogicTest extends Assert {
	private SessionFactory sf;
	private GenDAO generalDao = new GenDAOImpl();
  @BeforeSuite
  public void setUp() {
	  File buildFile = new File("db/build_db.xml");
	  Project p = new Project();
	  p.setUserProperty("ant.file", buildFile.getAbsolutePath());
	  p.init();
	  ProjectHelper helper = ProjectHelper.getProjectHelper();
	  p.addReference("ant.projectHelper", helper);
	  helper.parse(p, buildFile);
	  p.executeTarget("create_db");
	  p.executeTarget("populate_db");
	  try {
			Configuration cfg = new Configuration();
			cfg.configure();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
			sf = cfg.buildSessionFactory(serviceRegistry);
			((GenDAOImpl) generalDao).setSessionFactory(sf);
		} catch(Throwable ex) {
			System.err.println("Initial sessionfactory creation failed! " + ex);
			ex.printStackTrace();
		}
  }
  @AfterSuite(alwaysRun=true)
  public void tearDown() {
	  File buildFile = new File("db/build_db.xml");
	  Project p = new Project();
	  p.setUserProperty("ant.file", buildFile.getAbsolutePath());
	  p.init();
	  ProjectHelper helper = ProjectHelper.getProjectHelper();
	  p.addReference("ant.projectHelper", helper);
	  helper.parse(p, buildFile);
	  p.executeTarget("drop_db");
  }
  
  @BeforeMethod
  public void startTrans() {
	  sf.openSession();
	  sf.getCurrentSession().getTransaction().begin();
  }
  
  @AfterMethod
  public void commitTrans() {
	  sf.getCurrentSession().getTransaction().commit();
	  sf.getCurrentSession().close();
  }
  
  @Test
  public void readPushkin() {
	  Author pushkin = generalDao.fetch(Author.class, new Integer(1));
	  assertNotNull(pushkin);
	  assertEquals(pushkin.getBooks().size(), 1);
	  assertTrue(generalDao.isConnected(pushkin));
	  System.out.println(pushkin);
  }
  
  @Test
  public void readOrder() {
	  Order p = generalDao.fetch(Order.class, new Integer(1));
	  assertNotNull(p);
	  assertTrue(generalDao.isConnected(p));
	  Map<Book, Integer> res = p.getBooks();
	  assertNotNull(res);
	  assertEquals(res.size(), 3);
  }
  
  @Test
  public void addExistingBookToExistingOrder() {
	  Order p = generalDao.fetch(Order.class, new Integer(2));
	  assertNotNull(p);
	  assertTrue(generalDao.isConnected(p));
	  assertEquals(p.getBooks().size(), 1);
	  Book b = generalDao.fetch(Book.class, new Integer(3));
	  p.getBooks().put(b, new Integer(6));
	  generalDao.update(p);
	  assertEquals(generalDao.fetch(Order.class, new Integer(2)).getBooks().size(), 2);
  }
  
  @Test
  public void addExistantBookToNewOrder() {
	  Book b = generalDao.fetch(Book.class, new Integer(4));
	  Order p = new Order();
	  int was = generalDao.count(new Search(Order.class));
	  p.setClient(generalDao.fetch(Client.class, new Integer(2)));
	  p.setWhenMade(new Date(0));
	  p.setDescription("very good");
	  p.setStatus(generalDao.fetchAll(OrderStatus.class).get(0));
	  assertNotNull(p.getBooks());
	  p.getBooks().put(b, b.getQuantity());
	  assertFalse(generalDao.isConnected(p));
	  generalDao.create(p);
	  assertTrue(generalDao.isConnected(p));
	  assertEquals(generalDao.count(new Search(Order.class)), was + 1);
	  System.out.println(p);
  }
  
  @Test
  public void addNewCityThroughNewClient() {
	  int wasCities = generalDao.count(new Search(City.class));
	  int wasClients = generalDao.count(new Search(Client.class));
	  Client p = new Client();
	  City c = new City();
	  c.setName("РЈСЂРµРЅРіРѕР№");
	  p.setCity(c);
	  p.setFirstName("Mike");
	  p.setLastName("Koltsov");
	  p.setLogin("misha.koltsov");
	  p.setHouse(new Integer(11));
	  p.setWing(null);
	  p.setPasswordHash("123");
	  p.setPhone("123-45-67");
	  p.setStreet("Lomonosov");
	  p.setPostalCode("123123");
	  p.setPrivilege(new Integer(0));
	  assertFalse(generalDao.isConnected(p));
	  generalDao.create(p);
	  assertTrue(generalDao.isConnected(p));
	  assertTrue(generalDao.isConnected(c));
	  assertEquals(generalDao.count(new Search(City.class)), wasCities + 1);
	  assertEquals(generalDao.count(new Search(Client.class)), wasClients + 1);
	  System.out.println(p);
  }
  
  @Test
  public void addNewPublisherThroughNewBook() {
	  int wasPubls = generalDao.count(new Search(Publisher.class));
	  Book b = new Book();
	  Publisher p = new Publisher();
	  p.setCity(generalDao.fetch(City.class, new Integer(3)));
	  assertFalse(generalDao.isConnected(p));
	  p.setName("brand new publisher");
	  b.setBookface(generalDao.fetchAll(Bookface.class).get(0));
	  byte[] ph = new byte[]{1, 2, 3};
	  b.setFaceImage(ph);
	  b.setInnerImage(ph);
	  b.setFaceImageBig(ph);
	  b.setNumPages(new Integer(123));
	  b.setPrice(new Double(15.4));
	  b.setQuantity(new Integer(500));
	  b.setYear(new Integer(2010));
	  b.setTitle("Kolobok");
	  b.setPublisher(p);
	  assertFalse(generalDao.isConnected(b));
	  assertFalse(generalDao.isConnected(p));
	  generalDao.createOrUpdate(b);
	  assertTrue(generalDao.isConnected(p));
	  assertTrue(generalDao.isConnected(b));
	  assertEquals(generalDao.count(new Search(Publisher.class)), wasPubls + 1);
	  assertEquals(generalDao.count(new Search(Book.class).addFilterEqual("publisher.id",
			  new Integer(wasPubls + 1))), 1);
  }
  
  @Test
  public void addExistantAuthorToExistantBook() {
	  int wasAuth = generalDao.fetch(Book.class, new Integer(1)).getAuthors().size();
	  int wasBook = generalDao.fetch(Author.class, new Integer(3)).getBooks().size();
	  Book b = generalDao.fetch(Book.class, new Integer(1));
	  Author a = generalDao.fetch(Author.class, new Integer(3));
	  b.getAuthors().add(a);
	  a.getBooks().add(b);
	  assertTrue(generalDao.isConnected(b));
	  assertTrue(generalDao.isConnected(a));
	  assertEquals(generalDao.fetch(Book.class, new Integer(1)).getAuthors().size(), wasAuth + 1);
	  assertEquals(generalDao.fetch(Author.class, new Integer(3)).getBooks().size(), wasBook + 1);
	  b.getAuthors().remove(a);
	  a.getBooks().remove(b);
	  assertEquals(generalDao.fetch(Book.class, new Integer(1)).getAuthors().size(), wasAuth);
	  assertEquals(generalDao.fetch(Author.class, new Integer(3)).getBooks().size(), wasBook);
  }
  
  @Test
  public void addNewGenreWithExistantBook1() {
	  Book b = generalDao.fetch(Book.class, new Integer(4));
	  int wasGenres = b.getGenres().size();
	  Genre g = new Genre();
	  g.setName("Horror");
	  generalDao.createOrUpdate(g);
	  b.getGenres().add(g);
	  g.getBooks().add(b);
	  assertTrue(generalDao.isConnected(b));
	  assertTrue(generalDao.isConnected(g));
	  generalDao.createOrUpdate(b);
	  assertTrue(generalDao.isConnected(b));
	  assertTrue(generalDao.isConnected(g));
	  b.getGenres().remove(g);
	  g.getBooks().remove(b);
	  generalDao.createOrUpdate(b);
	  assertNotNull(generalDao.searchUnique(new Search(Genre.class).addFilterEqual("name", "Horror")));
	  assertEquals(g.getBooks().size(), 0);
	  assertEquals(b.getGenres().size(), wasGenres);
  }
  
  @Test
  public void addNewGenreWithExistantBook2() {
	  Book b = generalDao.fetch(Book.class, new Integer(4));
	  Genre g = new Genre();
	  g.setName("Horror2");
	  b.getGenres().add(g);
	  g.getBooks().add(b);
	  assertTrue(generalDao.isConnected(b));
	  assertFalse(generalDao.isConnected(g));
	  generalDao.createOrUpdate(b);
	  assertFalse(generalDao.isConnected(g));
  }
  
  @Test
  public void equalityOfBooks() {
	  Book b = generalDao.fetch(Book.class, new Integer(1));
	  Book r = new Book();
	  r.setAuthors(b.getAuthors()); r.setBookface(b.getBookface()); r.setDescription(b.getDescription()); r.setGenres(b.getGenres());
	  r.setNumPages(b.getNumPages()); r.setPrice(b.getPrice()); r.setPublisher(b.getPublisher()); r.setQuantity(b.getQuantity());
	  r.setYear(b.getYear()); r.setTitle(b.getTitle());
	  assertEquals(b, r);
	  assertNotSame(b, r);
	  r.setPublisher(generalDao.fetch(Publisher.class, new Integer(3)));
	  assertNotEquals(b, r);
	  r.setPublisher(b.getPublisher());
	  r.setYear(new Integer(123));
	  assertNotEquals(b, r);
  }

  @Test
  public void exampleFilter() {
	  assertEquals(generalDao.count(new Search(Book.class).addFilterEqual("publisher.city.name", "Москва")), 2);
	  assertEquals(generalDao.count(new Search(Book.class).addFilterEqual("publisher.city.id", new Integer(4))), 2);
	  assertEquals(generalDao.count(new Search(Author.class).addFilterGreaterThan("books.size", 1)), 2);
  }
}
