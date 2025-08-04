package slidingWindow;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    // Sum Sliding Window
    List<Integer> listSum = List.of(1, 4, 2, 10, 23, 3, 1, 0, 20);
    int k1 = 4;
    int resultSumMax = SlidingWindowUtil.getSumMaxValueWindow(listSum, k1);
    System.out.println("Sliding window sum max -> " + resultSumMax);

    // Average Sliding Window
    List<Integer> listAvg = List.of(2, 4, 6, 8, 10);
    int k2 = 3;
    List<Double> resultAvgSubList = SlidingWindowUtil.getAverageSubListWindow(listAvg, k2);
    System.out.println("Sliding window avg -> " + resultAvgSubList);

    // Length Of Longest Substring
    String stringTest = "abcdeabcdefg";
    int resultOfLongestSubstring = SlidingWindowUtil.lengthOfLongestSubstring(stringTest);
    System.out.println("Length Of Longest Substring -> " + resultOfLongestSubstring);
  }
}