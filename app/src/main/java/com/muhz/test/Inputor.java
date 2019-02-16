/**
 * 类名：Inputor
 * 描述：输入、显示控制器，用于控制 控件 的输入与点击效果，以及 表达式显示框 显示的内容；
 * 版本：2.0
 */
package com.muhz.test;

import android.widget.TextView;
import java.util.ArrayList;

public class Inputor {

    public String showExp; // 表达式显示字符串

    public ArrayList<Element> exception; // 储存表达式

    private TextView tv; // 表达式显示文本框

    // 表达式中可独立存在的数学符号
    private final String[] INDEPENDENT = { "-",  "√", "log", "(",};

    private final String BASE = "10";

    /**
     * 构造方法，使用要显示的文本框构造对象，并对相关属性进行初始化
     * @param tv 表达式显示文本框
     */
    public Inputor(TextView tv) {
        exception = new ArrayList<Element>();
        showExp = "";
        this.tv = tv;
    }

    /**
     * 已输入的表达式的最后一个Element，当表达式尚为空时创建一个空的Operator实例
     * @return 表达式最后一个Element
     */
    private Element last() {
        Element last;
        if (exception.isEmpty()) { // 当表达式尚为空时
            last = new Operator("?"); // 创建一个无意义的Operator，使之后的代码符合逻辑
        } else {
            last = this.exception.get(this.exception.size() - 1); // 引用表达式最后一个Element
        }
        return last;
    }

    /**
     * 判断要输入的运算符是否可独立存在（如负号、根号、括号等）
     * @param op // 将输入的运算符
     * @return // True or False
     */
    private boolean isIndep(String op) {
        for (String flag : this.INDEPENDENT) {
            if (op.equalsIgnoreCase(flag)) {
                return true;
            } else {
                // 无操作
            }
        }
        return false;
    }

    /**
     * 输入并显示数值的方法
     * @param value 要输入的数值
     */
    public void inputValue(String value) {
        if (this.last().isOperator()) { //当前上一位是运算符
            exception.add(new Element(value));
            showExp += value;
        } else {
            if (showExp.charAt(showExp.length() - 1) == 'e'){
                // 无操作
            } else {
                last().value += value;
                showExp += value;
            }
        }
        tv.setText(showExp);
    }

    public void inputE() {
        String strE = String.valueOf(Math.E);
        if (this.last().isOperator()) { //当前上一位是运算符
            this.exception.add(new Element(strE));
            showExp += "e";
        } else {
            // 无操作
        }
        this.tv.setText(this.showExp);
    }

    /**
     * 输入并显示运算符的方法
     * @param operator 要输入的运算符
     */
    public void inputOp(String operator) {
        if (last().isOperator()) {
            // 当表达式为空，或上一位是运算符时
            if (this.isIndep(operator)) {
                // 可以独立输入的运算符
                switch (operator) { // 默认的计算参数
                    case "-" : // 负数
                        exception.add(new Element("0"));
                        showExp += "0";
                        break;
                    case "√" : // 根号2
                        exception.add(new Element("2"));
                        showExp += "2";
                        break;
                    case  "log" : // lg（以10为底的对数）
                        exception.add(new Element(BASE));
                        showExp += BASE;
                        break;
                    default:
                        // 无操作
                        break;
                }
                exception.add(new Operator(operator));
                showExp += operator;
            } else {
                if (last().equals(")")) {
                    exception.add(new Operator(operator));
                    showExp += operator;
                } else {
                    // 不能输入
                }
            }
        } else {
            this.exception.add(new Operator(operator));
            showExp += operator;
        }
        this.tv.setText(this.showExp);
    }

    /**
     * 退格操作，删除上一个符号
     */
    public void delete() {
        if (last().isOperator()) { // 如果是运算符，直接删除Element
            showExp = showExp.substring(0, showExp.lastIndexOf(last().value));
            this.exception.remove(this.exception.size() - 1);
        } else { // 如果是数字，将Element的value的上一位删除
            showExp = showExp.substring(0, showExp.length() - 1);
            last().value = last().value.substring(0,last().value.length() - 1);
            if (last().value.isEmpty()) { // 当value为空时，删除这个Element
                this.exception.remove(this.exception.size() - 1);
            } else {
                // 无操作
            }
        }
        this.tv.setText(this.showExp);
    }

    /**
     * 清空操作
     */
    public void clean() {
        this.exception.clear(); // 清空表达式
        this.showExp = ""; // 显示字符串置空
        this.tv.setText(this.showExp);
    }

    /**
     * 等于操作，求值
     */
    public String getResult() {
        return Calculator.getResult(exception);
    }
}
