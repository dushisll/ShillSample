package sample.shillsample.utils;

import java.util.Collection;

/**
 * Created by we on 2016/12/8.
 */

public class CollectionUtils {
    /**
     * 判断集合是否为null或者0个元素
     */
    public static boolean isNullOrEmpty(Collection c) {
        if (null == c || c.isEmpty()) {
            return true;
        }
        return false;
    }
}
