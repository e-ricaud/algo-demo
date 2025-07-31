package pagination;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PaginationUtil {

  public static <T>List<T> paginate(List<T> items, int page, int itemsPerPage) {
    isValidPagination(items, page, itemsPerPage);

    int fromIndex = (page - 1) * itemsPerPage;

    if(fromIndex >= items.size()) {
      return new ArrayList<>();
    }

    int toIndex = Math.min(fromIndex + itemsPerPage, items.size());

    return items.subList(fromIndex, toIndex);
  }

  public static <T> List<T> paginateWithStream(List<T> items, int page, int itemsPerPage) {
    int fromIndex = (page - 1) * itemsPerPage;
    return items.stream().skip(fromIndex).limit(itemsPerPage).toList();
  }

  public static <T extends Comparable<T>> List<T> paginateSorted(List<T> items, int page, int itemsPerPage, boolean ascending) {
    isValidPagination(items, itemsPerPage, page);
    List<T> sortedList = items.stream().sorted(ascending ? Comparator.naturalOrder() : Comparator.reverseOrder()).toList();
    return paginate(sortedList, page, itemsPerPage);
  }

  public static <T> Page<T> paginateWithMetadata(List<T> items, int page, int itemsPerPage) {
    isValidPagination(items, page, itemsPerPage);
    List<T> paginatedList = paginate(items, page, itemsPerPage);
    return new Page<>(
            paginatedList,
            page,
            paginatedList.size(),
            items.size(),
            getNumberOfPages(items, itemsPerPage)
            );
  }

  public static <T> List<T> paginateWithCursor(List<T> items, int cursorIndex, int itemsPerPage) {
    if (items == null || cursorIndex < -1 || itemsPerPage <= 0) {
      throw new IllegalArgumentException("Arguments invalides.");
    }
    if (cursorIndex >= items.size() - 1) {
      return List.of();
    }
    return items.subList(cursorIndex + 1, Math.min(cursorIndex + itemsPerPage, items.size()));
  }


  public static int getNumberOfPages(List items, int itemsPerPage) {
    int totalPages = (int) Math.ceil((double) items.size() / itemsPerPage);
    return totalPages;
  }

  private static <T> void isValidPagination(List<T> items, int page, int itemsPerPage) {
    if (items == null || page <= 0 || itemsPerPage <= 0) {
      throw new IllegalArgumentException("Arguments invalides.");
    }
  }

}
