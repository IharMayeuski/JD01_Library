package by.htp.ellib.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.ellib.dao.BookDAO;
import by.htp.ellib.dao.BooksLibraryDAO;
import by.htp.ellib.dao.ConnectPool.ConnectionPool;
import by.htp.ellib.dao.ConnectPool.ConnectionPoolException;
import by.htp.ellib.entity.Book;
import by.htp.ellib.entity.BooksLibrary;
import by.htp.ellib.entity.Info;
import by.htp.ellib.entity.User;
import by.htp.ellib.exceptions.DaoException;

public class SQLBooksLibraryDAO extends SqlDao implements BooksLibraryDAO{

	private static final String QUERY_CHECK_CREDENTIONALS = "SELECT * FROM book WHERE genre=?";
	private static final String All_info = "SELECT * FROM info";
	private static final String All_book = "SELECT * FROM book";
	private static final String add_info = "INSERT INTO info (book_id, users_id) values (?,?)";

	private static final String found_info = "DELETE from info where book_id=?";

	private static final String Add_book = "INSERT INTO book (author, name, year_book, genre, price) VALUES (?,?,?,?,?)";

	private static final ConnectionPool pool = ConnectionPool.getInstance();

	@Override
	public List<BooksLibrary> find(String genre) throws ConnectionPoolException, DaoException {


		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<BooksLibrary> books = new ArrayList<>();

		try {
			con = pool.takeConection();
			st =  con.prepareStatement(QUERY_CHECK_CREDENTIONALS);

			st.setString(1, genre);
			rs = st.executeQuery();

			while (rs.next()) {
				BooksLibrary b = createBooksLibrary(rs);
				books.add(b);
			}
		}


		catch (SQLException e) {
			System.out.println("Œ¯Ë·Í‡");
			throw new DaoException(e);
		}


		finally {pool.closeConnection(rs, st, con);}
		return books;
	}

	private BooksLibrary createBooksLibrary(ResultSet rs) throws SQLException {
		BooksLibrary B = new BooksLibrary();
		B.setId(rs.getInt(1));
		B.setAuthor(rs.getString(2));
		B.setName(rs.getString(3));
		B.setYear_book(rs.getInt(4));
		//	B.setDate_receive(rs.getString(5));
		B.setGenre(rs.getString(5));
		B.setPrice(rs.getInt(6));

		return B;

	}

	private Info createInfoLibrary(ResultSet rs) throws SQLException {
		Info bookI = new Info();
		bookI.setId(rs.getInt(1));
		bookI.setBook_id(rs.getInt(2));
		bookI.setUsers_id(rs.getInt(3));

		return bookI;

	}

	@Override
	public List<BooksLibrary> all() throws DaoException, ConnectionPoolException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<BooksLibrary> books = new ArrayList<>();

		try {
			con = pool.takeConection();
			st =  con.prepareStatement(All_book);
			rs = st.executeQuery();

			while (rs.next()) {
				BooksLibrary b = createBooksLibrary(rs);
				books.add(b);
			}

		}
		catch (SQLException e) {
			System.out.println("Œ¯Ë·Í‡");
			throw new DaoException(e);
		}

		return books;

	}

	@Override
	public boolean registration(BooksLibrary book) throws DaoException, ConnectionPoolException {
		Connection con = null;
		PreparedStatement stBook = null;
		PreparedStatement stUsersDetail = null;
		ResultSet rs = null;
		User user = null;

		try {
			con=pool.takeConection();
			con.setAutoCommit(false);
			stBook =  con.prepareStatement(Add_book);

			stBook.setString(1, book.getAuthor());
			stBook.setString(2, book.getName());
			stBook.setInt(3, book.getYear_book());	
			stBook.setString(4, book.getGenre());
			stBook.setInt(5, book.getPrice());

			stBook.executeUpdate();

			con.commit();		

		}catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new DaoException(e);
			}

			throw new DaoException(e);
		}

		finally {pool.closeConnection(rs, stBook,con);}

		return true;
	}

	public List<BooksLibrary> findFree(String genre) throws ConnectionPoolException, DaoException {
		Connection con1 = null;
		Connection con2 = null;

		PreparedStatement st1 = null;
		PreparedStatement st2 = null;

		ResultSet rs1 = null;
		ResultSet rs2 = null;

		List<BooksLibrary> AllBookGenre = new ArrayList<>();
		List<Info> bookedBook = new ArrayList<>();

		List<BooksLibrary> books = new ArrayList<>();

		try {
			con1 = pool.takeConection();
			st1 =  con1.prepareStatement(QUERY_CHECK_CREDENTIONALS);

			st1.setString(1, genre);
			rs1 = st1.executeQuery();

			while (rs1.next()) {
				BooksLibrary b = createBooksLibrary(rs1);
				AllBookGenre.add(b);
			}

			con2 = pool.takeConection();
			st2 =  con2.prepareStatement(All_info);

			rs2 = st2.executeQuery();

			while (rs2.next()) {
				Info info = createInfoLibrary(rs2);
				bookedBook.add(info);
			}


			for (int i=0; i<AllBookGenre.size(); i++) {

				boolean temp = true;

				for (int j=0; j<bookedBook.size(); j++) {
					if (AllBookGenre.get(i).getId()==bookedBook.get(j).getBook_id()) {
						/*	System.out.println("------------------------------");
						System.out.print("AllBookGenre.get(i).getId()"+AllBookGenre.get(i).getId()+"="+"bookedBook.get(j).getBook_id()"+bookedBook.get(j).getBook_id()+"!!");
						 */	System.out.println("------------------------------");
						 temp = false;
					}

				}
				if (temp == true) {books.add(AllBookGenre.get(i));

				}
			}

		}


		catch (SQLException e) {
			System.out.println("Œ¯Ë·Í‡");
			throw new DaoException(e);
		}


		finally {
			pool.closeConnection(rs1, st1, con1);
			pool.closeConnection(rs2, st2, con2);}
		return books;
	}


	@Override
	public List<BooksLibrary> allFreeBook() throws DaoException, ConnectionPoolException {
		Connection con1 = null;
		Connection con2 = null;

		PreparedStatement st1 = null;
		PreparedStatement st2 = null;

		ResultSet rs1 = null;
		ResultSet rs2 = null;

		List<BooksLibrary> AllBook = new ArrayList<>();
		List<Info> bookedBook = new ArrayList<>();

		List<BooksLibrary> freeBook = new ArrayList<>();

		try {
			con1 = pool.takeConection();
			st1 =  con1.prepareStatement(All_book);
			rs1 = st1.executeQuery();

			while (rs1.next()) {
				BooksLibrary b = createBooksLibrary(rs1);
				AllBook.add(b);
			}

			con2 = pool.takeConection();
			st2 =  con2.prepareStatement(All_info);
			rs2 = st2.executeQuery();

			while (rs2.next()) {
				Info info = createInfoLibrary(rs2);
				bookedBook.add(info);
			}


			for (int i=0; i<AllBook.size(); i++) {

				boolean temp = true;

				for (int j=0; j<bookedBook.size(); j++) {
					if (AllBook.get(i).getId()==bookedBook.get(j).getBook_id()) {
						temp = false;
					}

				}
				if (temp == true) {freeBook.add(AllBook.get(i));

				}
			}

		}


		catch (SQLException e) {
			System.out.println("Œ¯Ë·Í‡");
			throw new DaoException(e);
		}


		finally {
			pool.closeConnection(rs1, st1, con1);
			pool.closeConnection(rs2, st2, con2);}
		return freeBook;
	}


	@Override
	public List<BooksLibrary> allBookedBook() throws DaoException, ConnectionPoolException {
		Connection con1 = null;
		Connection con2 = null;

		PreparedStatement st1 = null;
		PreparedStatement st2 = null;

		ResultSet rs1 = null;
		ResultSet rs2 = null;

		List<BooksLibrary> AllBook = new ArrayList<>();
		List<Info> bookedBook = new ArrayList<>();

		List<BooksLibrary> book = new ArrayList<>();

		try {
			con1 = pool.takeConection();
			st1 =  con1.prepareStatement(All_book);
			rs1 = st1.executeQuery();

			while (rs1.next()) {
				BooksLibrary b = createBooksLibrary(rs1);
				AllBook.add(b);
			}

			con2 = pool.takeConection();
			st2 =  con2.prepareStatement(All_info);
			rs2 = st2.executeQuery();

			while (rs2.next()) {
				Info info = createInfoLibrary(rs2);
				bookedBook.add(info);
			}


			for (int i=0; i<AllBook.size(); i++) {

				boolean temp = false;

				for (int j=0; j<bookedBook.size(); j++) {
					if (AllBook.get(i).getId()==bookedBook.get(j).getBook_id()) {
						temp = true;

					}

				}
				if (temp == true) {book.add(AllBook.get(i));

				}
			}

		}


		catch (SQLException e) {
			System.out.println("Œ¯Ë·Í‡");
			throw new DaoException(e);
		}


		finally {
			pool.closeConnection(rs1, st1, con1);
			pool.closeConnection(rs2, st2, con2);}
		return book;
	}



	@Override
	public boolean registration(Info book) throws DaoException, ConnectionPoolException {
		Connection con = null;
		PreparedStatement stBook = null;
		PreparedStatement stUsersDetail = null;
		ResultSet rs = null;
		Info info = null;

		try {
			con=pool.takeConection();
			con.setAutoCommit(false);
			stBook =  con.prepareStatement(add_info);
			System.out.println(stBook);
			System.out.println(book.getBook_id()+"!!"+book.getUsers_id());

			stBook.setInt(1, book.getBook_id());
			stBook.setInt(2, book.getUsers_id());

			stBook.executeUpdate();

			con.commit();		

		}catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new DaoException(e);
			}

			throw new DaoException(e);
		}

		finally {pool.closeConnection(rs, stBook,con);}

		return true;
	}

	@Override
	public boolean delete(Info info1) throws DaoException, ConnectionPoolException {

		Connection con = null;
		PreparedStatement stBook = null;
		PreparedStatement stUsersDetail = null;
		ResultSet rs = null;
		Info info = null;

		try {
			con=pool.takeConection();
			con.setAutoCommit(false);
			stBook =  con.prepareStatement(found_info);
			System.out.println(stBook);
			System.out.println(info1.getBook_id()+"!!"+info1.getUsers_id());

			stBook.setInt(1, info1.getBook_id());

			stBook.executeUpdate();

			con.commit();		

		}catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new DaoException(e);
			}

			throw new DaoException(e);
		}

		finally {pool.closeConnection(rs, stBook,con);}

		return true;
	}



}



