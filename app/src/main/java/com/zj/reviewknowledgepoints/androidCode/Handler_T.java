package com.zj.reviewknowledgepoints.androidCode;

/**
 * Created by zj on 2019-02-21 9:58.
 *  android 消息机制 主要指的是Handler运行机制以及Handler所附带的MessageQueue和Looper的工作过程
 */
public class Handler_T {

    /**
     *  Handler 运行底层主要是靠MessageQueue和Looper的支撑
     *      messageQueue消息队列，内部存储结构不是真正的队列，而是采用了单链表的数据结构来存储消息列表的
     *      Lopper 消息循环
     *      流程：由于MessageQueue只是一个消息的存储单元，不能去处理消息，而Looper就填补这个功能，Looper会以无限循环的形式去查找是否有新消息，
     *          如果有的话就处理消息，否则就一直等待着。
     *      Looper中还有一个特殊的概念，就是ThreadLocal，ThreadLocal并不是线程，它的作用是可以在每个线程中存储数据，
     *          我们知道Handler创建的时候会采用当前线程的Looper来构造消息循环系统，那么Handler内部如何获取当前的线程Looper呢？
     *              这就要使用ThreadLocal，它可以在不同线程中互不干扰的存储并提供数据，通过ThreadLocal可以轻松的获取每个线程的Looper，
     *                  当然注意的是，线程是默认没有Looper的，如果需要使用Handler就必须为线程创建Looper。
     *                  我们常常提到的UI线程，就是ActivityThread，ActivityThread被创建时就会初始化Looper，也就是主线程中默认可以使用Handler的原因。
     *
     *
     *   过程： Handler创建完毕后，这个时候其内部的Looper以及MessageQueue就可以和Handler一起协同工作了，然后通过Handler的post的方法将一个Runnable投递到
     *          Handler内部的Looper中去处理，也可以通过Handler的send方法来完成，这个消息童谣会在Looper中去处理，其实post的方法最终也是通过send的方法来完成的
     *
     *
     *   handler工作原理：
     *      handler的工作主要包括消息的发送和接收的过程，消息发送是通过post以及send一系列方法来实现的
     *         handler发送消息过程仅仅是向消息队列中插入一条消息，MessageQueue的next方法就会返回这条消息给Looper，
     *          Looper收到消息后就开始处理，最终消息由looper交由handler处理，即handler的dispatchMessage方法会被调用，这时就进入了处理阶段。
     *
     *
     *
     */
}
