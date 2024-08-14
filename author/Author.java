package library_management_system.author;

import java.util.UUID;

public class Author {
  private UUID authorId;
  private String firstName;
  private String lastName;

  public Author(UUID authorId, String firstName, String lastName) {
    this.authorId = authorId;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return "Author{" +
            "authorId=" + authorId +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  public UUID getAuthorId() {
    return authorId;
  }

  public void setAuthorId(UUID authorId) {
    this.authorId = authorId;
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
}
