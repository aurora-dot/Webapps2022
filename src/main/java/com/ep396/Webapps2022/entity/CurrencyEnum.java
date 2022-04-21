/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.ep396.Webapps2022.entity;

/**
 *
 * @author blankie
 */
public enum CurrencyEnum {
    GBP,
    EUR,
    USD;

    public static String getCurrencySymbol(CurrencyEnum currency) {
        if (currency != null) {
            switch (currency) {
                case GBP:
                    return "£";
                case EUR:
                    return "€";
                case USD:
                    return "$";
            }
        }
        return null;
    }

    public static String getCurrencyLong(CurrencyEnum currency) {
        if (currency != null) {
            switch (currency) {
                case GBP:
                    return "Pounds";
                case EUR:
                    return "Euros";
                case USD:
                    return "Dollars";
            }
        }
        return null;
    }

    public static CurrencyEnum getCurrencyType(String currency) {
        if (currency != null) {
            switch (currency) {
                case "£":
                    return GBP;
                case "Pounds":
                    return GBP;

                case "€":
                    return EUR;
                case "Euros":
                    return EUR;

                case "$":
                    return USD;
                case "Dollars":
                    return USD;
            }
        }
        return null;
    }
}
