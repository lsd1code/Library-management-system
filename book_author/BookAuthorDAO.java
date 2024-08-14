package library_management_system.book_author;

import library_management_system.author.Author;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface BookAuthorDAO {
  int insert(UUID bookId, UUID authorId) throws SQLException;

  List<Author> getAll(UUID bookId) throws SQLException;
}
