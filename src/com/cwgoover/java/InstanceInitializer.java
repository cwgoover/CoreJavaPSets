/**
 * Filename:     InstanceInitializer.java
 * Description:
 * Author:       CAO Ting
 * Version:      1.0
 * Create at:    03/10/2017
 * Modification History:
 * Date             Author        Version     Description
 * ------------------------------------------------------------------
 * 03/10/2017        caozangzang     1.0       1.0 Version
 */
package com.cwgoover.java;

public class InstanceInitializer {

    // instance variable initializer 实例变量初始化器
    private String s = "adb";

    // constructor 构造函数
    public InstanceInitializer() {
        System.out.println("constructor called");
    }

    // static initializer 静态初始化器
    static {
        System.out.println("static initializer called");
    }

    // 使用场景：
    // 初始化代码必须处理异常情况
    // 执行一个实例变量没办法进行的计算
    // 匿名内部类(这种情况下根本不能创建构造函数)

    // instance initializer 实例初始化器
    {
        // 想象成变量赋值的过程
        System.out.println("instance initializer called");
    }

    public static void main(String[] args) {
        new InstanceInitializer();

        System.out.println();
        new InstanceInitializer();
    }
}
