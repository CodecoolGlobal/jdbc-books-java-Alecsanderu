package com.codecool.books.model;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoJDBC implements Dao<Book> {
	private DataSource dataSource;
	private AuthorDaoJDBC authorDaoJDBC;

	public BookDaoJDBC(DataSource dataSource, AuthorDaoJDBC authorDaoJDBC){
		this.dataSource = dataSource;
		this.authorDaoJDBC = authorDaoJDBC;

	}

	@Override
	public void add(Book book) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO book(author_id, title) VALUES (?, ?);");
			preparedStatement.setInt(1, book.getAuthor().getId());
			preparedStatement.setString(2, book.getTitle());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Book book) {
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement("UPDATE book SET author_id = ? , title = ? , WHERE id = ?;");
			stmt.setInt(1, book.getAuthor().getId() );
			stmt.setString(2, book.getTitle());
			stmt.setInt(3, book.getId());
			stmt.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Override
	public Book get(int id) {
		try { Connection conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SElECT * FROM book WHERE id = ?");
			stmt.setInt(1, id );
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Book book = new Book(
						authorDaoJDBC.get(rs.getInt(id)),
						rs.getString("title"));
				book.setId(rs.getInt("id"));
				return book;
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> getAll() {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT * FROM book;");
			ResultSet rs = preparedStatement.executeQuery();
			List<Book> books = new ArrayList<>();
			while (rs.next()) {
				Book book = new Book(
						authorDaoJDBC.get(rs.getInt("author_id")),
						rs.getString("title"));
				book.setId(rs.getInt("id"));
				books.add(book);
			}
			return books;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
