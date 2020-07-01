package com.codecool.books.model;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoJDBC implements Dao<Author> {
    private DataSource dataSource;

    public AuthorDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Author author) {
        try {Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO author (first_name, last_name,birth_date) values (?, ?, ?)");
            stmt.setString(1, author.getFirstName() );
            stmt.setString(2, author.getLastName());
            stmt.setDate(3, author.getBirthDate());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Author author) {
        try { Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE author SET first_name = ? , last_name = ? , birth_date = ? WHERE id = ?;");
            stmt.setString(1, author.getFirstName() );
            stmt.setString(2, author.getLastName());
            stmt.setDate(3, author.getBirthDate());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Author get(int id) {
        try { Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SElECT * FROM author WHERE id = ?");
            stmt.setInt(1, id );
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Author author = new Author(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("birth_date"));
                author.setId(rs.getInt("id"));
                return author;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null; }

    @Override
    public List<Author> getAll() {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM author;");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Author> authors = new ArrayList<>();
            while (resultSet.next()) {
                Author author = new Author(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("birth_date"));
                author.setId(resultSet.getInt("id"));
                authors.add(author);
            }
            return authors;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
