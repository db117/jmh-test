package cn.db117.jmh;


import lombok.extern.slf4j.Slf4j;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * string遍历测试
 *
 * @author db117
 */
@Slf4j
@State(Scope.Benchmark)
public class StringForEachJmhTest {
    public String testString;
    private int len = 10000;


    @Setup
    public void buildTestData() {

        StringBuilder b = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            b.append((char) i % 128);
        }

        testString = b.toString();
    }

    @Benchmark
    @Fork(1)
    public void charAt() {
        // 61891.803 ±(99.9%) 11459.355 ops/s [Average]
        //  (min, avg, max) = (58161.438, 61891.803, 65503.619), stdev = 2975.958
        //  CI (99.9%): [50432.448, 73351.158] (assumes normal distribution)
        for (int i = 0; i < len; i++) {
            char c = testString.charAt(i);

        }
    }

    @Benchmark
    @Fork(1)
    public void toCharArray() {
        // 214710.770 ±(99.9%) 19171.402 ops/s [Average]
        //  (min, avg, max) = (210979.139, 214710.770, 222442.483), stdev = 4978.752
        //  CI (99.9%): [195539.368, 233882.172] (assumes normal distribution)
        char[] chars = testString.toCharArray();
        for (char c : chars) {

        }
    }
}
