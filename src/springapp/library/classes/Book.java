package springapp.library.classes;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Book {
	private Integer id;
	
	@NotNull
	@Size(min=1,max=100)
	private String title;
	
	@NotNull
	@Min(0)
	private Double price;
	
	@NotNull
	@Min(0)
	private Integer quantity;
	
	@NotNull
	private Integer year;
	
	@NotNull
	@Min(1)
	private Integer numPages;
	
	private String description;
	
	@NotNull
	private Bookface bookface;
	private byte[] faceImage, faceImageBig, innerImage;
	
	@NotNull
	private Publisher publisher;

	private Set<Author> authors = new HashSet<Author>(0);
	private Set<Genre> genres = new HashSet<Genre>(0);
	
	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public Book() {}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		if(title != null)
			return title.trim();
		return null;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getNumPages() {
		return numPages;
	}
	public void setNumPages(Integer numPages) {
		this.numPages = numPages;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getFaceImage() {
		return faceImage;
	}
	public void setFaceImage(byte[] faceImage) {
		this.faceImage = faceImage;
	}
	public byte[] getFaceImageBig() {
		return faceImageBig;
	}
	public void setFaceImageBig(byte[] faceImageBig) {
		this.faceImageBig = faceImageBig;
	}
	public byte[] getInnerImage() {
		return innerImage;
	}
	public void setInnerImage(byte[] innerImage) {
		this.innerImage = innerImage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bookface == null) ? 0 : bookface.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((numPages == null) ? 0 : numPages.hashCode());
		result = prime * result
				+ ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookface == null) {
			if (other.bookface != null)
				return false;
		} else if (!bookface.equals(other.bookface))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (numPages == null) {
			if (other.numPages != null)
				return false;
		} else if (!numPages.equals(other.numPages))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", price=" + price
				+ ", quantity=" + quantity + ", year=" + year + ", numPages="
				+ numPages + ", bookface=" + bookface.getType() + ", publisher=" 
				+ publisher + "]";
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Bookface getBookface() {
		return bookface;
	}

	public void setBookface(Bookface bookface) {
		this.bookface = bookface;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}
	
}
