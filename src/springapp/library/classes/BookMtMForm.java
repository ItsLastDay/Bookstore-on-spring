package springapp.library.classes;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BookMtMForm {
	@NotNull
	private Integer bookId;
	
	@NotNull
	@Min(1)
	private Integer quantity;
	public BookMtMForm() {
		super();
	}
	public boolean isDelete() {
		return delete;
	}
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	private boolean delete;
}