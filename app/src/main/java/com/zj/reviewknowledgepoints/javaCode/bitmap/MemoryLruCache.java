package com.zj.reviewknowledgepoints.javaCode.bitmap;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import android.util.LruCache;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zj on 2019-02-13 16:18.
 * 模拟Lrucache
 */
public class MemoryLruCache {

    public static final String TAG = MemoryLruCache.class.getSimpleName();

    private LruCache<String, Bitmap> mMemoryCache;
    private MemoryLruCache           instance;

    private MemoryLruCache() {

        long maxMemory = Runtime.getRuntime().maxMemory();
        int cache = (int) (maxMemory / 8);

        mMemoryCache = new LruCache<String, Bitmap>(cache) {
            //回收内存
            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                super.entryRemoved(evicted, key, oldValue, newValue);
                if (oldValue != null && !oldValue.isRecycled()) {
                    oldValue.recycle();
                }
            }

            //计算一张图片所占内存
            @SuppressLint("ObsoleteSdkInt")
            @Override
            protected int sizeOf(String key, Bitmap value) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//19
                    int size = value.getAllocationByteCount();
                    return size;
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {//12
                    return value.getByteCount();
                }

                // 低版本中使用bitmap所占的内存数等于bitmap的每一行所占的空间数乘以bitmap行数
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    public MemoryLruCache getInstance() {
        if (null == instance) {
            instance = new MemoryLruCache();
        }
        return instance;
    }

    public Bitmap getBitmap(String key) {
        if (TextUtils.isEmpty(key)) {
            return null;
        } else {
            return mMemoryCache.get(key);
        }
    }

    public void putBitmap(String key, Bitmap bitmap) {
        mMemoryCache.put(key, bitmap);
    }


    // 放在activity中清除不需要的bitmap
    public void cleanCache(String[] urls) {

        try {
            Class<?> aClass = Class.forName("android.util.LruCache");
            Field field = aClass.getDeclaredField("map");
            field.setAccessible(true);
            LinkedHashMap<String, Bitmap> map = (LinkedHashMap<String, Bitmap>) field.get(mMemoryCache);

            if (map == null)
                return;

            Iterator<Map.Entry<String, Bitmap>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {

                Map.Entry<String, Bitmap> entry = iterator.next();
                Bitmap bit = entry.getValue();

                if (urls != null && urls.length > 0) {
                    for (int i = 0; i < urls.length; i++) {
                        if (TextUtils.equals(entry.getKey(), urls[i])) {
                            if (bit != null && !bit.isRecycled()) {
                                bit.recycle();
                            }
                            iterator.remove();
                            break;
                        }
                    }
                } else {
                    if (bit != null && !bit.isRecycled()) {
                        bit.recycle();
                    }
                    iterator.remove();
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
