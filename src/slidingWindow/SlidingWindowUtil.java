package slidingWindow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * | Étape | Exercice                           | Concepts                |
 * | ----- | ---------------------------------- | ----------------------- |
 * | 1     | Somme maximale (fixe)              | base                    |
 * | 2     | Moyenne mobile                     | répétition contrôlée    |
 * | 3     | Longest substring sans répétition  | string + set            |
 * | 4     | Compter les fenêtres valides (< k) | fenêtre variable        |
 * | 5     | Maximum de chaque fenêtre (deque)  | O(n), deque             |
 * | 6     | K caractères distincts             | fenêtre dynamique + map |
 */

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

  public static int countValidWindows(List<Integer> nums, int windowSize, int k) {
    if (nums == null || windowSize <= 0 || k <= 0) {
      throw new IllegalArgumentException("Invalid Argument.");
    }
    if (nums.isEmpty() || nums.size() < windowSize) {
      return 0;
    }
    int currentWindowSum = nums.stream().limit(windowSize).mapToInt(Integer::intValue).sum();
    int totalValidWindow = currentWindowSum < k ? 1:0;

    for (int i = windowSize; i < nums.size(); i++) {
      currentWindowSum = currentWindowSum - nums.get(i-windowSize) + nums.get(i);
      if (currentWindowSum < k ) {
        totalValidWindow += 1;
      }
    }

    return totalValidWindow;
  }

  public static List<Integer> slidingWindowMax(List<Integer> nums, int windowSize) {
    if (nums == null || windowSize <= 0) {
      throw new IllegalArgumentException("Invalid Argument.");
    }
    if (nums.isEmpty() || nums.size() < windowSize) {
      return List.of();
    }

    Deque<Integer> deque = new ArrayDeque<>();
    List<Integer> result = new ArrayList<>();


    for (int i = 0; i < nums.size(); i++) {
      if (!deque.isEmpty() && deque.peekFirst() <= i - windowSize) {
        deque.pollFirst();  // On enlève l’indice trop ancien
      }
      while (!deque.isEmpty() && nums.get(deque.peekLast()) < nums.get(i)) {
        deque.pollLast();  // Ils ne peuvent plus être maximums
      }
      deque.offerLast(i);
      if (i >= windowSize - 1) {
        result.add(nums.get(deque.peekFirst()));
      }
    }

    return result;
  }

  public static int lengthOfLongestSubstringKDistinct(String s, int k) {
    if (s == null || k == 0) return 0;

    Map<Character, Integer> charCount = new HashMap<>();
    int left = 0, maxLen = 0;

    for (int right = 0; right < s.length(); right++) {
      char c = s.charAt(right);
      charCount.put(c, charCount.getOrDefault(c, 0) + 1);

      // Si plus de k caractères distincts, on contracte la fenêtre
      while (charCount.size() > k) {
        char leftChar = s.charAt(left);
        charCount.put(leftChar, charCount.get(leftChar) - 1);
        if (charCount.get(leftChar) == 0) {
          charCount.remove(leftChar);
        }
        left++;
      }

      maxLen = Math.max(maxLen, right - left + 1);
    }

    return maxLen;
  }
}
