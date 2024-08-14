package library_management_system.book;

import library_management_system.Database;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookDAOImpl implements BookDAO{
  @Override
  public Book get(UUID id) throws SQLException {
    Connection conn = Database.getConnection();

    String query = """
    SELECT
      b.book_id, b.title, b.publication_date, b.copies_owned, c.name
    FROM book b
    INNER JOIN category c
      ON b.category_id = c.category_id
    WHERE book_id = ?;
    """;

    PreparedStatement ps = conn.prepareStatement(query);
    ps.setObject(1, id);

    ResultSet rs = ps.executeQuery();

    Book book = null;

    if (rs.next()) {
      book = extract(rs);
    }

    Database.closePreparedStatement(ps);
    Database.closeConnection(conn);

    return book;
  }

  @Override
  public List<Book> getAll() throws SQLException {
    Connection conn = Database.getConnection();

    Statement statement = conn.createStatement();

    String query = """
    SELECT
      b.book_id, b.title, b.publication_date, b.copies_owned, c.name
    FROM book b
    INNER JOIN category c
      ON b.category_id = c.category_id;
    """;

    ResultSet rs = statement.executeQuery(query);

    List<Book> books = new ArrayList<>();

    while (rs.next()) {
      Book book = extract(rs);
      books.add(book);
    }

    Database.closeConnection(conn);

    return books;
  }

  @Override
  public int insert(Book book) throws SQLException {
    Connection conn = Database.getConnection();

    String query = """
    INSERT INTO book
      (book_id, title, publication_date, copies_owned, category_id)
    VALUES
      (?, ?, ?, ?, ?);
    """;

    PreparedStatement ps = conn.prepareStatement(query);
    ps.setObject(1, book.getBookId());
    ps.setString(2, book.getTitle());
    ps.setDate(3, Date.valueOf(book.getPublicationDate()));
    ps.setInt(4, book.getCopiesOwned());
    ps.setInt(5, book.getCategory().getCategoryId());

    int result = ps.executeUpdate();

    Database.closePreparedStatement(ps);
    Database.closeConnection(conn);

    return result;
  }

  @Override
  public int update(Book object) throws SQLException {
    return 0;
  }

  @Override
  public int delete(Book book) throws SQLException {
    Connection conn = Database.getConnection();

    String query = """
    DELETE FROM book
    WHERE book_id = ?
    """;

    PreparedStatement ps = conn.prepareStatement(query);
    ps.setObject(1, book.getBookId());

    int result = ps.executeUpdate();

    Database.closePreparedStatement(ps);
    Database.closeConnection(conn);

    return result;
  }

  @Override
  public Book extract(ResultSet rs) throws SQLException {
    UUID bookId = UUID.fromString(rs.getString("book_id"));
    String title = rs.getString("title");
    LocalDate pubDate = rs.getDate("publication_date").toLocalDate();
    int copiesOwned = rs.getInt("copies_owned");
    BookCategory category = BookCategory.valueOf(rs.getString("name"));

    return new Book(bookId, title, pubDate, copiesOwned, category);
  }

  @Override
  public Book get(int id) throws SQLException {
    return null;
  }
}
