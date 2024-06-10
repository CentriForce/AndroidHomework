package com.example.hejunzheapp.page1;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BasicCalculator {

    private static BasicCalculator basicCalculator;
    private final char[] operatorSet = {'+', '-', '×', '÷', '(', ')', '#'};
    //使用哈希表方便后面取运算符的下标
    private final Map<Character, Integer> operatorMap = new HashMap<Character, Integer>() {{
        put('+', 0);
        put('-', 1);
        put('×', 2);
        put('÷', 3);
        put('(', 4);
        put(')', 5);
        put('#', 6);
    }};
    //运算符优先级表，operPrior[oper1下标][oper2下标]
    private final char[][] operatorPriority = {
            /* (o1,o2)  +    -    ×    /    (    )    # */
            /*  +  */ {'>', '>', '<', '<', '<', '>', '>'},
            /*  -  */ {'>', '>', '<', '<', '<', '>', '>'},
            /*  ×  */ {'>', '>', '>', '>', '<', '>', '>'},
            /*  /  */ {'>', '>', '>', '>', '<', '>', '>'},
            /*  (  */ {'<', '<', '<', '<', '<', '=', ' '},
            /*  )  */ {'>', '>', '>', '>', ' ', '>', '>'},
            /*  #  */ {'<', '<', '<', '<', '<', ' ', '='},
    };

    private BasicCalculator() {
    }

    public static BasicCalculator getInstance() {
        if (basicCalculator == null) {
            basicCalculator = new BasicCalculator();
        }
        return basicCalculator;
    }

    //返回2个运算符优先级比较的结果'<','=','>'
    private char getPriority(char operator1, char operator2) {
        Integer op1 = operatorMap.get(operator1);
        Integer op2 = operatorMap.get(operator2);
        return operatorPriority[op1][op2]; //Map.get方法获取运算符的下标
    }

    //简单四则运算
    private double operate(double a, char operator, double b) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '×':
                return a * b;
            case '÷':
                if (b == 0) {
                    return Double.MAX_VALUE; //处理异常
                }
                return a / b;
            default:
                return 0;
        }
    }

    //计算普通的运算式
    private double calculator(String mathExpression) {
        if (mathExpression.isEmpty()) {
            return Double.MAX_VALUE;
        } else {
            if (hasOperator(mathExpression.substring(1))
                    || mathExpression.contains("E-")) {
                return Double.parseDouble(mathExpression);
            }

            //设置flag用于存储math开始位置的负数，如-3-5中的-3，避免-被识别成运算符而出错
            int flag = 0;
            if (mathExpression.charAt(0) == '-') {
                flag = 1;
                mathExpression = mathExpression.substring(1);
            }

            Stack<Character> operatorStack = new Stack<>(); //operator栈
            Stack<Double> numberStack = new Stack<>();     //num栈

            operatorStack.push('#'); //设置栈底元素
            mathExpression += "#";

            StringBuilder tempNum = new StringBuilder(); //暂存数字str

            //计算math
            for (int i = 0; i < mathExpression.length(); i++) {

                char charOfMath = mathExpression.charAt(i); //遍历math中的char

                //(1)num进栈
                if (!isOperator(charOfMath)                                                 //1.不是operator
                        || charOfMath == '-' && mathExpression.charAt(i - 1) == '(')    //2.是'-'并且'-'左边有'('，说明是在math中间用负数
                {
                    tempNum.append(charOfMath);

                    //1.1 获取下一个char
                    charOfMath = mathExpression.charAt(i + 1);

                    //1.2 判断下一个char是不是operator,如果是operator，就将num压入numStack
                    if (isOperator(charOfMath)) {   //此条件成功时，下次for循环就直接跳到else语句了
                        double num = Double.parseDouble(tempNum.toString());
                        if (flag == 1) {        //恢复math首位的负数
                            num = -num;
                            flag = 0;
                        }
                        numberStack.push(num); //push num
                        tempNum = new StringBuilder(); //重置tempNum
                    }
                }

                //(2)operator进栈
                else {
                    //比较栈顶与当前运算符的优先级
                    switch (getPriority(operatorStack.peek(), charOfMath)) {

                        //2.1 栈顶operator优先级低，新operator入栈
                        case '<':
                            operatorStack.push(charOfMath);
                            break;

                        //2.2 说明当前的charOfMath为')'，而栈顶operator为'('，去掉'('，其实也是math去括号的过程
                        case '=':
                            operatorStack.pop();
                            break;

                        //2.3 栈顶operator优先级高，operator出栈，并将num运算结果push进numStack
                        case '>':
                            char operator = operatorStack.pop();
                            double b = numberStack.pop();
                            double a = numberStack.pop();
                            double result = operate(a, operator, b);
                            if (result == Double.MAX_VALUE)
                                return Double.MAX_VALUE;
                            numberStack.push(result);
                            i--; //继续比较该operator与栈顶operator的关系
                            break;
                    }
                }
            }
            return numberStack.peek(); //最后的math变成一个num了
        }
    }

    //计算math，添加了一些特殊math的处理
    public double calculate(String math) {
        double result;
        if (math.isEmpty()) { //处理异常
            result = Double.MAX_VALUE;
        } else if (hasOperator(math.substring(1)) || math.contains("E-")) {
            result = Double.parseDouble(math);
        }
        //普通运算
        else {
            result = calculator(math);
        }
        return result;
    }

    //判断String中是否有运算符
    private boolean hasOperator(String s) {
        return !s.contains("+") && !s.contains("-") && !s.contains("×") && !s.contains("÷");
    }

    //判断字符是否为运算符
    private boolean isOperator(char c) {
        boolean result = false;
        for (char operator : operatorSet) {
            result = result || operator == c;
        }
        return result;
    }
}