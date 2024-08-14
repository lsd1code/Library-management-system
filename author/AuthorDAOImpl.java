package library_management_system.author;

import library_management_system.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class AuthorDAOImpl implements AuthorDAO {
  @Override
  public Author get(UUID id) throws SQLException {
    Connection conn = Database.getConnection();

    String query = """
    SELECT
      author_id, first_name, last_name
    FROM author
    WHERE author_id = ?
    """;

    PreparedStatement ps = conn.prepareStatement(query);
    ps.setObject(1, id);

    ResultSet rs = ps.executeQuery();

    Author author = null;

    if (rs.next()) {
      UUID authorId = UUID.fromString(rs.getString("author_id"));
      String firstName = rs.getString("first_name");
      String lastName = rs.getString("last_name");

      author = new Author(authorId, firstName, lastName);
    }

    return author;
  }

  @Override
  public List<Author> getAll(UUID bookId) throws SQLException {
    return null;
  }

  @Override
  public int insert(Author author) throws SQLException {
    Connection conn = Database.getConnection();

    String query = "INSERT INTO author VALUES (?, ?, ?);";

    PreparedStatement ps = conn.prepareStatement(query);
    ps.setObject(1, author.getAuthorId());
    ps.setString(2, author.getFirstName());
    ps.setString(3, author.getLastName());

    int result = ps.executeUpdate();

    Database.closePreparedStatement(ps);
    Database.closeConnection(conn);

    return result;
  }

  @Override
  public int update(Author author) throws SQLException {
    return 0;
  }

  @Override
  public int delete(Author author) throws SQLException {
    return 0;
  }
}
