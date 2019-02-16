/**
 * 类名：Calculator
 * 描述：中缀表达式计算器，用于使用ArrayList<Element>存储的中缀表达式的计算；
 *       提供一个getResult方法，获得中缀表达式的计算结果；
 * 版本：2.0
 */

package com.muhz.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;
import java.util.ArrayList;

public class Calculator {

    private final static int SCALE = 16;//运算结果的保留位数

    /**
     * 获得表达式计算结果
     * @param exp 要计算的中缀表达式
     * @return 计算结果
     */
    public static String getResult(ArrayList exp) {
        try {
            return getValue(exp);//计算结果
        }catch(Exception e) {
            //表达式错误时，返回“错误！”字符串
            return "错误！";
        }
    }

    /**
     * 两项式运算
     * @param num1 数值1
     * @param num2 数值2
     * @param opPop 运算符
     * @return 计算结果
     */
    private static String count(String num1, String num2, String opPop) {
        //使用BigDecimal进行运算
        BigDecimal bd1 = new BigDecimal(num1);
        BigDecimal bd2 = new BigDecimal(num2);
        BigDecimal bd3;
        String result = ""; //初始化结果
        Double value;
        switch (opPop) {
            case "+" :
                result = bd1.add(bd2).toString();
                break;
            case "-" :
                result = bd2.subtract(bd1).toString();
                break;
            case "*" :
                result = bd1.multiply(bd2).toString();
                break;
            case "/" :
                //divide方法中，SCALE参数为保留位数，ROUND_HALF_UP表示四舍五入法则
                result = bd2.divide(bd1, SCALE, RoundingMode.HALF_UP).stripTrailingZeros().toString();
                break;
            case "^" :
                result = String.valueOf(Math.pow(bd2.doubleValue(), bd1.doubleValue()));
                if (result.charAt(result.length() - 1) == '0') {
                    result = result.substring(0, result.length() - 2); // 去0
                }
                break;
            case "√" :
                BigDecimal one = new BigDecimal(1);
                // 保留32位来获得较好的运算精度
                BigDecimal temp = one.divide(bd2,32, RoundingMode.HALF_UP);
                value = Math.pow(bd1.doubleValue(), temp.doubleValue());
                result = String.valueOf(value);
                if (result.charAt(result.length() - 1) == '0') {
                    result = result.substring(0, result.length() - 2); // 去0
                }
                break;
            case "log":
                value = Math.log(bd1.doubleValue()) / Math.log(bd2.doubleValue());
                result = String.valueOf(value);
                if (result.charAt(result.length() - 1) == '0') {
                    result = result.substring(0, result.length() - 2); // 去0
                }
        }
        return result;
    }


    /**
     * 计算中缀表达式的值（核心部分！）
     * @param expression 中缀表达式
     * @return 计算结果
     * @throws （被0除，运算符不符合规定等...）
     */
    private static String getValue(ArrayList<Element> expression) throws Exception {
        Stack<Element> value = new Stack<Element>(); //数字栈
        Stack<Operator> operator = new Stack<Operator>(); //运算符栈
        String result = "0"; //结果
        for (int index = 0; index < expression.size(); index++) {
            //判断当前字符是否为运算符
            if (expression.get(index).isOperator()) {
                Operator op = expression.get(index).toOperator();
                //计算小括号中的值
                if (op.equals(")")) {
                    while (!operator.peek().equals("(")) {
                        String num1 = value.pop().value;
                        String num2 = value.pop().value;
                        result = count(num1, num2, operator.pop().value);
                        value.push(new Element(result));
                        }
                        operator.pop(); //移除“（”
                } else {
                    //运算符栈为空时，直接入栈
                    if (operator.empty()) {
                        operator.push(op);
                        } else{
                        //判断当前运算符优先级是否大于栈顶运算符
                        if (op.isPrior(operator.peek())) {
                            operator.push(op); //若大于，运算符直接入栈
                        } else {
                            //若小于等于，计算两项式，将结果入栈
                            String num1 = value.pop().value;
                            String num2 = value.pop().value;
                            result = count(num1, num2, operator.pop().value);
                            value.push(new Element(result));
                            operator.push(op);
                            }
                    }
                }
            } else {
                //将数值入栈
                value.push(expression.get(index));
            }
        }
        //计算栈中剩余的数字与运算符
        while (!operator.empty() && !value.empty()) {
            String num1 = value.pop().value;
            String num2 = value.pop().value;
            result = count(num1, num2, operator.pop().value);
            value.push(new Element(result));
        }
        return value.pop().value; //数字栈中最后剩余一个值，即计算结果，移除并返回它
    }
}
