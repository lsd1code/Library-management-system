package library_management_system.book;

import library_management_system.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public interface BookDAO extends DAO<Book> {
  public Book get(UUID id) throws SQLException;

  public Book extract(ResultSet rs) throws SQLException;
}
