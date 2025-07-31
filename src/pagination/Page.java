package pagination;

import java.util.List;

public class Page<T> {
  List<T> content;
  int pageNumber;
  int pageSize;
  int totalItems;
  int totalPages;

  public Page(List<T> content, int pageNumber, int pageSize, int totalItems, int totalPages) {
    this.content = content;
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
    this.totalItems = totalItems;
    this.totalPages = totalPages;
  }

  public List<T> getContent() {
    return content;
  }

  @Override
  public String toString() {
    return "Page " + pageNumber + "/" + totalPages + ", " + pageSize + " items";
  }
}