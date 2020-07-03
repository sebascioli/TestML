package com.seba.testml.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import java.util.Locale;

import static java.lang.Math.abs;

public class Utils {

    public static final String TAG = "sscioli";

    public static String formatPrice(float price, String currency, int decimals) {
        String space = (currency == null || currency.equals("")) ? "" : " ";
        String priceFormat = "%,." + decimals + "f";
        String priceStr;
        if (price >= 0d) {
            priceStr = currency + space + String.format(Locale.getDefault(), priceFormat, price);
        } else {
            priceStr = "-" + currency + space + String.format(Locale.getDefault(), priceFormat, abs(price));
        }
        return priceStr;
    }

    public static void showKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

}