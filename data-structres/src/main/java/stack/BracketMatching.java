package stack;

import java.util.Stack;


/**
 * 用于检查给定的包含圆括号 “()”、方括号 “[ ]” 和花括号 “{ }” 且可任意嵌套的表达式字符串中括号是否匹配。
 * 程序应从左至右依次扫描字符串，遇到左括号时将其压入栈中，遇到右括号时从栈顶取出一个左括号进行匹配检查，
 * 若匹配则继续扫描后续字符串，若遇到不能配对的右括号或者栈为空，则判定表达式为非法格式。
 * 当整个字符串扫描完毕后，若栈为空，则判定表达式为合法格式，否则判定表达式为非法格式，程序最终需输出表达式是否合法的结果。
 */
public class BracketMatching {
    public static boolean isBracketsMatched(String expression) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((ch == ')' && top!= '(') ||
                    (ch == ']' && top!= '[') ||
                    (ch == '}' && top!= '{')) return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String expression1 = "{[31gad3f(as3d5g4)]}";
        String expression2 = "[{f(})8909090]";
        System.out.println(expression1 + " is matched: " + isBracketsMatched(expression1));
        System.out.println(expression2 + " is matched: " + isBracketsMatched(expression2));
    }
}