package library_management_system.auth;

import library_management_system.member.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Authentication {
  private static final int adminRegCode = 1000;
  private static Scanner scanner;

  public Authentication() {}

  public static Member register() throws IOException, SQLException {
    MemberDAO memberDAO = new MemberDAOImpl();
    Member member;

    scanner = new Scanner(System.in);

    int role;

    try {
      System.out.print("Choose your role. [1. Admin, 2. Member]: ");
      role = scanner.nextInt();
    } catch(InputMismatchException ime) {
      return register();
    }

    switch (role) {
      case 1 -> {
        System.out.print("Enter Admin Registration code: ");
        int value = scanner.nextInt();

        if (value != adminRegCode) {
          System.out.println("Enter valid Admin Registration Code!");
          System.exit(1);
        }

        member = generateMember(MemberRole.ADMIN);
      }
      case 2 -> member = generateMember(MemberRole.MEMBER);
      default -> {
        return register();
      }
    }

    memberDAO.insert(member);

    return member;
  }

  public static Member login() throws SQLException, IOException {
    MemberDAO memberDAO = new MemberDAOImpl();

    Member member;
    int role;

    scanner = new Scanner(System.in);

    try {
      System.out.print("Choose your role. [1. Admin, 2. Member]: ");
      role = scanner.nextInt();
    } catch(InputMismatchException ime) {
      return login();
    }

    switch (role) {
      case 1 -> {
        System.out.print("Enter Admin Registration code: ");
        int value = scanner.nextInt();

        if (value != adminRegCode) {
          System.out.println("Enter valid Admin Registration Code!");
          System.exit(1);
        }

        String libraryNum = getString("Enter your Library Number");

        member = memberDAO.get(libraryNum);
      }
      case 2 -> {
        String libraryNum = getString("Enter your Library Number");

        member = memberDAO.get(libraryNum);
      }
      default -> {
        return login();
      }
    }

    return member;
  }

  private static Member generateMember(MemberRole role) throws IOException {
    String firstName = getString("Firstname");
    String lastName = getString("Lastname");
    LocalDate joinedDate = LocalDate.now();
    ActiveStatus activeStatus = ActiveStatus.ACTIVE;
    String libraryNumber = generateLibraryNumber(role);

    return new Member(0, firstName, lastName, joinedDate, activeStatus, role, libraryNumber);
  }

  private static String generateLibraryNumber(MemberRole role) {
    StringBuilder libraryNumber = new StringBuilder();

    if (role == MemberRole.ADMIN) {
      libraryNumber.append("A");
    } else {
      libraryNumber.append("M");
    }

    for (int i = 0; i < 6; i++) {
      int randomNum = (int) ((Math.random() * 10) + 1);
      libraryNumber.append(randomNum);
    }
    return libraryNumber.toString();
  }

  private static String getString(String prompt) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    System.out.print(prompt + ": ");
    return reader.readLine();
  }
}
