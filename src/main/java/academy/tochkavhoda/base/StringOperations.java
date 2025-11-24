package academy.tochkavhoda.base;

import java.text.DecimalFormat;

public class StringOperations {
    public static int getSummaryLength(String[] strings) {
        int length = 0;

        for (String str: strings) {
            length += str.length();
        }

        return length;
    }

    public static String getFirstAndLastLetterString(String string) {
        return string.substring(0, 1) + string.substring(string.length() - 1);
    }

    public static boolean isSameCharAtPosition(String string1, String string2, int index) {
        return string1.charAt(index) ==  string2.charAt(index);
    }

    public static boolean isSameFirstCharPosition(String string1, String string2, char character) {
        return string1.indexOf(character) == string2.indexOf(character);
    }

    public static boolean isSameLastCharPosition(String string1, String string2, char character) {
        return string1.lastIndexOf(character) == string2.lastIndexOf(character);
    }

    public static boolean isSameFirstStringPosition(String string1, String string2, String str) {
        int position1 = string1.indexOf(str);
        int position2 = string2.indexOf(str);

        return position1 != -1 && position2 != -1 && position1 == position2;
    }

    public static boolean isSameLastStringPosition(String string1, String string2, String str) {
        int position1 = string1.lastIndexOf(str);
        int position2 = string2.lastIndexOf(str);

        return position1 != -1 && position2 != -1 && position1 == position2;
    }

    public static boolean isEqual(String string1, String string2) {
        return string1.equals(string2);
    }

    public static boolean isEqualIgnoreCase(String string1, String string2) {
        return string1.equalsIgnoreCase(string2);
    }

    public static boolean isLess(String string1, String string2) {
        return string1.compareTo(string2) < 0;
    }

    public static boolean isLessIgnoreCase(String string1, String string2) {
        return string1.compareToIgnoreCase(string2) < 0;
    }

    public static String concat(String string1, String string2) {
        return string1.concat(string2);
    }

    public static boolean isSamePrefix(String string1, String string2, String prefix) {
        return string1.startsWith(prefix) && string2.startsWith(prefix);
    }

    public static boolean isSameSuffix(String string1, String string2, String suffix) {
        return string1.endsWith(suffix) && string2.endsWith(suffix);
    }

    public static String getCommonPrefix(String string1, String string2) {
        if (string1 == null || string2 == null ||
                string1.isEmpty() || string2.isEmpty() ||
                string1.charAt(0) != string2.charAt(0)) {
            return "";
        }

        return string1.charAt(0) + getCommonPrefix(string1.substring(1), string2.substring(1));
    }

    public static String reverse(String string) {
        return new StringBuilder(string).reverse().toString();
    }

    public static boolean isPalindrome(String string) {
        int left = 0;
        int right = string.length() - 1;

        while (left < right) {
            if (string.charAt(left) != string.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static boolean isPalindromeIgnoreCase(String string) {
        String lowerCaseString = string.toLowerCase();
        int left = 0;
        int right = lowerCaseString.length() - 1;

        while (left < right) {
            if (lowerCaseString.charAt(left) != lowerCaseString.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static String getLongestPalindromeIgnoreCase(String[] strings) {
        String longestPalindrome = "";

        for (String str : strings) {
            if (str != null && isPalindromeIgnoreCase(str) && str.length() > longestPalindrome.length()) {
                longestPalindrome = str;
            }
        }

        return longestPalindrome;
    }

    public static boolean hasSameSubstring(String string1, String string2, int index, int length) {
        if (index < 0 || length < 0 ||
                index + length > string1.length() ||
                index + length > string2.length()) {
            return false;
        }

        String substr1 = string1.substring(index, index + length);
        String substr2 = string2.substring(index, index + length);

        return substr1.equals(substr2);
    }

    public static boolean isEqualAfterReplaceCharacters(String string1, char replaceInStr1, char replaceByInStr1,
                                                        String string2, char replaceInStr2, char replaceByInStr2) {
        String result1 = string1.replace(replaceInStr1, replaceByInStr1);
        String result2 = string2.replace(replaceInStr2, replaceByInStr2);

        return result1.equals(result2);
    }

    public static boolean isEqualAfterReplaceStrings(String string1, String replaceInStr1, String replaceByInStr1,
                                                     String string2, String replaceInStr2, String replaceByInStr2) {
        String result1 = string1.replace(replaceInStr1, replaceByInStr1 != null ? replaceByInStr1 : "");
        String result2 = string2.replace(replaceInStr2, replaceByInStr2 != null ? replaceByInStr2 : "");

        return result1.equals(result2);
    }

    public static boolean isPalindromeAfterRemovingSpacesIgnoreCase(String string) {
        String noSpaces = string.replace(" ", "").toLowerCase();

        int left = 0;
        int right = noSpaces.length() - 1;

        while (left < right) {
            if (noSpaces.charAt(left) != noSpaces.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static boolean isEqualAfterTrimming(String string1, String string2) {
        return string1.trim().equals(string2.trim());
    }

    public static String makeCsvStringFromInts(int[] array) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(",");
            }
        }

        return sb.toString();
    }

    public static String makeCsvStringFromDoubles(double[] array) {
        StringBuilder sb = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.00");

        for (int i = 0; i < array.length; i++) {
            sb.append(df.format(array[i]));
            if (i < array.length - 1) {
                sb.append(",");
            }
        }

        return sb.toString();
    }

    public static StringBuilder makeCsvStringBuilderFromInts(int[] array) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(",");
            }
        }

        return sb;
    }

    public static StringBuilder makeCsvStringBuilderFromDoubles(double[] array) {
        StringBuilder sb = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.00");

        for (int i = 0; i < array.length; i++) {
            sb.append(df.format(array[i]));
            if (i < array.length - 1) {
                sb.append(",");
            }
        }

        return sb;
    }

    public static StringBuilder removeCharacters(String string, int[] positions) {
        StringBuilder sb = new StringBuilder(string);

        for (int i = positions.length - 1; i >= 0; i--) {
            int pos = positions[i];
            if (pos >= 0 && pos < sb.length()) {
                sb.deleteCharAt(pos);
            }
        }

        return sb;
    }

    public static StringBuilder insertCharacters(String string, int[] positions, char[] characters) {
        StringBuilder sb = new StringBuilder(string);

        for (int i = positions.length - 1; i >= 0; i--) {
            int pos = positions[i];
            char ch = characters[i];

            if (pos >= 0 && pos <= sb.length()) {
                sb.insert(pos, ch);
            }
        }

        return sb;
    }
}