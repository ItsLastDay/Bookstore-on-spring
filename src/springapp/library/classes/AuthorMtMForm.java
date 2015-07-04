package springapp.library.classes;

import javax.validation.constraints.NotNull;

public class AuthorMtMForm {
	@NotNull
	private Integer authorId;
	private boolean delete;
	
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public AuthorMtMForm() {
		super();
	}
	public boolean isDelete() {
		return delete;
	}
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
}
