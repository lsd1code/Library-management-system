package library_management_system.book;

public enum BookCategory {
  LITERARY_FICTION(1),
  ADVENTURE_FICTION(2),
  MYSTERY(3),
  CLASSICS(4),
  YOUNG_ADULTS(5),
  HISTORY(6),
  SHORT_STORIES(7),
  HORROR(8),
  COMICS(9),
  THRILLER(10),
  FANTASY(11),
  FAIRYTALE(12),
  MEMOIR(13),
  SATIRE(14),
  SCIENCE_FICTION(15),
  HISTORICAL_ROMANCE(16),
  BIOGRAPHY(17),
  ROMANCE_NOVEL(18),
  GLOBAL_NONFICTION(19),
  RELIGION(20);

  private final int categoryId;

  BookCategory(int categoryId) {
    this.categoryId = categoryId;
  }

  public int getCategoryId() {
    return categoryId;
  }
}
