package de.comparus.opensource.longmap;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Fork(1)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@BenchmarkMode(Mode.AverageTime)
@Threads(1)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class LongMapBenchmarkTest {

    private static final int COUNT = 100_000;

    private static HashMap<Long, Object> hashMap;
    private static LongMap<Object> longMap;


    public static void main(String[] args) throws Exception {
        Main.main(args);
    }

    @Setup
    public static void setup() {
        hashMap = new HashMap<>(COUNT);
        longMap = new LongMapImpl<>(Object.class);

        for (int i = 0; i < COUNT; i++) {
            hashMap.put((long) i, new Object());
            longMap.put((long) i, new Object());
        }
    }

    @Benchmark
    public void removePutHashMap(Blackhole blackhole) throws Exception {
        Object object = new Object();
        hashMap.remove((long) (COUNT / 2));
        hashMap.put((long) (COUNT / 2), object);
    }

    @Benchmark
    public void removeputLongMap(Blackhole blackhole) throws Exception {
        Object object = new Object();
        longMap.remove((long) (COUNT / 2));
        longMap.put((long) (COUNT / 2), object);
    }


    @Benchmark
    public void getFromHashMap(Blackhole blackhole) throws Exception {
        hashMap.get((long) (COUNT / 2));
    }

    @Benchmark
    public void getFromLongMap(Blackhole blackhole) throws Exception {
        longMap.get((long) (COUNT / 2));
    }

    @Benchmark
    public void getValuesFromHashMap(Blackhole blackhole) throws Exception {
        hashMap.values();
    }

    @Benchmark
    public void getValuesFromLongMap(Blackhole blackhole) throws Exception {
        longMap.values();
    }

    @Benchmark
    public void getKeysFromHashMap(Blackhole blackhole) throws Exception {
        hashMap.keySet();
    }

    @Benchmark
    public void getKeysFromLongMap(Blackhole blackhole) throws Exception {
        longMap.keys();
    }

    @Benchmark
    public void containsKeyHashMap(Blackhole blackhole) throws Exception {
        hashMap.containsKey((long) (COUNT / 2));
    }

    @Benchmark
    public void containsKeysLongMap(Blackhole blackhole) throws Exception {
        longMap.containsKey((long) (COUNT / 2));
    }

    @Benchmark
    public void containsValueHashMap(Blackhole blackhole) throws Exception {
        hashMap.containsValue((long) (COUNT / 2));
    }

    @Benchmark
    public void containsValueLongMap(Blackhole blackhole) throws Exception {
        longMap.containsValue((long) (COUNT / 2));
    }
}
