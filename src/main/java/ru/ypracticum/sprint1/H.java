package ru.ypracticum.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class H {
    private static boolean shift = false;
    private static String sumOfBinaries(String a, String b) {
        StringBuilder sb = new StringBuilder();

        String maxStr = a.length() >= b.length() ? a : b;
        String minStr = a.length() < b.length() ? a : b;

        int maxLen = maxStr.length() - 1;
        int minLen = minStr.length() - 1;
        while (true) {
            if (minLen >= 0) {
                boolean isZeroZero = maxStr.charAt(maxLen) == '0' && minStr.charAt(minLen) == '0';
                boolean isZeroOne = maxStr.charAt(maxLen) == '0' && minStr.charAt(minLen) == '1';
                boolean isOneZero = maxStr.charAt(maxLen) == '1' && minStr.charAt(minLen) == '0';

                if (isZeroZero) {
                    zeroZero(sb);
                } else if (isZeroOne) {
                    zeroOne(sb);
                } else if (isOneZero) {
                    oneZero(sb);
                } else {
                    oneOne(sb);
                }
                maxLen -= 1;
                minLen -= 1;
            } else {
                char ch = maxStr.charAt(maxLen);
                if (shift) {
                    if (ch == '1') {
                        sb.append(0);
                    } else {
                        sb.append(1);
                        shift = false;
                    }
                } else {
                    sb.append(ch);
                    shift = false;
                }
                maxLen -= 1;
            }

            if (maxLen == -1) {
                break;
            }
        }

        if (shift) {
            sb.append(1);
        }

        return sb.reverse().toString();
    }

    private static void zeroZero(StringBuilder sb) {
        if (shift) {
            sb.append(1);
            shift = false;
        } else {
            sb.append(0);
        }
    }

    private static void zeroOne(StringBuilder sb) {
        if (shift) {
            sb.append(0);
        } else {
            sb.append(1);
        }
    }

    private static void oneZero(StringBuilder sb) {
        if (shift) {
            sb.append(0);
        } else {
            sb.append(1);
        }
    }

    private static void oneOne(StringBuilder sb) {
        if (shift) {
            sb.append(1);
        } else {
            sb.append(0);
            shift = true;
        }
    }
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String a = reader.readLine();
            String b = reader.readLine();
            System.out.println(sumOfBinaries(a, b));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
