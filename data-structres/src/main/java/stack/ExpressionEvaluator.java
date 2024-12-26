package stack;

import java.util.Stack;

/**
 *开发一个能够实现对只包含加减乘除四则运算的算术表达式进行求值的程序。
 * 该程序需模仿编译器利用两个栈（一个操作数栈和一个运算符栈）来实现求值功能，
 * 具体实现方式为从左向右遍历表达式，数字直接压入操作数栈，运算符与运算符栈顶元素比较优先级，
 * 若优先级高则压入运算符栈，若优先级低或相同，则从运算符栈取栈顶运算符，
 * 从操作数栈取 2 个操作数进行计算，将结果压入操作数栈后继续比较，
 * 直至表达式遍历完毕得出最终结果。
 */
public class ExpressionEvaluator {
    public static int evaluate(String expression) {
        Stack<Integer> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                int num = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num = num * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                i--;
                operandStack.push(num);
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!operatorStack.isEmpty() && hasHigherPrecedence(operatorStack.peek(), ch)) {
                    int result = performOperation(operatorStack.pop(), operandStack.pop(), operandStack.pop());
                    operandStack.push(result);
                }
                operatorStack.push(ch);
            }
        }

        while (!operatorStack.isEmpty()) {
            int result = performOperation(operatorStack.pop(), operandStack.pop(), operandStack.pop());
            operandStack.push(result);
        }

        return operandStack.pop();
    }

    private static boolean hasHigherPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        return (op1 != '*' && op1 != '/') || (op2 != '+' && op2 != '-');
    }

    private static int performOperation(char operator, int b, int a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return 0;
    }

    public static void main(String[] args) {
        String expression = "34+13*9+44-12/3";
        int result = evaluate(expression);
        System.out.println("The result of the expression：" + expression + " is: " + result);
    }
}