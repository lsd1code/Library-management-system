package library_management_system.book_author;

import library_management_system.Database;
import library_management_system.author.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookAuthorDAOImpl implements BookAuthorDAO{
  @Override
  public int insert(UUID bookId, UUID authorId) throws SQLException {
    Connection conn = Database.getConnection();

    String query = "INSERT INTO book_author VALUES (?, ?);";

    PreparedStatement ps = conn.prepareStatement(query);
    ps.setObject(1, bookId);
    ps.setObject(2, authorId);

    int result = ps.executeUpdate();

    Database.closePreparedStatement(ps);
    Database.closeConnection(conn);

    return result;
  }

  @Override
  public List<Author> getAll(UUID bookId) throws SQLException {
    Connection conn = Database.getConnection();

    String query = """
    SELECT
      a.author_id, a.first_name, a.last_name
    FROM author a
    INNER JOIN book_author ba
      ON a.author_id = ba.author_id
    WHERE ba.book_id = ?;
    """;

    PreparedStatement ps = conn.prepareStatement(query);
    ps.setObject(1, bookId);

    ResultSet rs = ps.executeQuery();

    List<Author> authors = new ArrayList<>();

    while (rs.next()) {
      UUID authorId = UUID.fromString(rs.getString("author_id"));
      String firstName = rs.getString("first_name");
      String lastName = rs.getString("last_name");

      authors.add(new Author(authorId, firstName, lastName));
    }

    Database.closePreparedStatement(ps);
    Database.closeConnection(conn);

    return authors;
  }
}