package springapp.library.classes;

import java.io.Serializable;

public class SearchForm implements Serializable {
	private static final long serialVersionUID = 186312L;
	private String title;
	private Integer genreId;
	private String bookface;
	private Integer publisherId;
	private Integer year;
	private String order;
	private Boolean presented;
	private Integer page;
	@Override
	public String toString() {
		return "SearchForm [title=" + title + ", genreId=" + genreId
				+ ", bookface=" + bookface + ", publisherId=" + publisherId
				+ ", year=" + year + ", order=" + order + ", presented="
				+ presented + ", page=" + page + ", numPages=" + numPages
				+ ", numberOfBooks=" + numberOfBooks + "]";
	}
	private Integer numPages;
	private Integer numberOfBooks;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getNumPages() {
		return numPages;
	}
	public void setNumPages(Integer numPages) {
		this.numPages = numPages;
	}
	public Integer getNumberOfBooks() {
		return numberOfBooks;
	}
	public void setNumberOfBooks(Integer numberOfBooks) {
		this.numberOfBooks = numberOfBooks;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getGenreId() {
		return genreId;
	}
	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}
	public String getBookface() {
		return bookface;
	}
	public void setBookface(String bookface) {
		this.bookface = bookface;
	}
	public Integer getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public Boolean getPresented() {
		return presented;
	}
	public void setPresented(Boolean presented) {
		this.presented = presented;
	}
	
}
