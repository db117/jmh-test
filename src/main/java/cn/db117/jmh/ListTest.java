package cn.db117.jmh;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.*;

/**
 * 对比arrayList LinkedList
 *
 * @author db117
 * @date 2021/5/20
 */
@Slf4j
@State(Scope.Benchmark)
public class ListTest {

    private List<Integer> data;

    @Setup
    public void setup() {
        Random random = new Random();
        data = new ArrayList<>(10000);

        for (int i = 0; i < 10000; i++) {
            data.add(random.nextInt());
        }
    }


    @Benchmark
    public void arrayList() {
        // Result "cn.db117.jmh.ListTest.arrayList":
        //  287.337 ±(99.9%) 16.866 ops/s [Average]
        //  (min, avg, max) = (217.109, 287.337, 319.942), stdev = 22.515
        //  CI (99.9%): [270.472, 304.203] (assumes normal distribution)
        ArrayList<Integer> list = new ArrayList<>(data);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }

    @Benchmark
    public void lindedList() {
        // Result "cn.db117.jmh.ListTest.lindedList":
        //  8153.211 ±(99.9%) 1461.098 ops/s [Average]
        //  (min, avg, max) = (2902.604, 8153.211, 9599.010), stdev = 1950.525
        //  CI (99.9%): [6692.112, 9614.309] (assumes normal distribution)
        List<Integer> list = new LinkedList<>(data);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }
}
