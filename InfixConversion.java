import java.util.Stack;
import java.util.Scanner;

public class InfixConversion {

    // Function to define operator precedence
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    // Function to convert Infix to Postfix
    static String infixToPostfix(String exp) {
        String result = "";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // If operand, add to result
            if (Character.isLetterOrDigit(c)) {
                result += c;
            }

            // If '(', push to stack
            else if (c == '(') {
                stack.push(c);
            }

            // If ')', pop until '('
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                stack.pop(); // remove '('
            }

            // If operator
            else {
                while (!stack.isEmpty() &&
                        precedence(stack.peek()) >= precedence(c)) {
                    result += stack.pop();
                }
                stack.push(c);
            }
        }

        // Pop remaining operators
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    // Function to reverse expression and swap brackets
    static String reverse(String exp) {
        String result = "";

        for (int i = exp.length() - 1; i >= 0; i--) {
            char c = exp.charAt(i);

            if (c == '(')
                result += ')';
            else if (c == ')')
                result += '(';
            else
                result += c;
        }

        return result;
    }

    // Function to convert Infix to Prefix
    static String infixToPrefix(String exp) {
        String reversed = reverse(exp);
        String postfix = infixToPostfix(reversed);

        // Reverse postfix to get prefix
        return new StringBuilder(postfix).reverse().toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Infix Expression: ");
        String infix = scanner.nextLine();

        String postfix = infixToPostfix(infix);
        String prefix = infixToPrefix(infix);

        System.out.println("Postfix Expression: " + postfix);
        System.out.println("Prefix Expression: " + prefix);

        scanner.close();
    }
}