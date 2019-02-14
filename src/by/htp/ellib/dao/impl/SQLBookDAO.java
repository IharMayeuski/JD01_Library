package by.htp.ellib.dao.impl;

import java.util.ArrayList;
import java.util.List;


import by.htp.ellib.dao.BookDAO;
import by.htp.ellib.entity.Book;
import by.htp.ellib.exceptions.DaoException;

public class SQLBookDAO extends SqlDao implements BookDAO{

	@Override
	public List<Book> find(String criteria) { //throws DaoException(){
		
		
		//заменить на формирует код критериа, далее формирует
		// скл запрос что хотим вернуть, только по тайтл, только по имени
		
		
		List <Book> list = new ArrayList<Book>();
		Book b1 = new Book();
		Book b2 =  new Book();
		b1.setTitle("Java");
		b1.setPrice(500);
		
		b2.setTitle("ASM");
		b2.setPrice(600);
		
		list.add(b1);
		list.add(b2);
		

		return list;
	}
	
}
