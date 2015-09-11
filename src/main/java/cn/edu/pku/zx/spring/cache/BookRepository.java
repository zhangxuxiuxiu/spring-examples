package cn.edu.pku.zx.spring.cache;

public interface BookRepository {
	public Book getByIsbn(String isbn);
}
