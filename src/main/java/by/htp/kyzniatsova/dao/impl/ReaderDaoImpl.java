package by.htp.kyzniatsova.dao.impl;

import static by.htp.kyzniatsova.dao.util.MySqlPropertyManager.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.kyzniatsova.dao.ReaderDao;
import by.htp.kyzniatsova.domain.entity.Employer;

public class ReaderDaoImpl implements ReaderDao {

	private List <Employer> books = new ArrayList<Employer>();

	public Employer read(int id) {
		Employer book = null;
		
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = connection.prepareStatement(SELECT_BOOK_BY_ID);
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
	
			if(rs.next()) {
				book = buildBook(rs);
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	public List<Employer> list() {
		List<Employer> books = new ArrayList<Employer>();
		return books;
	}

	public int insert(Book book) {
		int a = -1;
		boolean isExistId = false;
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			PreparedStatement psSel = connection.prepareStatement(SELECT_ID_BOOK);
			
			ResultSet rsAll = psSel.executeQuery();
			int id = book.getId();
			while(rsAll.next()) {
				if(rsAll.getInt("id_book") == id) {
					System.out.println("The id is existing in database");
					isExistId = true;
					break;
				}
			}
			
			if(isExistId == false) {
				PreparedStatement ps = connection.prepareStatement(INSERT_BOOK);
			
				ps.setInt(1, book.getId());
				ps.setString(2, book.getTitle());
				ps.setDate(3, book.getDate());
		
				ps.executeUpdate();
				System.out.println(book + " was inserted");
		
				return a;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	public void delete(Employer book) {
	
	}

	public void update(Employer book) {
		
	}

	public Employer readAll() {
		Employer book = null;
		
		try(Connection connection = DriverManager.getConnection(getUrl(), getLogin(), getPass())){
			
			PreparedStatement psAll = connection.prepareStatement(SELECT_ALL_BOOK);
			ResultSet rsAll = psAll.executeQuery();
			
			while(rsAll.next()) {
				book = buildEmployer(rsAll);
				books.add(book);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	public List<Employer> getBooks() {
		return null;
	}
	
	private Employer buildEmployer(ResultSet rs) throws SQLException {
		Employer book = new Employer();
		book.setId(rs.getInt("id_book"));
		book.setTitle(rs.getString("Title"));
		book.setDate(rs.getDate("Prod_year"));
		return book;
	}

}
