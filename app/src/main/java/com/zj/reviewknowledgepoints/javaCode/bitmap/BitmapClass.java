package com.zj.reviewknowledgepoints.javaCode.bitmap;

/**
 * Created by zj on 2019-02-13 15:57.
 * Bitmap如果优化，他的三级缓存的大致思想与逻辑
 * https://blog.csdn.net/qq_30993595/article/details/84025632
 */
public class BitmapClass {

    /**
     *  一般的三级缓存机制：
     *      1.内存缓存(从内存中加载图片，速度最快，不浪费流量，但会消耗手机运行内存)
     *      2.磁盘缓存或者文件缓存(从本地加载图片，速度快，不浪费流量，但是会消耗磁盘容量，不过可以忽略)
     *      3.网络缓存(从网络加载图片，速度慢，浪费流量)
     *     逻辑：每次使用图片时，先从内存缓存中取出，如果有就使用，没有从磁盘缓存中取出，有就使用并添加到内存缓存中，没有就从网络下载，下载完成后添加到内存和磁盘缓存中
     *
     *
     *  -- 内存缓存
     *      LRU算法：使用android自带的LruCache，即最近最少使用算法
     *              核心是：存储最近添加最后使用的图片，当你往里面添加要缓存的元素时，如果预先设置的容量满了，就剔除掉那些最久添加最少使用的缓存元素
     *              内部维护了一个LinkedHashMap，可以接受多种key和value
     *              public class LruCache<K,V> {
     *                  private final LinkedHashMap<K,V> map;
     *                  // 内部维护了一个双向链表，可以控制元素的迭代顺序，该顺序可以是插入顺序，也可是访问顺序，相当于是将所有的Entry节点链入一个双向链表的HashMap
     *              }
     *
     *
     *
     *
     *
     */

}
