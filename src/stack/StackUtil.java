package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * Valid Parentheses
 * Min Stack
 * Daily Temperatures
 * Evaluate Reverse Polish Notation
 * Simplify Path
 * Largest Rectangle in Histogram (plus avancé)
 */
public class StackUtil {

  public static boolean isValidParentheses(String s) {
    if (s.isBlank()) {
      throw new IllegalArgumentException("String cannot be empty");
    }
    Map<Character, Character> matchingParentheses = Map.of(
            ')', '(',
            ']', '[',
            '}', '{'
    );
    Deque<Character> stack = new ArrayDeque<>();

    for (int i = 0; i < s.length(); i++) {
      Character c = s.charAt(i);
      if (matchingParentheses.containsValue(c)) {
        stack.addLast(c);
      } else if (matchingParentheses.containsKey(c)) {
        if (stack.isEmpty() || !stack.peekLast().equals(matchingParentheses.get(c))) {
          return false;
        }
        stack.pollLast();
      }
    }
    return stack.isEmpty();
  }


}
