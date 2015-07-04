package springapp.library.classes;

import javax.validation.constraints.NotNull;

public class GenreMtMForm {
	@NotNull
	private Integer genreId;
	private boolean delete;
	
	
	public Integer getGenreId() {
		return genreId;
	}
	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}
	public GenreMtMForm() {
		super();
	}
	public boolean isDelete() {
		return delete;
	}
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
}
