package com.zj.reviewknowledgepoints.javaCode;

/**
 * Created by zj on 2019-02-14 16:59.
 * 手写三种单例模式，冒泡排序
 * http://wuchong.me/blog/2014/08/28/how-to-correctly-write-singleton-pattern/
 * https://blog.csdn.net/u011109881/article/details/80038573
 */
public class Comparator_T {

    /*//冒泡排序
    public static void compareSortOf(int[] a) {

        for (int i = 0; i < a.length - 1; i++) {

            for (int j = 0; j < a.length - 1 - i; j++) {

                if (a[j] > a[j + 1]) {

                    int temp = a[j + 1];

                    a[j + 1] = a[j];

                    a[j] = temp;
                }
            }
        }
    }*/

    /*// 选择排序
    public static void selectSort(int[] a) {

        int minIndex;

        for (int i = 0; i < a.length - 1; i++) {

            minIndex = i;

            for (int j = i; j < a.length - 1; j++) {

                if (a[minIndex] > a[j + 1]) {

                    minIndex = j + 1;

                }
            }

            int temp = a[minIndex];

            a[minIndex] = a[i];

            a[i] = temp;

        }

    }*/
}
