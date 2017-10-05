/**
 * Filename:     CalenderPrinter.java
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

import java.text.DateFormatSymbols;
import java.util.Calendar;

// 如果需要使用大量带有冗长名字的常量，就应该使用"静态导入"
// 静态导入还有一个应用场景：算数函数
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.MONTH;

import java.util.GregorianCalendar;
import java.util.Locale;

public class CalenderPrinter {

    public static void main(String[] args) {
//        Locale.setDefault(Locale.FRANCE);

        CalenderPrinter printer = new CalenderPrinter();
        printer.printCurMonth();
    }

    private void printCurMonth() {
        // 默认返回当前的日期和时间
        Calendar calendar = new GregorianCalendar();
        // 当前月份
        int curMonth = calendar.get(MONTH);
        // 当前日期
        int curDay = calendar.get(DAY_OF_MONTH);

        // calculate indents of the first day
        int indents = calculateIndentOfFirstDay();

        // print title
        printCalenderTitle();

        // print days
        printDays(calendar, curMonth, curDay, indents);

    }

    private int calculateIndentOfFirstDay() {
        // 为了不还原printCurMonth()中的calendar日期，这里新创建一个当前时间的Calendar对象
        Calendar d = new GregorianCalendar();
        // 获取当前地区星期的起始日(星期几)
        int firstDayOfWeek = d.getFirstDayOfWeek();

        // 将d设置为这个月的第一天，并得到这一天为星期几
        d.set(DAY_OF_MONTH, 1);
        int weekDay = d.get(DAY_OF_WEEK);

        // 看看需要退几步才能到这个月星期的起始日
        int indent = 0;
        while (firstDayOfWeek != weekDay) {
            indent++;
            d.add(DAY_OF_MONTH, -1);
            weekDay = d.get(DAY_OF_WEEK);
        }
        return indent;
    }

    private void printCalenderTitle() {
        String[] weekdays = new DateFormatSymbols().getShortWeekdays();
        // 数组0是空值，所以这里跳过0从1开始打印
        for (int i = 1; i < weekdays.length; i++) {
            // TODO: 确认String的format意义
            System.out.printf("%6s", weekdays[i]);
        }
//        System.out.println();
    }

    private void printDays(Calendar calendar, int curMonth, int curDay, int indents) {
        boolean firstPrint = true;
        // 从头打印
        calendar.set(DAY_OF_MONTH, 1);
        int firstDayOfWeek = calendar.getFirstDayOfWeek();

        // 一旦calendar进入下个月，就终止打印
        while (calendar.get(MONTH) == curMonth) {
            int printDay = calendar.get(DAY_OF_MONTH);

            // 每个星期的第一天换行打印
            int printMonth = calendar.get(DAY_OF_WEEK);
            if (printMonth == firstDayOfWeek) {
                System.out.println();
            }

            // 第一行缩进
            if (firstPrint) {
                for (int i = 0; i < indents; i++) {
                    System.out.printf("%6s", " ");
                }
                firstPrint = false;
            }

            // 如果calendar是当前日期，打印日期之后再打印一个*标记
            if (printDay == curDay) {
                System.out.printf("%5s*", printDay);
            } else {
                System.out.printf("%6s", printDay);
            }
            // 将calendar设置为下一天
            calendar.add(DAY_OF_MONTH, 1);
        }
    }

}
