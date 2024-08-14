package library_management_system.member;

import library_management_system.Database;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO{
  @Override
  public Member get(int id) throws SQLException {
    Connection conn = Database.getConnection();

    String query = """
    SELECT 
      member_id, first_name, last_name, active_status, joined_date, member_role, library_number
    FROM member
    WHERE member_id = ?;
    """;

    PreparedStatement statement = conn.prepareStatement(query);
    statement.setInt(1,  id);

    ResultSet rs = statement.executeQuery();

    Member member = null;

    if(rs.next()) {
      member = extractMemberInfo(rs);
    }

    Database.closePreparedStatement(statement);
    Database.closeConnection(conn);

    return member;
  }

  @Override
  public List<Member> getAll() throws SQLException {
    Connection conn = Database.getConnection();

    Statement statement = conn.createStatement();

    String query = """
    SELECT 
      member_id, first_name, last_name, active_status, joined_date, member_role, library_number
    FROM member;
    """;

    ResultSet rs = statement.executeQuery(query);

    List<Member> members = new ArrayList<>();

    while (rs.next()) {
      Member member = extractMemberInfo(rs);
      members.add(member);
    }

    Database.closeConnection(conn);

    return members;
  }

  @Override
  public int insert(Member member) throws SQLException {
    Connection conn = Database.getConnection();

    String query = """
    INSERT INTO member 
      (first_name, last_name, joined_date, active_status, member_role, library_number)
    VALUES 
      (?, ?, ?, ?, ?, ?);        
    """;

    PreparedStatement statement = conn.prepareStatement(query);

    statement.setString(1, member.getFirstName());
    statement.setString(2, member.getLastName());
    statement.setDate(3, Date.valueOf(member.getJoinedDate()));
    statement.setString(4, String.valueOf(member.getActiveStatus()));
    statement.setString(5, String.valueOf(member.getRole()));
    statement.setString(6, String.valueOf(member.getLibraryNumber()));

    int result = statement.executeUpdate();

    Database.closePreparedStatement(statement);
    Database.closeConnection(conn);

    return result;
  }

  @Override
  public int update(Member object) throws SQLException {
    return 0;
  }

  @Override
  public int delete(Member object) throws SQLException {
    return 0;
  }

  @Override
  public Member get(String libraryNumber) throws SQLException {
    Connection conn = Database.getConnection();
    Member member = null;

    String query = """
    SELECT 
      member_id, first_name, last_name, joined_date, active_status, member_role, library_number
    FROM member
    WHERE library_number = ?;
    """;

    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, libraryNumber);

    ResultSet rs = statement.executeQuery();

    if (rs.next()) {
      member = extractMemberInfo(rs);
    }

    return member;
  }

  @Override
  public Member extractMemberInfo(ResultSet rs) throws SQLException {
    int mId = rs.getInt("member_id");
    String firstName = rs.getString("first_name");
    String lastName = rs.getString("last_name");
    LocalDate joinedDate = Date.valueOf(rs.getString("joined_date")).toLocalDate();
    ActiveStatus status = ActiveStatus.valueOf(rs.getString("active_status"));
    MemberRole role = MemberRole.valueOf(rs.getString("member_role"));
    String libraryNum = rs.getString("library_number");

    return new Member(mId, firstName, lastName, joinedDate, status, role, libraryNum);
  }
}
