package com.kang.utilman;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.*;

/**
 * A String manipulation without any dependencies
 *
 * @author bradly
 * @version 1.0.0
 * @date 11/19/18 3:35 PM
 */
public class Strman {

    private static final char SPACE_CHAR = ' ';
    private static final Pattern WORD_PATTERN = Pattern.compile("\\{(\\w+)}");
    private static final Pattern NON_WORD_PATTERN = Pattern.compile("\\W+");
    private static final Pattern BLANK_PATTERN = Pattern.compile("\\s");

    private Strman() {
    }

    public static Optional<String> join(CharSequence delimiter, String... values) {
        if (isNull(delimiter) || Arrman.isEmpty(values)) {
            return Optional.empty();
        }
        if (Arrman.size(values) == 1) {
            return Optional.ofNullable(values[0]);
        } else {
            StringBuilder builder = new StringBuilder(values[0]);
            for (int i = 1; i < values.length; i++) {
                builder.append(delimiter).append(values[i]);
            }
            return Optional.of(builder.toString());
        }
    }

    /**
     * Appends Strings to value
     *
     * @param value   initial String
     * @param appends an array of strings to append
     * @return full String
     */
    public static Optional<String> append(final String value, final String... appends) {
        return Optional.ofNullable(value).map(t -> {
            if (Arrman.isEmpty(appends)) {
                return t;
            } else {
                StringBuilder builder = new StringBuilder(t);
                for (String append : appends) {
                    builder.append(append);
                }
                return builder.toString();
            }
        });
    }

    /**
     * Return a new String starting with prepends
     *
     * @param value    The input String
     * @param prepends Strings to prepend
     * @return The prepended String
     */
    public static Optional<String> prepend(final String value, final String... prepends) {
        return Optional.ofNullable(value).map(t -> {
            if (Arrman.isEmpty(prepends)) {
                return t;
            } else {
                StringBuilder builder = new StringBuilder();
                for (String prepend : prepends) {
                    builder.append(prepend);
                }
                builder.append(value);
                return builder.toString();
            }
        });
    }

    /**
     * Get the character at index. This method will take care of negative indexes.
     * The valid value of index is between -(length-1) to (length-1).
     * For values which don't fall under this range Optional.empty will be returned.
     *
     * @param value input value
     * @param index location
     * @return an Optional String if found else empty
     */
    public static Optional<String> at(final String value, int index) {
        if (isEmpty(value)) {
            return Optional.empty();
        }
        int length = value.length();
        if (index < 0) {
            index = length + index;
        }
        return (index < length && index >= 0) ? Optional.of(String.valueOf(value.charAt(index))) : Optional.empty();
    }

    /**
     * Returns an array with strings between start and end.
     *
     * @param value input
     * @param start start
     * @param end   end
     * @return Array containing different parts between start and end.
     */

    public static Optional<String[]> between(final String value, final String start, final String end) {
        if (isEmpty(value)
                || isEmpty(start)
                || isEmpty(end)) {
            return Optional.empty();
        }
        return Optional.of(Arrays.stream(value.split(end))
                .filter(subPart -> subPart.contains(start))
                .map(subPart -> subPart.substring(subPart.indexOf(start) + start.length()))
                .toArray(String[]::new));
    }

    /**
     * Returns a String array consisting of the characters in the String.
     *
     * @param value input
     * @return character array
     */
    public static Optional<String[]> chars(final String value) {
        return Optional.ofNullable(value).filter(t -> !isEmpty(t)).map(t -> t.split(""));
    }

    /**
     * Replace consecutive whitespace characters with a single space.
     *
     * @param value input String
     * @return collapsed String
     */
    public static Optional<String> collapseWhitespace(final String value) {
        return Optional.ofNullable(value).map(t -> t.trim().replaceAll("\\s\\s+", " "));
    }

    /**
     * Verifies that all needles are contained in value. The search is case sensitive
     *
     * @param value   to search
     * @param needles to find
     * @return true if all needles are found else false.
     */
    public static boolean contains(final String value, final String... needles) {
        return contains(true, value, needles);
    }

    /**
     * Verifies that all needles are contained in value
     *
     * @param caseSensitive true or false
     * @param value         input String to search
     * @param needles       needles to find
     * @return true if all needles are found else false.
     */
    public static boolean contains(final boolean caseSensitive, final String value, final String... needles) {
        if (isEmpty(value) || Arrman.isEmpty(needles)) {
            return false;
        } else {
            return Arrays.stream(needles).allMatch(needle -> contains(value, needle, caseSensitive));
        }
    }

    /**
     * Verifies that one or more of needles are contained in value. This is case sensitive
     *
     * @param value   input
     * @param needles needles to search
     * @return boolean true if any needle is found else false
     */
    public static boolean containsAny(final String value, final String... needles) {
        return containsAny(true, value, needles);
    }

    /**
     * Verifies that one or more of needles are contained in value.
     *
     * @param value         input
     * @param needles       needles to search
     * @param caseSensitive true or false
     * @return boolean true if any needle is found else false
     */
    public static boolean containsAny(final boolean caseSensitive, final String value, final String... needles) {
        if (isEmpty(value) || Arrman.isEmpty(needles)) {
            return false;
        }
        return Arrays.stream(needles).anyMatch(needle -> contains(value, needle, caseSensitive));
    }

    /**
     * Count the number of times substr appears in value
     *
     * @param value  input
     * @param subStr to search
     * @return count of times substring exists
     */
    public static long countSubstr(final String value, final String subStr) {
        return countSubstr(value, subStr, true, false);
    }

    /**
     * Count the number of times substr appears in value
     *
     * @param value            input
     * @param subStr           search string
     * @param caseSensitive    whether search should be case sensitive
     * @param allowOverlapping boolean to take into account overlapping
     * @return count of times substring exists
     */
    public static long countSubstr(final String value, final String subStr, final boolean caseSensitive,
                                   boolean allowOverlapping) {
        if (isEmpty(value) || isEmpty(subStr)) {
            return 0L;
        }
        return countSubstr(caseSensitive ? value : value.toLowerCase(), caseSensitive ? subStr : subStr.toLowerCase(),
                allowOverlapping, 0L);
    }

    /**
     * Test if value ends with search. The search is case sensitive.
     *
     * @param value  input string
     * @param search string to search
     * @return true or false
     */
    public static boolean endsWith(final String value, final String search) {
        return endsWith(value, search, true);
    }

    /**
     * Test if value ends with search.
     *
     * @param value         input string
     * @param search        string to search
     * @param caseSensitive true or false
     * @return true or false
     */
    public static boolean endsWith(final String value, final String search, final boolean caseSensitive) {
        if (isEmpty(value) || isEmpty(search)) {
            return false;
        }
        if (caseSensitive) {
            return value.endsWith(search);
        } else {
            return value.toLowerCase().endsWith(search.toLowerCase());
        }
    }

    /**
     * The indexOf() method returns the index within the calling String of the first occurrence of the specified value, starting the search at fromIndex.
     * Returns -1 if the value is not found.
     *
     * @param value         The input String
     * @param needle        The search String
     * @param offset        The offset to start searching from.
     * @param caseSensitive boolean to indicate whether search should be case sensitive
     * @return Returns position of first occurrence of needle.
     */
    public static int indexOf(final String value, final String needle, final int offset, final boolean caseSensitive) {
        if (isNull(value) || isNull(needle) || offset >= value.length()) {
            return -1;
        }
        int position = offset >= 0 ? offset : value.length() + offset;
        if (position < 0) {
            position = 0;
        }
        if (caseSensitive) {
            return value.indexOf(needle, position);
        } else {
            return value.toLowerCase().indexOf(needle.toLowerCase(), position);
        }
    }

    /**
     * Ensures that the value begins with prefix. If it doesn't exist, it's prepended. It is case sensitive.
     *
     * @param value  input
     * @param prefix prefix
     * @return string with prefix if it was not present.
     */
    public static Optional<String> ensureLeft(final String value, final String prefix) {
        return ensureLeft(value, prefix, true);
    }

    /**
     * Ensures that the value begins with prefix. If it doesn't exist, it's prepended.
     *
     * @param value         input
     * @param prefix        prefix
     * @param caseSensitive true or false
     * @return string with prefix if it was not present.
     */
    public static Optional<String> ensureLeft(final String value, final String prefix, final boolean caseSensitive) {
        if (isNull(value) || isNull(prefix)) {
            return Optional.ofNullable(value);
        } else if (caseSensitive) {
            return Optional.of(value).map(t -> t.startsWith(prefix) ? t : prefix + t);
        } else {
            return Optional.of(value.toLowerCase()).map(t -> t.startsWith(prefix.toLowerCase()) ? t : prefix + t);
        }
    }

    /**
     * Ensures that the value ends with suffix. If it doesn't, it's appended. This operation is case sensitive.
     *
     * @param value  The input String
     * @param suffix The substr to be ensured to be right
     * @return The string which is guarenteed to start with substr
     */
    public static Optional<String> ensureRight(final String value, final String suffix) {
        return ensureRight(value, suffix, true);
    }

    /**
     * Ensures that the value ends with suffix. If it doesn't, it's appended.
     *
     * @param value         The input String
     * @param suffix        The substr to be ensured to be right
     * @param caseSensitive Use case (in-)sensitive matching for determining if value already ends with suffix
     * @return The string which is guarenteed to start with substr
     */
    public static Optional<String> ensureRight(final String value, final String suffix, boolean caseSensitive) {
        if (isNull(value) || isNull(suffix)) {
            return Optional.ofNullable(value);
        } else if (endsWith(value, suffix, caseSensitive)) {
            return Optional.of(value);
        } else {
            return append(value, suffix);
        }
    }

    /**
     * Decodes data encoded with MIME base64
     *
     * @param value The data to decode
     * @return decoded data
     */
    public static Optional<String> base64Decode(final String value) {
        return Optional.ofNullable(value).map(t -> new String(Base64.getDecoder().decode(value), StandardCharsets.UTF_8));
    }

    /**
     * Encodes data with MIME base64.
     *
     * @param value The data to encode
     * @return The encoded String
     */
    public static Optional<String> base64Encode(final String value) {
        return Optional.ofNullable(value).map(t -> Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * Convert binary unicode (16 digits) string to string chars
     *
     * @param value The value to decode
     * @return The decoded String
     */
    public static Optional<String> binDecode(final String value) {
        return decode(value, 16, 2);
    }

    /**
     * Convert string chars to binary unicode (16 digits)
     *
     * @param value The value to encode
     * @return String in binary format
     */
    public static Optional<String> binEncode(final String value) {
        return encode(value, 16, 2);
    }

    /**
     * Convert decimal unicode (5 digits) string to string chars
     *
     * @param value The value to decode
     * @return decoded String
     */
    public static Optional<String> decDecode(final String value) {
        return decode(value, 5, 10);
    }

    /**
     * Convert string chars to decimal unicode (5 digits)
     *
     * @param value The value to encode
     * @return Encoded value
     */
    public static Optional<String> decEncode(final String value) {
        return encode(value, 5, 10);
    }

    /**
     * Convert hexadecimal unicode (4 digits) string to string chars
     *
     * @param value The value to decode
     * @return The decoded String
     */
    public static Optional<String> hexDecode(final String value) {
        return decode(value, 4, 16);
    }

    /**
     * Convert string chars to hexadecimal unicode (4 digits)
     *
     * @param value The value to encode
     * @return String in hexadecimal format.
     */
    public static Optional<String> hexEncode(final String value) {
        return encode(value, 4, 16);
    }

    /**
     * Formats a string using parameters
     *
     * @param value  The value to be formatted
     * @param params Parameters to be described in the string
     * @return The formatted string
     */
    public static Optional<String> format(final String value, String... params) {
        if (isEmpty(value)) {
            return Optional.empty();
        }
        Matcher m = WORD_PATTERN.matcher(value);
        String result = value;
        while (m.find()) {
            int paramNumber = Integer.parseInt(m.group(1));
            if (params == null || paramNumber >= params.length) {
                throw new IllegalArgumentException("params does not have value for " + m.group());
            }
            result = result.replace(m.group(), params[paramNumber]);
        }
        return Optional.of(result);
    }

    /**
     * Tests if two Strings are equal
     *
     * @param first  The first String
     * @param second The second String
     * @return true if first and second are equal false otherwise
     */
    public static boolean equal(final String first, final String second) {
        return Objects.equals(first, second);
    }

    /**
     * Tests if two Strings are unequal
     *
     * @param first  The first String
     * @param second The second String
     * @return true if first and second are not equal false otherwise
     */
    public static boolean unequal(final String first, final String second) {
        return !Objects.equals(first, second);
    }

    /**
     * Inserts 'substr' into the 'value' at the 'index' provided.
     *
     * @param value  The input String
     * @param substr The String to insert
     * @param index  The index to insert substr
     * @return String with substr added
     */
    public static Optional<String> insert(final String value, final String substr, final int index) {
        if (isNull(substr) || isNull(value)) {
            return Optional.ofNullable(value);
        }
        return Optional.of(value).map(t -> {
            int position = index >= 0 ? index : size(value) + index;
            if (position <= 0) {
                return append(substr, t);
            } else if (size(t) >= position) {
                return append(t, substr);
            } else {
                return append(t.substring(0, position), substr, t.substring(position));
            }
        }).map(Optional::get);

    }

    /**
     * Verifies if String is uppercase
     *
     * @param value The input String
     * @return true if String is uppercase false otherwise
     */
    public static boolean isUpperCase(final String value) {
        if (isEmpty(value)) {
            return false;
        } else {
            for (int i = 0; i < value.length(); i++) {
                if (Character.isLowerCase(value.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Verifies if String is lower case
     *
     * @param value The input String
     * @return true if String is lowercase false otherwise
     */
    public static boolean isLowerCase(final String value) {
        if (isEmpty(value)) {
            return false;
        } else {
            for (int i = 0; i < value.length(); i++) {
                if (Character.isUpperCase(value.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean isNull(final CharSequence value) {
        return Objects.isNull(value);
    }

    /**
     * Checks whether value is null or empty
     *
     * @param value The input String
     * @return true if the value is null or empty false otherwise
     */
    public static boolean isEmpty(final String value) {
        return isNull(value) || value.isEmpty();
    }

    /**
     * Checks whether value is null or blank
     *
     * @param value The input String
     * @return true if the value is null or blank false otherwise
     */
    public static boolean isBlank(String value) {
        if (isEmpty(value)) {
            return true;
        } else {
            for (int i = 0; i < value.length(); ++i) {
                if (!Character.isWhitespace(value.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Checks whether Object is String
     *
     * @param value The input String
     * @return true if Object is a String false otherwise
     */
    public static boolean isString(final Object value) {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("value can't be null");
        }
        return value instanceof String;
    }


    /**
     * Returns the first n chars of String
     *
     * @param value The input String
     * @param n     Number of chars to return
     * @return The first n chars
     */
    public static Optional<String> first(final String value, final int n) {
        return Optional.ofNullable(value).map(t -> n > t.length() ? t : t.substring(0, n));
    }

    /**
     * Return the first char of String
     *
     * @param value The input String
     * @return The first char
     */
    public static Optional<String> head(final String value) {
        return first(value, 1);
    }

    /**
     * Return the last n chars of String
     *
     * @param value The input String
     * @param n     Number of chars to return
     * @return n Last characters
     */
    public static Optional<String> last(final String value, int n) {
        return Optional.ofNullable(value).map(t -> n > t.length() ? t : t.substring(t.length() - n));
    }

    /**
     * Returns a new string of a given length such that the beginning of the string is padded.
     *
     * @param value  The input String
     * @param pad    The pad
     * @param length Length of the String we want
     * @return Padded String
     */
    public static Optional<String> leftPad(final String value, final String pad, final int length) {
        return Optional.ofNullable(value).map(t -> t.length() > length ? Optional.of(t).get() : append(repeat(pad, length - t.length()).orElse(null), t).orElse(value));
    }

    /**
     * This method returns the index within the calling String object of the last occurrence of the specified value, searching backwards from the offset.
     * Returns -1 if the value is not found. The search starts from the end and case sensitive.
     *
     * @param value  The input String
     * @param needle The search String
     * @return Return position of the last occurrence of 'needle'.
     */
    public static int lastIndexOf(final String value, final String needle) {
        return lastIndexOf(value, needle, true);
    }

    /**
     * This method returns the index within the calling String object of the last occurrence of the specified value, searching backwards from the offset.
     * Returns -1 if the value is not found. The search starts from the end and case sensitive.
     *
     * @param value         The input String
     * @param needle        The search String
     * @param caseSensitive true or false
     * @return Return position of the last occurrence of 'needle'.
     */
    public static int lastIndexOf(final String value, final String needle, boolean caseSensitive) {
        if (isNull(value)) {
            throw new IllegalArgumentException("The 'value' must be not null");
        }
        return lastIndexOf(value, needle, value.length(), caseSensitive);
    }

    /**
     * This method returns the index within the calling String object of the last occurrence of the specified value, searching backwards from the offset.
     * Returns -1 if the value is not found.
     *
     * @param value         The input String
     * @param needle        The search String
     * @param offset        The index to start search from
     * @param caseSensitive whether search should be case sensitive
     * @return Return position of the last occurrence of 'needle'.
     */
    public static int lastIndexOf(final String value, final String needle, final int offset,
                                  final boolean caseSensitive) {
        if (isNull(value)) {
            throw new IllegalArgumentException("The 'value' must be not null");
        }
        if (isNull(needle)) {
            throw new IllegalArgumentException("The 'needle' must be not null");
        }
        if (caseSensitive) {
            return value.lastIndexOf(needle, offset);
        }
        return value.toLowerCase().lastIndexOf(needle.toLowerCase(), offset);
    }

    /**
     * Returns length of String. Delegates to java.lang.String length method.
     *
     * @param value The input String
     * @return Length of the String, 0 if null
     */
    public static int size(final String value) {
        return value == null ? 0 : value.length();
    }

    /**
     * Remove empty Strings from string array
     *
     * @param strings Array of String to be cleaned
     * @return Array of String without empty Strings
     */
    public static Optional<String[]> removeEmpty(String... strings) {
        if (Objects.isNull(strings)) {
            return Optional.empty();
        }
        return Optional.of(Arrays.stream(strings).filter(str -> !isEmpty(str)).toArray(String[]::new));
    }

    /**
     * Remove empty Strings from string array
     *
     * @param strings Array of String to be cleaned
     * @return Array of String without empty Strings
     */
    public static Optional<String[]> removeBlank(String... strings) {
        if (Objects.isNull(strings)) {
            return Optional.empty();
        }
        return Optional.of(Arrays.stream(strings).filter(str -> !isBlank(str)).toArray(String[]::new));
    }

    /**
     * Returns a new String with the prefix removed, if present. This is case sensitive.
     *
     * @param value  The input String
     * @param prefix String to remove on left
     * @return The String without prefix
     */
    public static Optional<String> removeLeft(final String value, final String prefix) {
        return removeLeft(value, prefix, true);
    }

    /**
     * Returns a new String with the prefix removed, if present.
     *
     * @param value         The input String
     * @param prefix        String to remove on left
     * @param caseSensitive ensure case sensitivity
     * @return The String without prefix
     */
    public static Optional<String> removeLeft(final String value, final String prefix, final boolean caseSensitive) {
        if (isNull(value) || isNull(prefix)) {
            return Optional.ofNullable(value);
        } else if (caseSensitive) {
            return Optional.of(value.startsWith(prefix) ? value.substring(prefix.length()) : value);
        } else {
            return Optional.of(value.toLowerCase().startsWith(prefix.toLowerCase()) ? value.substring(prefix.length()) : value);
        }

    }

    /**
     * Remove all non word characters.
     *
     * @param value The input String
     * @return String without non-word characters
     */
    public static Optional<String> removeNonWords(final String value) {
        return Optional.ofNullable(value).map(t -> NON_WORD_PATTERN.matcher(t).replaceAll(""));
    }

    /**
     * Returns a new string with the 'suffix' removed, if present. Search is case sensitive.
     *
     * @param value  The input String
     * @param suffix The suffix to remove
     * @return The String without suffix!
     */
    public static Optional<String> removeRight(final String value, final String suffix) {
        return removeRight(value, suffix, true);
    }

    /**
     * Returns a new string with the 'suffix' removed, if present.
     *
     * @param value         The input String
     * @param suffix        The suffix to remove
     * @param caseSensitive whether search should be case sensitive or not
     * @return The String without suffix!
     */
    public static Optional<String> removeRight(final String value, final String suffix, final boolean caseSensitive) {
        if (isNull(value) || isNull(suffix)) {
            return Optional.ofNullable(value);
        } else if (endsWith(value, suffix, caseSensitive)) {
            return Optional.of(value.substring(0, value.toLowerCase().lastIndexOf(suffix.toLowerCase())));
        } else {
            return Optional.of(value);
        }
    }

    /**
     * Remove all spaces and replace for value.
     *
     * @param value The input String
     * @return String without space
     */
    public static Optional<String> removeSpace(final String value) {
        return Optional.ofNullable(value).map(t -> BLANK_PATTERN.matcher(t).replaceAll(""));
    }

    /**
     * Returns a repeated string given a multiplier.
     *
     * @param value      The input String
     * @param multiplier Number of repeats
     * @return The String repeated
     */
    public static Optional<String> repeat(final String value, final int multiplier) {
        return Optional.ofNullable(value).map(t -> Stream.generate(() -> t).limit(multiplier).collect(joining()));
    }

    /**
     * Replace all occurrences of 'search' value to 'newvalue'. Uses String replace method.
     *
     * @param value         The input
     * @param search        The String to search
     * @param newValue      The String to replace
     * @param caseSensitive whether search should be case sensitive or not
     * @return String replaced with 'newvalue'.
     */
    public static Optional<String> replace(final String value, final String search, final String newValue,
                                           final boolean caseSensitive) {
        if (isNull(value) || isNull(search) || isNull(newValue)) {
            return Optional.ofNullable(value);
        } else if (caseSensitive) {
            return Optional.of(value.replace(search, newValue));
        } else {
            return Optional.of(Pattern.compile(search, Pattern.CASE_INSENSITIVE).matcher(value)
                    .replaceAll(Matcher.quoteReplacement(newValue)));
        }
    }

    /**
     * Reverse the input String
     *
     * @param value The input String
     * @return Reversed String
     */
    public static Optional<String> reverse(final String value) {
        return Optional.ofNullable(value).map(t -> new StringBuilder(t).reverse().toString());
    }

    /**
     * Returns a new string of a given length such that the ending of the string is padded.
     *
     * @param value  The input String
     * @param length Max length of String.
     * @param pad    Character to repeat
     * @return Right padded String
     */
    public static Optional<String> rightPad(final String value, String pad, final int length) {
        if (isNull(value) || isNull(pad) || value.length() > length) {
            return Optional.ofNullable(value);
        }
        return append(value, repeat(pad, length - value.length()).get());
    }

    /**
     * Removes all spaces on left
     *
     * @param value The input String
     * @return String without left border spaces
     */
    public static Optional<String> leftTrim(final String value) {
        return Optional.ofNullable(value).map(t -> {
            int st = 0;
            while (st < t.length() && t.charAt(st) <= SPACE_CHAR) {
                st++;
            }
            return st > 0 ? t.substring(st) : t;
        });
    }

    /**
     * Remove all spaces on right.
     *
     * @param value The String
     * @return String without right boarders spaces.
     */
    public static Optional<String> rightTrim(final String value) {
        return Optional.ofNullable(value).map(t -> {
            int len = t.length();
            while (len > 0 && t.charAt(len - 1) <= SPACE_CHAR) {
                len--;
            }
            return len < t.length() ? t.substring(0, len) : t;
        });
    }

    /**
     * Truncate the unsecured form string, cutting the independent string of required position.
     *
     * @param value  Value will be truncated unsecurely.
     * @param length Size of the returned string.
     * @param filler Value that will be added to the end of the return string. Example: '...'
     * @return String truncated unsafely.
     */
    public static Optional<String> truncate(final String value, final int length, final String filler) {
        if (isNull(value) || length <= 0) {
            return Optional.empty();
        }
        if (length >= value.length()) {
            return Optional.of(value);
        }
        return append(value.substring(0, length - size(filler)), filler);
    }

    /**
     * Truncate the string securely, not cutting a word in half. It always returns the last full word.
     *
     * @param value  The input String
     * @param length Max size of the truncated String
     * @param filler String that will be added to the end of the return string. Example: '...'
     * @return The truncated String
     */
    public static Optional<String> safeTruncate(final String value, final int length, final String filler) {
        if (isNull(value) || length <= 0) {
            return Optional.empty();
        }
        if (length >= value.length()) {
            return Optional.of(value);
        }

        String[] words = words(value).get();
        StringBuilder builder = new StringBuilder();
        int spaceCount = 0;
        int fillerLength = size(filler);
        for (String word : words) {
            if (builder.length() + word.length() + fillerLength + spaceCount > length) {
                break;
            } else {
                if (spaceCount++ > 0) {
                    builder.append(" ");
                }
                builder.append(word);
            }
        }
        if (fillerLength > 0) {
            builder.append(filler);
        }
        return Optional.of(builder.toString());
    }

    /**
     * Alias to String split function. Defined only for completeness.
     *
     * @param value The input String
     * @param regex The delimiting regular expression
     * @return String Array
     */
    public static Optional<String[]> split(final String value, final String regex) {
        if (isNull(value)) {
            return Optional.empty();
        }
        if (isNull(regex)) {
            return Optional.of(new String[]{value});
        }
        return Optional.of(value.split(regex));
    }

    /**
     * Splits a String to words
     *
     * @param value The input String
     * @return Words Array
     */
    public static Optional<String[]> words(final String value) {
        return split(value, "\\s+");
    }

    /**
     * Split lines to an array
     *
     * @param input The input String
     * @return lines in an array
     */
    public static Optional<String[]> lines(String input) {
        return split(input, "\r\n?|\n");
    }

    /**
     * Converts all HTML entities to applicable characters.
     *
     * @param encodedHtml The encoded HTML
     * @return The decoded HTML
     */
    public static Optional<String> htmlDecode(final String encodedHtml) {
        if (isNull(encodedHtml)) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(Arrays.stream(encodedHtml.split("&\\W+;")).map(HtmlDecodedEntities.entities::get).collect(joining()));
        }
    }

    /**
     * Convert all applicable characters to HTML entities.
     *
     * @param html The HTML to encode
     * @return The encoded data
     */
    public static Optional<String> htmlEncode(final String html) {
        if (isNull(html)) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(html.chars().mapToObj(c -> "\\u" + String.format("%04x", c).toUpperCase())
                    .map(HtmlEncodedEntities.entities::get).collect(joining()));
        }
    }

    /**
     * It returns a string with its characters in random order.
     *
     * @param value The input String
     * @return The shuffled String
     */
    public static Optional<String> shuffle(final String value) {
        String[] chars = chars(value).orElse(null);
        if (chars == null) {
            return Optional.empty();
        }
        Random random = new Random();
        for (int i = 0; i < chars.length; i++) {
            int r = random.nextInt(chars.length);
            String tmp = chars[i];
            chars[i] = chars[r];
            chars[r] = tmp;
        }
        return Optional.of(Arrays.stream(chars).collect(joining()));
    }

    /**
     * Alias of substring method
     *
     * @param value The input String
     * @param begin Start of slice.
     * @param end   End of slice.
     * @return The String sliced!
     */
    public static Optional<String> slice(final String value, int begin, int end) {
        return Optional.ofNullable(value).map(t -> t.substring(begin, end));
    }

    /**
     * Convert a String to a slug
     *
     * @param value The value to slugify
     * @return The slugified value
     */
    public static Optional<String> slugify(final String value) {
        return Optional.ofNullable(value).map(t -> collapseWhitespace(t).map(Strman::transliterate).map(Optional::get)).orElse(null).map(t -> t.replace("&", "-and-")).map(t -> split(t, "\\W+").map(s -> Arrays.stream(s).collect(joining("-"))).orElse(t));
    }

    /**
     * Remove all non valid characters.
     *
     * @param value The input String
     * @return String without non valid characters.
     */
    public static Optional<String> transliterate(final String value) {
        if (isNull(value)) {
            return Optional.empty();
        }
        String result = value;
        Set<Map.Entry<String, List<String>>> entries = Ascii.ASCII.entrySet();
        for (Map.Entry<String, List<String>> entry : entries) {
            for (String ch : entry.getValue()) {
                result = result.replace(ch, entry.getKey());
            }
        }
        return Optional.of(result);
    }

    /**
     * Surrounds a 'value' with the given 'prefix' and 'suffix'.
     *
     * @param value  The input String
     * @param prefix prefix. If suffix is null then prefix is used
     * @param suffix suffix
     * @return The String with surround substrs!
     */
    public static Optional<String> surround(final String value, final String prefix, final String suffix) {
        return Optional.ofNullable(value).map(t -> {
            StringBuilder builder = new StringBuilder();
            if (!isEmpty(prefix)) {
                builder.append(prefix);
            }
            builder.append(t);
            if (!isEmpty(suffix)) {
                builder.append(suffix);
            }
            return builder.toString();
        });
    }

    /**
     * Transform to camelCase
     *
     * @param value The input String
     * @return String in camelCase.
     */
    public static Optional<String> toCamelCase(final String value) {
        return Optional.ofNullable(value).map(t -> {
            if (isEmpty(t)) {
                return t;
            } else {
                return toStudlyCase(t).map(s -> s.substring(0, 1).toLowerCase() + s.substring(1)).orElse(t);
            }
        });
    }

    /**
     * Transform to StudlyCaps.
     *
     * @param value The input String
     * @return String in StudlyCaps.
     */
    public static Optional<String> toStudlyCase(final String value) {
        return Optional.ofNullable(value).map(String::trim).map(t -> collapseWhitespace(t).get().split("\\s*(_|-|\\s)\\s*")).map(t ->
                Arrays.stream(t).filter(w -> !w.trim().isEmpty()).map(Strman::upperFirst).map(Optional::get).collect(joining())
        );
    }

    /**
     * Return tail of the String
     *
     * @param value The input String
     * @return String tail
     */
    public static Optional<String> tail(final String value) {
        return Optional.ofNullable(value).filter(v -> !v.isEmpty()).map(v -> last(v, v.length() - 1).get());
    }

    /**
     * Decamelize String
     *
     * @param value The input String
     * @param chr   string to use
     * @return String decamelized.
     */
    public static Optional<String> toDecamelize(final String value, final String chr) {
        return toCamelCase(value).map(t -> Arrays.stream(t.split("(?=\\p{Upper})")).map(String::toLowerCase).collect(joining(Optional.ofNullable(chr).orElse(" "))));
    }

    /**
     * Transform to kebab-case.
     *
     * @param value The input String
     * @return String in kebab-case.
     */
    public static Optional<String> toKebabCase(final String value) {
        return toDecamelize(value, "-");
    }

    /**
     * Transform to snake_case.
     *
     * @param value The input String
     * @return String in snake_case.
     */
    public static Optional<String> toSnakeCase(final String value) {
        return toDecamelize(value, "_");
    }

    public static Optional<String> decode(final String value, final int digits, final int radix) {
        if (isEmpty(value)) {
            return Optional.empty();
        }

        return Optional.ofNullable(Arrays.stream(value.split("(?<=\\G.{" + digits + "})"))
                .map(data -> String.valueOf(Character.toChars(Integer.parseInt(data, radix))))
                .collect(joining()));
    }

    public static Optional<String> encode(final String value, final int digits, final int radix) {
        return Optional.ofNullable(value).map(t -> t.chars().mapToObj(ch -> leftPad(Integer.toString(ch, radix), "0", digits)).map(Optional::get).collect(joining()));
    }

    /**
     * Join concatenates all the elements of the strings array into a single String. The separator string is placed between elements in the resulting string.
     *
     * @param strings   The input array to concatenate
     * @param separator The separator to use
     * @return Concatenated String
     */
    public static Optional<String> join(final String[] strings, final String separator) {
        if (strings == null || separator == null) {
            return Optional.empty();
        }
        StringJoiner joiner = new StringJoiner(separator);
        for (String el : strings) {
            joiner.add(el);
        }
        return Optional.of(joiner.toString());
    }

    /**
     * Converts the first character of string to upper case and the remaining to lower case.
     *
     * @param input The string to capitalize
     * @return The capitalized string
     */
    public static Optional<String> capitalize(final String input) throws IllegalArgumentException {
        return Optional.ofNullable(input).map(t -> head(input).map(String::toUpperCase).map(h -> tail(input).map(s -> h + s.toLowerCase()).orElse(h))).map(Optional::get);
    }

    /**
     * Converts the first character of string to lower case.
     *
     * @param input The string to convert
     * @return The converted string
     */
    public static Optional<String> lowerFirst(final String input) {
        return Optional.ofNullable(input).map(t -> head(t).map(String::toLowerCase).map(h -> tail(t).map(s -> h + s).orElse(h))).map(Optional::get);
    }

    /**
     * Verifies whether String is enclosed by encloser
     *
     * @param input    The input String
     * @param encloser String which encloses input String
     * @return true if enclosed false otherwise
     */
    public static boolean isEnclosedBetween(final String input, final String encloser) {
        return isEnclosedBetween(input, encloser, encloser);
    }

    /**
     * Verifies whether String is enclosed by encloser
     *
     * @param input         The input String
     * @param leftEncloser  String which encloses input String at left start
     * @param rightEncloser String which encloses input String at the right end
     * @return true if enclosed false otherwise
     */
    public static boolean isEnclosedBetween(final String input, final String leftEncloser, String rightEncloser) {
        if (isNull(input)) {
            throw new IllegalArgumentException("input can't be null");
        }
        if (isNull(leftEncloser)) {
            throw new IllegalArgumentException("leftEncloser can't be null");
        }
        if (isNull(rightEncloser)) {
            throw new IllegalArgumentException("rightEncloser can't be null");
        }
        return input.startsWith(leftEncloser) && input.endsWith(rightEncloser);
    }

    /**
     * Converts the first character of string to upper case.
     *
     * @param input The string to convert.
     * @return Returns the converted string.
     */
    public static Optional<String> upperFirst(String input) {
        if (isEmpty(input)) {
            return Optional.ofNullable(input);
        }
        return head(input).map(String::toUpperCase).map(h -> tail(input).map(t -> h + t).orElse(h));
    }

    /**
     * Removes leading whitespace from string.
     *
     * @param input The string to trim.
     * @return Returns the trimmed string.
     */
    public static Optional<String> trimStart(final String input) {
        return Optional.ofNullable(input).filter(v -> !v.isEmpty()).map(Strman::leftTrim).map(Optional::get);
    }

    /**
     * Removes leading characters from string.
     *
     * @param input The string to trim.
     * @param chars The characters to trim.
     * @return Returns the trimmed string.
     */
    public static Optional<String> trimStart(final String input, String... chars) {
        return Optional.ofNullable(input).filter(v -> !v.isEmpty()).map(v -> {
            String pattern = String.format("^[%s]+", join(chars, "\\"));
            return v.replaceAll(pattern, "");
        });
    }

    /**
     * Removes trailing whitespace from string.
     *
     * @param input The string to trim.
     * @return Returns the trimmed string.
     */
    public static Optional<String> trimEnd(final String input) {
        return Optional.ofNullable(input).filter(v -> !v.isEmpty()).map(Strman::rightTrim).map(Optional::get);
    }

    /**
     * Removes trailing characters from string.
     *
     * @param input The string to trim.
     * @param chars The characters to trim.
     * @return Returns the trimmed string.
     */
    public static Optional<String> trimEnd(final String input, String... chars) {
        return Optional.ofNullable(input).filter(v -> !v.isEmpty()).map(v -> {
            String pattern = String.format("[%s]+$", join(chars, "\\"));
            return v.replaceAll(pattern, "");
        });
    }

    /**
     * Counts the number of occurrences of each character in the string
     *
     * @param input The input string
     * @return A map containing the number of occurrences of each character in the string
     */
    public static Map<Character, Long> charsCount(String input) {
        if (isEmpty(input)) {
            return Collections.emptyMap();
        }
        return input.chars().mapToObj(c -> (char) c).collect(groupingBy(identity(), counting()));
    }

    /**
     * Changes passed in string to all lower case and adds underscore between words.
     *
     * @param value The input string
     * @return the input string in all lower case with underscores between words
     */
    public static Optional<String> underscored(final String value) {
        if (isEmpty(value)) {
            return Optional.empty();
        } else {
            return Optional.of(value).map(t -> t.trim().replaceAll("([a-z\\d])([A-Z]+)", "$1_$2").replaceAll("[-\\s]+", "_").toLowerCase());
        }
    }

    /**
     * Aggregates the contents of n strings into a single list of tuples.
     *
     * @param inputs A list of strings.
     * @return A list of strings if none of the strings in the input is null or empty.
     * An empty list otherwise.
     */
    public static List<String> zip(String... inputs) {
        if (Arrman.isEmpty(inputs)) {
            return Collections.emptyList();
        }
        OptionalInt min = Arrays.stream(inputs).mapToInt(Strman::size).min();
        if (!min.isPresent()) {
            return Collections.emptyList();
        }
        return IntStream.range(0, min.getAsInt())
                .mapToObj(elementIndex -> Arrays.stream(inputs)
                        .map(input -> String.valueOf(input.charAt(elementIndex)))
                        .collect(joining()))
                .collect(toList());
    }


    /**
     * Converts a underscored or camelized string into an dasherized one.
     *
     * @param input The input String
     * @return dasherized String.
     */
    public static Optional<String> dasherize(String input) {
        return toKebabCase(input);
    }

    /**
     * Converts an underscored, camelized, or dasherized string into a humanized one. Also removes beginning and ending whitespace.
     *
     * @param input The input String
     * @return humanized version of String
     */
    public static Optional<String> humanize(final String input) {
        if (isEmpty(input)) {
            return Optional.empty();
        } else {
            return upperFirst(underscored(input).get().replace("_", ""));
        }
    }

    /**
     * Returns a copy of the string in which all the case-based characters have had their case swapped.
     *
     * @param input Input string
     * @return String with all the case swapped
     */
    public static Optional<String> swapCase(String input) {
        if (isEmpty(input)) {
            return Optional.empty();
        }
        StringBuilder resultBuilder = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                resultBuilder.append(Character.toLowerCase(ch));
            } else {
                resultBuilder.append(Character.toUpperCase(ch));
            }
        }
        return Optional.of(resultBuilder.toString());
    }

    /**
     * Returns a string representation of the number passed in where groups of three digits are delimited by comma
     *
     * @param number Input number
     * @return formatted String
     */
    public static Optional<String> formatNumber(long number) {
        String stringRepresentation = Long.toString(number);
        StringBuilder sb = new StringBuilder();
        int bound = stringRepresentation.length() - 1;
        String delimiter = ",";
        int counter = 0;
        for (int i = bound; i >= 0; i--) {
            char c = stringRepresentation.charAt(i);
            if (i != bound && counter % 3 == 0) {
                sb.append(delimiter);
            }
            sb.append(c);
            counter++;
        }
        return Optional.of(sb.reverse().toString());
    }

    public static Optional<String[]> chop(String input, int step) {
        if (isEmpty(input)) {
            return Optional.empty();
        }
        if (step == 0) {
            return Optional.of(new String[]{input});
        }
        int strLength = input.length();
        int iterations = strLength % step == 0 ? strLength / step : strLength / step + 1;
        return Optional.of(IntStream.iterate(0, i -> i + step)
                .limit(iterations)
                .mapToObj(i -> input.substring(i, (i + step) < strLength ? i + step : strLength))
                .toArray(String[]::new));
    }

    /**
     * Converts a String into its Start Case version
     * https://en.wikipedia.org/wiki/Letter_case#Stylistic_or_specialised_usage
     *
     * @param input The input String
     * @return Start Case String
     */
    public static Optional<String> startCase(final String input) {
        // split into a word when we encounter a space, or an underscore, or a dash, or a switch from lower to upper case
        return split(input, "\\s|_|-|(?<=[a-z])(?=[A-Z])").map(t -> Arrays.stream(t).filter(w -> !isBlank(w))
                .map(w -> upperFirst(w.toLowerCase()).get()).collect(joining(" ")));
    }

    public static Optional<String> escapeRegExp(final String input) {
        return Optional.ofNullable(input).map(t -> t.replaceAll("[\\\\\\^\\$\\*\\+\\-\\?\\.\\|\\(\\)\\{\\}\\[\\]]", "\\\\$0"));
    }

    private static boolean contains(final String value, final String needle, final boolean caseSensitive) {
        return caseSensitive ? value.contains(needle) : value.toLowerCase().contains(needle.toLowerCase());
    }

    private static long countSubstr(String value, String subStr, boolean allowOverlapping, long count) {
        int position = value.indexOf(subStr);
        if (position == -1) {
            return count;
        }
        int offset;
        if (!allowOverlapping) {
            offset = position + subStr.length();
        } else {
            offset = position + 1;
        }
        return countSubstr(value.substring(offset), subStr, allowOverlapping, ++count);
    }

}
