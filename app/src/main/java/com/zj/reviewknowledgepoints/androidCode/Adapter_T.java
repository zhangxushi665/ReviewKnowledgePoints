package com.zj.reviewknowledgepoints.androidCode;

/**
 * Created by zj on 2019-02-18 10:48.
 * 屏幕适配
 * https://blog.csdn.net/zhaokaiqiang1992/article/details/45419023
 */
public class Adapter_T {

    /**
     *  市面上主流的屏幕： 720*1280 768*1280 800*1280 1080*1920 1440*2560
     *
     *  屏幕尺寸： 屏幕的对角线长度，单位英寸，1英寸 = 2.54cm
     *  屏幕分辨率：横纵向上的像素点数，单位px，一般以纵向像素*横向像素，如1960*1080
     *  屏幕像素密度：每英寸上的像素点数，单位dpi
     *
     *      dp和dip是同一个意思，是density independent pixels缩写，即密度无关像素
     *
     *      mdpi  120-160
     *      hdpi  160-240
     *      xhdpi 240-320
     *      xxhdpi 320-480
     *      xxxhpi 480-640
     *
     *   解决方案：
     *      1.支持各种屏幕尺寸
     *          1.1 使用wrap_content、match_parent、weight
     *          1.2 使用相对布局，禁用绝对布局
     *      2.使用限定符
     *          2.1 使用尺寸限定符
     *              res/layout/main.xml 单面板（默认）布局
     *              res/layout-large/main.xml 双面板布局
     *      3.使用最小宽度限定符 （可以通过指定某个最小宽度dp为单位来定位屏幕）
     *          res/layout/main.xml
     *          res/layout-sw600dp/main.xml
     *      4.使用屏幕方向限定符
     *           .9.png 左上边代表拉伸区域，右下边代表padding box，间隔距离
     *
     *      思考： 因为分辨率不一样，所以不能用px
     *            因为屏幕宽度不一样，所以要小心使用dp
     *
     *
     * ==============================================================================
     * https://mp.weixin.qq.com/s/d9QCoBP6kV9VSWvVldVVwA
     *
     *   传统的dp适配方式缺点：
     *       .px = density * dp
     *       .density = dpi/160
     *       .px = dp * (dpi / 160)
     *
     *     缺点： 假设UI设计图是按照屏幕宽度为360dp设计，那么在设备中，屏幕宽度其实为1080/(440/160)= 392.7dp，也就是屏幕势必设计图要宽的，
     *      这种情况下，及时使用dp也是无法在不同设备中显示同样效果，同时还存在部分设备的屏幕宽度不足360dp，这样会导致以360dp宽度开发实际是显示不全的情况
     *          而且很多设备并没有按照屏幕尺寸，分辨率和像素密度规则来实现，因此dpi的值是非常乱的，没有规律可循
     *
     *    大致需求是：
     *      1.支持以宽或高一个维度去适配，保持该维度上和设计图一致
     *      2.支持dp和sp单位，控制迁移成本
     *
     *
     *
     *
     *
     *
     *
     *
     */
}
