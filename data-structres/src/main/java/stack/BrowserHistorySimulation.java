package stack;

import java.util.Stack;

/**
 * 一个模拟网页浏览器页面浏览历史操作（后退、前进功能）的程序，
 * 使用两个栈（分别命名为X和Y）来管理页面浏览历史。
 * 首次浏览的页面依次压入栈X，点击后退按钮时，将栈X中的页面依次出栈并压入栈Y；
 * 点击前进按钮时，把栈Y中的页面依次取出并压入栈X。
 * 同时，当栈X为空时表示没有页面可继续后退浏览，栈Y为空时表示没有页面可点击前进按钮浏览，
 * 程序需能体现上述操作逻辑并能正确判断可后退、可前进的情况。
 */
public class BrowserHistorySimulation {

    private final Stack<String> stackX; // 用于存储浏览历史（正向顺序）
    private final Stack<String> stackY; // 用于辅助实现前进后退功能

    public BrowserHistorySimulation() {
        stackX = new Stack<>();
        stackY = new Stack<>();
    }

    // 访问新页面，将页面压入栈X
    public void visitPage(String page) {
        stackX.push(page);
        // 每次访问新页面时，清空栈Y，因为新的浏览历史开始了，之前的前进历史失效
        stackY.clear(); 
    }

    // 点击后退按钮，从栈X出栈并压入栈Y
    public String goBack() {
        if (!stackX.isEmpty()) {
            String page = stackX.pop();
            stackY.push(page);
            return page;
        }
        return null; // 如果栈X为空，说明没有页面可后退，返回null
    }

    // 点击前进按钮，从栈Y出栈并压入栈X
    public String goForward() {
        if (!stackY.isEmpty()) {
            String page = stackY.pop();
            stackX.push(page);
            return page;
        }
        return null; // 如果栈Y为空，说明没有页面可前进，返回null
    }

    public static void main(String[] args) {
        BrowserHistorySimulation browser = new BrowserHistorySimulation();

        String pageA = "a";
        String pageB = "b";
        String pageC = "c";

        browser.visitPage(pageA);
        System.out.println("用户点击了页面: " + pageA);
        browser.visitPage(pageB);
        System.out.println("用户点击了页面: " + pageB);
        browser.visitPage(pageC);
        System.out.println("用户点击了页面: " + pageC);

        System.out.println("当前后退，返回页面: " + browser.goBack());
        System.out.println("当前后退，返回页面: " + browser.goBack());
        System.out.println("当前前进，返回页面: " + browser.goForward());
        System.out.println("当前前进，返回页面: " + browser.goForward());

    }
}