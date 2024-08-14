package library_management_system.book;

import java.time.LocalDate;
import java.util.UUID;

public class Book {
  private UUID bookId;
  private String title;
  private LocalDate publicationDate;
  private int copiesOwned;
  private BookCategory category;

  public Book(UUID bookId, String title, LocalDate publicationDate, int copiesOwned, BookCategory category) {
    this.bookId = bookId;
    this.title = title;
    this.publicationDate = publicationDate;
    this.copiesOwned = copiesOwned;
    this.category = category;
  }

  @Override
  public String toString() {
    return "Book{" +
            "bookId=" + bookId +
            ", title='" + title + '\'' +
            ", publicationDate=" + publicationDate +
            ", copiesOwned=" + copiesOwned +
            ", category='" + category + '\'' +
            '}';
  }

  public UUID getBookId() {
    return bookId;
  }

  public void setBookId(UUID bookId) {
    this.bookId = bookId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDate getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(LocalDate publicationDate) {
    this.publicationDate = publicationDate;
  }

  public int getCopiesOwned() {
    return copiesOwned;
  }

  public void setCopiesOwned(int copiesOwned) {
    this.copiesOwned = copiesOwned;
  }

  public BookCategory getCategory() {
    return category;
  }

  public void setCategory(BookCategory category) {
    this.category = category;
  }
}
