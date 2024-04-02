package com.shok.planets.mytest;

import com.shok.planets.utils.AlgorithmUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
* 算法工具类测试
*/
public class AlgorithmUtilsTest {

    @Test
    void test() {
        String str1 = "一只狗";
        String str2 = "一只猫";
        String str3 = "一只兔子";
        //        String str4 = "鱼皮是猫";
        // 1
        int score1 = AlgorithmUtils.minDistance(str1, str2);
        // 3
        int score2 = AlgorithmUtils.minDistance(str1, str3);
        System.out.println(score1);
        System.out.println(score2);
    }

    @Test
    void testCompareTags() {
        List<String> tagList1 = Arrays.asList("Java", "大一", "男");
        List<String> tagList2 = Arrays.asList("Java", "大一", "女");
        List<String> tagList3 = Arrays.asList("Python", "大二", "女");
        List<String> tagList4 = Arrays.asList("Java", "大一", "男","摄影","羽毛球","音乐");
        // 1
        int score1 = AlgorithmUtils.minDistance(tagList1, tagList2);
        // 3
        int score2 = AlgorithmUtils.minDistance(tagList1, tagList3);

        int score3 = AlgorithmUtils.minDistance(tagList1, tagList4);
        System.out.println(score1);
        System.out.println(score2);
        System.out.println(score3);
    }
}