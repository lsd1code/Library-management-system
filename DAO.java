package library_management_system;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
  T get(int id) throws SQLException;

  List<T> getAll() throws SQLException;

  int insert(T object) throws SQLException;

  int update(T object) throws SQLException;

  int delete(T object) throws SQLException;
}
