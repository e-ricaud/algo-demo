package slidingWindow;

import java.util.ArrayList;
import java.util.List;

public class SlidingWindowUtil {
  public static int getSumMaxValueWindow(List<Integer> list, int subListSize) {
    if (list == null || list.size() < subListSize || subListSize <= 0) {
      throw new IllegalArgumentException("Liste ou taille de fenêtre invalide.");
    }
    int sumMaxValueWindow = list.subList(0, subListSize)
            .stream()
            .mapToInt(Integer::intValue)
            .sum();
    for(int i = subListSize; i < list.size(); i++) {
        int sumMaxTemp = sumMaxValueWindow - list.get(i- subListSize) + list.get(i);
        if(sumMaxTemp> sumMaxValueWindow) {
          sumMaxValueWindow = sumMaxTemp;
      }
    }
    return sumMaxValueWindow;
  }

  public static List<Double> getAverageSubListWindow(List<Integer> list, int subListSize) {
    if (list == null || list.size() < subListSize || subListSize <= 0) {
      throw new IllegalArgumentException("Liste ou taille de fenêtre invalide.");
    }

    List<Double> averageSubListResult = new ArrayList<>();
    int currentSum = list.subList(0, subListSize)
            .stream()
            .mapToInt(Integer::intValue)
            .sum();


    averageSubListResult.add(currentSum/(double)subListSize);

    for(int i = subListSize; i < list.size(); i++) {
      currentSum = currentSum - list.get(i- subListSize) + list.get(i);
      averageSubListResult.add(currentSum/ (double) subListSize);
    }

    return averageSubListResult;
  }
}
