package springapp.library.classes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Author {
    private Integer id;
    
    @NotNull
    @NotEmpty
    private String lastName;
    
    @NotNull
    @NotEmpty
    private String firstName;
    private String biography;
    private Set<Book> books = new HashSet<Book>(0);
    private byte[] photo = null;

	public Author () {} 
    
    public void setId(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getLastName() { return lastName; }
    
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getFirstName() { return firstName; }
        
    public void setBiography(String bio) { this.biography = bio; }
    public String getBiography() { return biography; }
    
    public void setPhoto(byte[] ph) { 
    	this.photo = ph; 
    }
    public byte[] getPhoto() { return photo; }
    


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((biography == null) ? 0 : biography.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + Arrays.hashCode(photo);
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
		Author other = (Author) obj;
		if (biography == null) {
			if (other.biography != null)
				return false;
		} else if (!biography.equals(other.biography))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (!Arrays.equals(photo, other.photo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", lastName=" + lastName + ", firstName="
				+ firstName + ", biography=" + biography + "]";
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
}
