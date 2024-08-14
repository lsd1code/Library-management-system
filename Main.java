package library_management_system;

import library_management_system.author.Author;
import library_management_system.author.AuthorDAO;
import library_management_system.author.AuthorDAOImpl;
import library_management_system.book.Book;
import library_management_system.book.BookCategory;
import library_management_system.book.BookDAO;
import library_management_system.book.BookDAOImpl;
import library_management_system.book_author.BookAuthorDAO;
import library_management_system.book_author.BookAuthorDAOImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
  private static Scanner scn = null;
  private static final BookDAO bookDAO = new BookDAOImpl();
  private static final AuthorDAO authorDAO = new AuthorDAOImpl();
  private static final BookAuthorDAO bookAuthorDAO = new BookAuthorDAOImpl();


  public static void main(String[] args) throws Exception {
    var authors = bookAuthorDAO.getAll(UUID.fromString("88336582-d4c0-42fc-af38-e17b4ea305a5"));

    authors.forEach(System.out::println);
  }

  private static String getString(String prompt) {
    scn = new Scanner(System.in);

    System.out.print(prompt + ": ");
    return scn.next();
  }

  private static int getInt(String prompt) {
    scn = new Scanner(System.in);

    System.out.print(prompt + ": ");
    return scn.nextInt();
  }

  public static void addBook() throws SQLException, Exception{
    UUID bookId = UUID.randomUUID();

    String title = getString("title");

    System.out.println("Enter book publication date: ");
    int day = getInt("day");
    int month = getInt("month");
    int year = getInt("year");
    LocalDate publicationDate = LocalDate.of(year, month, day);

    int copiesOwned = getInt("Number of copies");

    BookCategory[] bc = BookCategory.values();

    System.out.println("Categories: ");
    for (var cat : bc) {
      System.out.println(cat.getCategoryId() + ": " + cat);
    }

    int categoryNum = getInt("Choose category");
    if(categoryNum > bc.length) {
      throw new Exception("Enter valid book category number");
    }

    BookCategory category = null;

    for (var cat : bc) {
      if (cat.getCategoryId() == categoryNum) {
        category = cat;
        break;
      }
    }

    Book book = new Book(bookId, title, publicationDate, copiesOwned, category);

    bookDAO.insert(book);

    List<Author> authors = new ArrayList<>();

    System.out.println("\nAdd Book authors.");
    while (true) {
      Author author = addAuthor();
      authors.add(author);

      String response = getString("Do you want to add another author?[Y/N]");

      if (!response.equalsIgnoreCase("y"))
        break;
    }

    authors.forEach(author -> {
      try {
        authorDAO.insert(author);
        bookAuthorDAO.insert(bookId, author.getAuthorId());
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    });
  }

  public static Author addAuthor() {
    scn = new Scanner(System.in);

    UUID authorId = UUID.randomUUID();

    System.out.print("Firstname: ");
    String firstname = scn.next();

    System.out.print("Lastname: ");
    String lastname = scn.next();

    return new Author(authorId, firstname, lastname);
  }
}