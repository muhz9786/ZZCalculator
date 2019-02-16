/**
 * 类名：Operator
 * 父类：Element
 * 描述：继承Element类，用于专门存放 运算符 对象
 * 版本：2.0
 */

package com.muhz.test;

public class Operator extends Element {

    /**
     * 使用String构造对象实例（与父类相同）
     * @param value 值
     */
    public Operator(String value){
        super(value);
    }

    /**
     * 判断一个String类型元素是否存在于某个String数组中
     * @param flagArray 要对比的字符串数组
     * @return True 或 False
     */
    private boolean inStringArray(String[] flagArray) {
        for (String flag : flagArray) {
            if (this.equals(flag)) {
                return true;
            } else {
                //不进行操作
            }
        }
        return false;
    }

    /**
     * 判断当前运算符 优先级 是否大于 另一运算符
     * @param op 比较的运算符
     * @return True 大于, False 小于等于
     */
    public boolean isPrior(Operator op) {
        final String[] TOP1 = {"("}; //类别1
        final String[] TOP2 = {"*", "/", "√", "^", "log"}; //类别2
        final String[] TOP3 = {"+", "-"}; //类别3
        int opThis = 0; //初始化当前运算符相对优先级
        int opParam = 0; //初始化栈顶运算符相对优先级
        if (this.inStringArray(TOP1)) {
            opThis = 1; //“（”在当前运算符时，优先级最高
        } else {
            if (this.inStringArray(TOP2)) {
                opThis = 2;
            } else {
                if (this.inStringArray(TOP3)) {
                    opThis = 3;
                } else {
                    //当增加新的优先级时，从此继续添加代码
                }
            }
        }
        if (op.inStringArray(TOP1)) {
            opParam = 4; //“（”在栈顶时，优先级最低
        } else {
            if (op.inStringArray(TOP2)) {
                opParam = 2;
            } else {
                if (op.inStringArray(TOP3)) {
                    opParam = 3;
                } else {
                    //当增加新的优先级时，从此继续添加代码
                }
            }
        }
        return (opThis < opParam);
    }
}
