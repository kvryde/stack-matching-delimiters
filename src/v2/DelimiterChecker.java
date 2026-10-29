package v2;

/* Description: Uses a stack to check whether (), [], and {} are properly matched and nested,
 *              and displays every stack operation (trace) while processing.
 * Programmed by: <Zyrell Kent R Oloroso> <BSIT> <48079> <Data Structures and Algorithms>
 * Last Modified: October 23, 2026
 * Version: 2.0
 * [Acknowledgements: <I used claude to draft the code and guide me on connecting it to github>]
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

    static char opener(char closing) {
        if (closing == ')') return '(';
        if (closing == ']') return '[';
        return '{';
    }

    // prints the stack from bottom to top, e.g. { [ (
    static String show(Stack<Character> stack) {
        if (stack.isEmpty()) return "(empty)";
        StringBuilder sb = new StringBuilder();
        for (char c : stack) sb.append(c).append(' ');
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter sequence: ");
        String input = sc.hasNextLine() ? sc.nextLine() : "";
        System.out.println("Input: " + input);
        System.out.println();

        Stack<Character> stack = new Stack<>();
        String reason = null;

        for (int i = 0; i < input.length() && reason == null; i++) {
            char c = input.charAt(i);

            if (isOpening(c)) {
                System.out.println("Read: " + c);
                stack.push(c);
                System.out.println("Operation: PUSH " + c);
                System.out.println("Stack: " + show(stack));
                System.out.println();
            } else if (isClosing(c)) {
                System.out.println("Read: " + c);
                if (stack.isEmpty()) {
                    reason = "Closing delimiter '" + c + "' has no matching opening delimiter (stack is empty).";
                } else {
                    char top = stack.peek();
                    System.out.println("Operation: PEEK -> " + top);
                    if (top == opener(c)) {
                        stack.pop();
                        System.out.println("Operation: POP " + top);
                        System.out.println("Stack: " + show(stack));
                        System.out.println();
                    } else {
                        reason = "Mismatched delimiters: '" + c + "' does not match top '" + top + "'.";
                    }
                }
            }
        }

        if (reason == null && !stack.isEmpty()) {
            reason = "Unmatched opening delimiter remains: " + show(stack);
        }

        if (reason == null) {
            System.out.println("Result: Valid");
        } else {
            System.out.println("Operation: INVALID DETECTED");
            System.out.println("Reason: " + reason);
            System.out.println("Result: Invalid");
        }
    }
}