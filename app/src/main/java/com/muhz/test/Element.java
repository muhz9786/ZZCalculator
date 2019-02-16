/**
 * 类名：Element
 * 描述：表达式的基本元素，用于存放表达式中的 数值 与 运算符。
 * 版本：2.0
 */

package com.muhz.test;

public class Element {

    public String value; //值

    /**
     * 使用String构造对象实例
     * @param value 值
     */
    public Element(String value) {
        this.value = value;
    }

    /**
     * 比较两个运算符字符串是否相同
     * @param strOp 运算符字符串2
     * @return True 或 Flase
     */
    public boolean equals(String strOp) {
        return (this.value.charAt(0) == strOp.charAt(0));
    }

    /**
     * 判断对象是否为符号（Operator）类
     * @return True 或 Flase
     */
    public boolean isOperator() {
        return (this instanceof Operator);
    }

    /**
     * 将父类Element实例转换为子类Operator实例
     * @return 新的Operator实例，值与原Element实例相同
     */
    public Operator toOperator() {
        return new Operator(this.value);
    }
}

