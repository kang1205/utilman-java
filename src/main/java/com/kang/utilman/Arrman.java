package com.kang.utilman;

import java.util.Objects;
import java.util.Optional;

/**
 * A Array manipulation without any dependencies
 *
 * @author kang
 */
public class Arrman {

    private Arrman() {
    }

    public static <T> int size(final T[] array) {
        return Objects.isNull(array) ? 0 : array.length;
    }

    public static int size(final char[] array) {
        return Objects.isNull(array) ? 0 : array.length;
    }

    public static int size(final byte[] array) {
        return Objects.isNull(array) ? 0 : array.length;
    }

    public static int size(final short[] array) {
        return Objects.isNull(array) ? 0 : array.length;
    }

    public static int size(final float[] array) {
        return Objects.isNull(array) ? 0 : array.length;
    }

    public static int size(final int[] array) {
        return Objects.isNull(array) ? 0 : array.length;
    }

    public static int size(final double[] array) {
        return Objects.isNull(array) ? 0 : array.length;
    }

    public static int size(final long[] array) {
        return Objects.isNull(array) ? 0 : array.length;
    }

    public static <T> boolean isEmpty(final T[] array) {
        return size(array) == 0;
    }

    public static boolean isEmpty(final byte[] array) {
        return size(array) == 0;
    }

    public static boolean isEmpty(final char[] array) {
        return size(array) == 0;
    }

    public static boolean isEmpty(final short[] array) {
        return size(array) == 0;
    }

    public static boolean isEmpty(final int[] array) {
        return size(array) == 0;
    }

    public static boolean isEmpty(final float[] array) {
        return size(array) == 0;
    }

    public static boolean isEmpty(final double[] array) {
        return size(array) == 0;
    }

    public static boolean isEmpty(final long[] array) {
        return size(array) == 0;
    }

    public static <T> Optional<T> get(final T[] array, int index) {
        int size = size(array);
        if (size == 0) {
            return Optional.empty();
        }
        index = index < 0 ? size + index : index;
        if (index < 0 || index >= size) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(array[index]);
        }
    }

    public static Optional<Byte> get(final byte[] array, int index) {
        int size = size(array);
        if (size == 0) {
            return Optional.empty();
        }
        index = index < 0 ? size + index : index;
        if (index < 0 || index >= size) {
            return Optional.empty();
        } else {
            return Optional.of(array[index]);
        }
    }

    public static Optional<Character> get(final char[] array, int index) {
        int size = size(array);
        if (size == 0) {
            return Optional.empty();
        }
        index = index < 0 ? size + index : index;
        if (index < 0 || index >= size) {
            return Optional.empty();
        } else {
            return Optional.of(array[index]);
        }
    }

    public static Optional<Short> get(final short[] array, int index) {
        int size = size(array);
        if (size == 0) {
            return Optional.empty();
        }
        index = index < 0 ? size + index : index;
        if (index < 0 || index >= size) {
            return Optional.empty();
        } else {
            return Optional.of(array[index]);
        }
    }

    public static Optional<Integer> get(final int[] array, int index) {
        int size = size(array);
        if (size == 0) {
            return Optional.empty();
        }
        index = index < 0 ? size + index : index;
        if (index < 0 || index >= size) {
            return Optional.empty();
        } else {
            return Optional.of(array[index]);
        }
    }

    public static Optional<Float> get(final float[] array, int index) {
        int size = size(array);
        if (size == 0) {
            return Optional.empty();
        }
        index = index < 0 ? size + index : index;
        if (index < 0 || index >= size) {
            return Optional.empty();
        } else {
            return Optional.of(array[index]);
        }
    }

    public static Optional<Double> get(final double[] array, int index) {
        int size = size(array);
        if (size == 0) {
            return Optional.empty();
        }
        index = index < 0 ? size + index : index;
        if (index < 0 || index >= size) {
            return Optional.empty();
        } else {
            return Optional.of(array[index]);
        }
    }

    public static Optional<Long> get(final long[] array, int index) {
        int size = size(array);
        if (size == 0) {
            return Optional.empty();
        }
        index = index < 0 ? size + index : index;
        if (index < 0 || index >= size) {
            return Optional.empty();
        } else {
            return Optional.of(array[index]);
        }
    }

}
