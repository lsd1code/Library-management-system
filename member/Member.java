package library_management_system.member;

import java.time.LocalDate;

public class Member {
  private int id;
  private String firstName;
  private String lastName;
  private LocalDate joinedDate;
  private ActiveStatus activeStatus;
  private MemberRole role;
  private String libraryNumber;

  public Member(int id, String firstName, String lastName, LocalDate joinedDate, ActiveStatus activeStatus, MemberRole role, String libraryNumber) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.joinedDate = joinedDate;
    this.activeStatus = activeStatus;
    this.role = role;
    this.libraryNumber = libraryNumber;
  }

  @Override
  public String toString() {
    return "Member{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", joinedDate=" + joinedDate +
            ", activeStatus=" + activeStatus +
            ", role=" + role +
            ", libraryNumber=" + libraryNumber +
            '}';
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getJoinedDate() {
    return joinedDate;
  }

  public void setJoinedDate(LocalDate joinedDate) {
    this.joinedDate = joinedDate;
  }

  public ActiveStatus getActiveStatus() {
    return activeStatus;
  }

  public void setActiveStatus(ActiveStatus activeStatus) {
    this.activeStatus = activeStatus;
  }

  public MemberRole getRole() {
    return role;
  }

  public void setRole(MemberRole role) {
    this.role = role;
  }

  public String getLibraryNumber() {
    return libraryNumber;
  }

  public void setLibraryNumber(String libraryNumber) {
    this.libraryNumber = libraryNumber;
  }
}
