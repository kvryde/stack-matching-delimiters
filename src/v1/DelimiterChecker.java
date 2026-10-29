package v1;

/* Description: Uses a stack to check whether (), [], and {} are properly matched and nested.
 * Programmed by: <Zyrell Kent R Oloroso> <BSIT> <48079> <Data Structures and Algorithms>
 * Last Modified: October 23, 2026
 * Version: 1.0
 * [Acknowledgements: <I used claude to guide me on connecting it to github>]
 */
import java.util.Scanner;
import java.util.Stack;

public class DelimiterChecker {

    static boolean isOpening(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    static boolean isClosing(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    // returns the opening delimiter that a closing delimiter needs
    static char opener(char closing) {
        if (closing == ')') return '(';
        if (closing == ']') return '[';
        return '{';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter sequence: ");
        String input = sc.hasNextLine() ? sc.nextLine() : "";

        Stack<Character> stack = new Stack<>();
        String reason = null;

        for (int i = 0; i < input.length() && reason == null; i++) {
            char c = input.charAt(i);

            if (isOpening(c)) {
                stack.push(c);
            } else if (isClosing(c)) {
                if (stack.isEmpty()) {
                    reason = "Closing delimiter '" + c + "' has no matching opening delimiter.";
                } else {
                    char top = stack.peek();
                    if (top == opener(c)) {
                        stack.pop();
                    } else {
                        reason = "Mismatched delimiters: '" + c + "' does not match top '" + top + "'.";
                    }
                }
            }
            // any other character is ignored
        }

        if (reason == null && !stack.isEmpty()) {
            reason = "Unmatched opening delimiter remains: " + stack.toString().replace(", ", " ").replaceAll("^\\[|\\]$", "");
        }

        if (reason == null) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
            System.out.println("Reason: " + reason);
        }
    }
}