package springapp.library.classes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class Order {
	private Integer id;
	
	@DateTimeFormat(pattern = "dd.MM.yy")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date whenMade;
	
	@NotNull
	private Client client;
	
	private String description;
	
	private OrderStatus status;
	
	private Map<Book, Integer> books = new HashMap<Book, Integer>(0);
	
	public Order() {}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getWhenMade() {
		return whenMade;
	}
	public void setWhenMade(Date whenMade) {
		this.whenMade = whenMade;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", whenMade=" + whenMade + ", description="
				+ description + ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((whenMade == null) ? 0 : whenMade.hashCode());
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
		Order other = (Order) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (whenMade == null) {
			if (other.whenMade != null)
				return false;
		} else if (!whenMade.equals(other.whenMade))
			return false;
		return true;
	}
	public Map<Book, Integer> getBooks() {
		return books;
	}
	public void setBooks(Map<Book, Integer> books) {
		this.books = books;
	}
	
	
}
