package by.htp.ellib.dao;

import java.util.List;

import by.htp.ellib.entity.Book;

public interface BookDAO {
	
	
	// здесь нужно внести критерии поиска
	List<Book> find (String criteria);
	
	
	

}
