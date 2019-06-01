package me.own.learn.authorization.utils;

import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServlet;
import java.util.Arrays;
import java.util.List;


public class ArrayContainsUtils extends HttpServlet {

    public static boolean arrayAllContains(long[] hadArray, long[] needArray) {
        List<Object> asList1 = Arrays.asList(ObjectUtils.toObjectArray(hadArray));

        List<Object> asList2 = Arrays.asList(ObjectUtils.toObjectArray(needArray));

        if (asList1.containsAll(asList2)) {
            return true;
        }
        return false;
    }

    public static boolean arrayContains(int[] array1, int[] array2) {
        if (array1 != null && array2 != null) {
            for (int i = 0; i < array1.length; i++) {
                for (int j = 0; j < array2.length; j++) {
                    if (array1[i] == array2[j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean arrayContains(String[] array1, String[] array2) {
        if (array1 != null && array2 != null) {
            for (int i = 0; i < array1.length; i++) {
                for (int j = 0; j < array2.length; j++) {
                    if (array1[i].equals(array2[j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
