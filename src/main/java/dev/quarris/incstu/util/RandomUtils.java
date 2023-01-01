package dev.quarris.incstu.util;

import java.util.Random;

public class RandomUtils {

    private static final Random RANDOM = new Random();

    public static Random get() {
        return RANDOM;
    }
}
