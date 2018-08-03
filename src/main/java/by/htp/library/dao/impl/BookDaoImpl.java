package by.htp.library.dao.impl;

import static by.htp.library.dao.util.MySqlPropertyManager.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.library.dao.BookDao;
import by.htp.library.domain.entity.Book;

public class BookDaoImpl implements BookDao {
	
	private static final String SELECT_BOOK_BY_ID = "SELECT * FROM Book b WHERE b.id_book = ?";
	private static final String SELECT_ALL_BOOK = "SELECT * FROM Library.Book b";
	private static final String INSERT_BOOK = "INSERT INTO Book(Title, Pages, Prod_year) VALUES(?,?,?);";
	private static final String DELETE_ID_BOOK = "DELETE from Book where id_book = ?";
	private static final String UPDATE_ID_BOOK = "UPDATE Book SET Title = ?, Pages = ?, Prod_year = ? where id_book = ?";

	
	private List <Book> books = new ArrayList<Book>();

	public Book read(int id) {
		Book book = null;
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = connection.prepareStatement(SELECT_BOOK_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
	
			if(rs.next()) {
				book = buildBook(rs);
			}
		} catch(SQLException e) {
			System.out.println("Database connection error");
			e.printStackTrace();
		}
		return book;
	}

	public List<Book> list() {
		Book book = new Book();
		List<Book> books = new ArrayList<Book>();
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			PreparedStatement psAll = connection.prepareStatement(SELECT_ALL_BOOK);
			ResultSet rsAll = psAll.executeQuery();
			while(rsAll.next()) {
				book = buildBook(rsAll);
				books.add(book);
			}

		} catch(SQLException e) {
			System.out.println("Database connection error");
			e.printStackTrace();
		}
		return books;
	}

	public boolean insert(Book book) {
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			PreparedStatement ps = connection.prepareStatement(INSERT_BOOK);
			ps.setString(1, book.getTitle());
			ps.setInt(2, book.getPages());
			ps.setString(3, book.getProductYear());
			if(ps.executeUpdate() == 1) {
				System.out.println(book + " was inserted successfully");
				return true;
			}
		} catch(SQLException e) {
			System.out.println("Database connection error");
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(Book book) {
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			PreparedStatement ps = connection.prepareStatement(DELETE_ID_BOOK);
			ps.setInt(1, book.getId());
			if(ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Database connection error");
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Book book) {
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			PreparedStatement ps = connection.prepareStatement(UPDATE_ID_BOOK);
			ps.setString(1, book.getTitle());
			ps.setInt(2, book.getPages());
			ps.setString(3, book.getProductYear());
			ps.setInt(4, book.getId());
			if(ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Database connection error");
			e.printStackTrace();
		}
		return false;
	}

	public Book readAll() {
		Book book = null;
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			PreparedStatement psAll = connection.prepareStatement(SELECT_ALL_BOOK);
			ResultSet rsAll = psAll.executeQuery();
			
			while(rsAll.next()) {
				book = buildBook(rsAll);
				books.add(book);
				System.out.println(book);
			}
			
		} catch(SQLException e) {
			System.out.println("Database connection error");
			e.printStackTrace();
		}
		return book;
	}

	public List<Book> getBooks() {
		return null;
	}
	
	private Book buildBook(ResultSet rs) throws SQLException {
		Book book = new Book();
		book.setId(rs.getInt("id_book"));
		book.setTitle(rs.getString("Title"));
		book.setPages(rs.getInt("Pages"));;
		book.setProductYear(rs.getString("Prod_year"));
		return book;
	}

}
