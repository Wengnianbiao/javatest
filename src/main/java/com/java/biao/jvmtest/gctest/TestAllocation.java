package com.java.biao.jvmtest.gctest;

/**
 * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 */
public class TestAllocation {
    private static final int _1MB = 1024 * 1024;
    // Allocation Failure是触发GC的原因是分配对象内存空间失败触发的。在GC开始时，年轻代占用了6165K的内存。在GC结束时，
    // 年轻代减少到460K。年轻代的总容量为9216K。这次GC花费了0.0028160秒。
//    [GC (Allocation Failure) [DefNew: 6165K->460K(9216K), 0.0028160 secs] 6165K->4556K(19456K), 0.0029778 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
//    Heap
//    def new generation   total 9216K, used 6926K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
//    eden space 8192K,  78% used [0x00000007bec00000, 0x00000007bf2505f8, 0x00000007bf400000)
//    from space 1024K,  44% used [0x00000007bf500000, 0x00000007bf5732b0, 0x00000007bf600000)
//    to   space 1024K,   0% used [0x00000007bf400000, 0x00000007bf400000, 0x00000007bf500000)
//    tenured generation   total 10240K, used 4096K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
//    the space 10240K,  40% used [0x00000007bf600000, 0x00000007bfa00020, 0x00000007bfa00200, 0x00000007c0000000)
//    Metaspace       used 3172K, capacity 4496K, committed 4864K, reserved 1056768K
//    class space    used 349K, capacity 388K, committed 512K, reserved 1048576K
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];  // 出现一次Minor GC
    }

    public static void main(String[] args) {
        testAllocation();
    }
}
