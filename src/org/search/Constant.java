package org.search;

/**
 * Created by LJT on 2016/3/28.
 */
public class Constant {
    public static String[] symbol = {"，", "。", ","};

    public static boolean isSymbol(String symbol) {
        int len = Constant.symbol.length;
        for (int i = 0; i < len; i++) {
            if (symbol.equals(Constant.symbol[i])) {
                return true;
            }
        }
        return false;
    }
}
