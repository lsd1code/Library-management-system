package library_management_system.member;

import library_management_system.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface MemberDAO extends DAO<Member> {
  Member get(String libraryNumber) throws SQLException;

  Member extractMemberInfo(ResultSet resultSet) throws SQLException;
}
