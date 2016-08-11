package com.bianmaren.util;

import java.util.Comparator;

/**
 * Created by bianmaren on 2016-02-04.
 * QQ:441889070
 */
public class MapKeyComparator implements Comparator<String> {
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}