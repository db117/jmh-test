package cn.db117.jmh;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jmh.annotations.*;

import java.util.HashMap;
import java.util.Map;

/**
 * string拼接测试
 *
 * @author db117
 * @since 2020/7/29 16:11
 */
@Slf4j
@State(Scope.Benchmark)
public class StringAppendJmhTest {

    private Map<String, String> data;

    @Setup
    public void buildTestData() {


        data = new HashMap<>();

        for (int i = 0; i < 10000; i++) {
            data.put(i + "k", i + "v");
        }
    }

    @Benchmark
    @Fork(1)
    public void add() {
        // 2581.922 ±(99.9%) 446.054 ops/s [Average]
        //  (min, avg, max) = (2469.446, 2581.922, 2726.632), stdev = 115.839
        //  CI (99.9%): [2135.868, 3027.975] (assumes normal distribution)
        data.forEach((s, s2) -> {
            String res = s + s2;
        });
    }

    @Benchmark
    @Fork(1)
    public void format() {
        // 130.687 ±(99.9%) 3.448 ops/s [Average]
        //  (min, avg, max) = (129.309, 130.687, 131.480), stdev = 0.895
        //  CI (99.9%): [127.239, 134.136] (assumes normal distribution)
        data.forEach((s, s2) -> {
            String res = String.format("%s%s", s, s2);
        });
    }

    @Benchmark
    @Fork(1)
    public void stringBuild() {
        //   3292.527 ±(99.9%) 108.735 ops/s [Average]
        //  (min, avg, max) = (3248.656, 3292.527, 3317.552), stdev = 28.238
        //  CI (99.9%): [3183.791, 3401.262] (assumes normal distribution)
        data.forEach((s, s2) -> {
            String res = new StringBuilder().append(s).append(s2).toString();
        });
    }


}
