package slidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  public static int lengthOfLongestSubstring(String s) {
    if (s == null) {
      throw new IllegalArgumentException("String cannot be null.");
    }
    if (s.isEmpty()) {
      return 0;
    }

    int maxLength = 0;
    int left = 0;

    Map<Character, Integer> lastSeen = new HashMap<>();

    for (int right = 0; right < s.length(); right++) {
      char currentChar = s.charAt(right);

      if (lastSeen.containsKey(currentChar)) {
        left = Math.max(left, lastSeen.get(currentChar) + 1);
      }

      lastSeen.put(currentChar, right);
      maxLength = Math.max(maxLength, right - left + 1);
    }
    return maxLength;
  }

}
