package pagination;

import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<String> data = new ArrayList<>();
    for (int i = 1; i <= 25; i++) {
      data.add("Item " + i);
    }

    List<String> paginatedList = PaginationUtil.paginate(data, 4, 5);
    List<String> paginatedStreamList = PaginationUtil.paginateWithStream(data, 4, 5);
    List<String> paginatedCursorList = PaginationUtil.paginateWithCursor(data, 0, 5);
    Page<String> paginatedMetadata = PaginationUtil.paginateWithMetadata(data, 4, 5);
    int numberOfpages = PaginationUtil.getNumberOfPages(data, 5);

    System.out.println("Pagined list : " + paginatedList);
    System.out.println("Pagined stream list : " + paginatedStreamList);
    System.out.println("Pagined cursor list : " + paginatedCursorList);
    System.out.println("Pagined metadata : " + paginatedMetadata + " -> " + paginatedMetadata.getContent());
    System.out.println("Number of pages : " + numberOfpages);
  }
}