package library_management_system.author;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface AuthorDAO {
  Author get(UUID id) throws SQLException;

  List<Author> getAll(UUID bookId) throws SQLException;

  int insert(Author author) throws SQLException;

  int update(Author author) throws SQLException;

  int delete(Author author) throws SQLException;
}
