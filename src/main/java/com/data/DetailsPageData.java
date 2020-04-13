package com.data;

import java.util.ArrayList;

public class DetailsPageData {
    public static String CAR_YEAR = "2013";
    public static String CAR_MAKE = "TOYOTA";
    public static String CAR_MODEL = "Vios";
    public static String CAR_VARIANT = "TRD 1.5L Gas A/T";
    public static boolean STILL_PAYING_FOR_CAR_LOAN = true;
    public static String CAR_USAGE = "Private";
    public static String INSURED_FOR = "₱455,000";

    public static String OWN_DAMAGE_AND_THEFT = "₱455,000";
    public static String VTPL_BODILY_INJURY = "₱200,000";
    public static String VTPL_PROPERTY_DAMAGE = "₱200,000";
    public static String ACT_OF_NATURE = "₱455,000";
    public static String WHEN_SOMEONE_IN_YOUR_CAR_GETS_HURT = "₱50,000 per seat, max 5 seats";
    public static ArrayList<String> PRICES() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("₱ 12,573 /year");
        arrayList.add("₱ 13,821 /year");
        arrayList.add("₱ 14,445 /year");
        arrayList.add("₱ 14,672 /year");
        arrayList.add("₱ 14,786 /year");
        arrayList.add("₱ 14,842 /year");
        return arrayList;
        }

    public static ArrayList<String> PRICES_InsurerAZ() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("₱ 14,842 /year");
        arrayList.add("₱ 13,821 /year");
        arrayList.add("₱ 14,786 /year");
        arrayList.add("₱ 14,672 /year");
        arrayList.add("₱ 14,445 /year");
        arrayList.add("₱ 12,573 /year");
        return arrayList;
    }
    public static ArrayList<String> HEADERS_InsurerAZ() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Alpha Insurance");
        arrayList.add("MAPFRE Insular");
        arrayList.add("Pacific Union Insurance");
        arrayList.add("PhilsFirst Insurance");
        arrayList.add("PNB General Insurers");
        arrayList.add("QBE Seaboard");
        return arrayList;
    }

    public static ArrayList<String> PRICES_InsurerZA() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("₱ 12,573 /year");
        arrayList.add("₱ 14,445 /year");
        arrayList.add("₱ 14,672 /year");
        arrayList.add("₱ 14,786 /year");
        arrayList.add("₱ 13,821 /year");
        arrayList.add("₱ 14,842 /year");
        return arrayList;
    }
    public static ArrayList<String> HEADERS_InsurerZA() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("QBE Seaboard");
        arrayList.add("PNB General Insurers");
        arrayList.add("PhilsFirst Insurance");
        arrayList.add("Pacific Union Insurance");
        arrayList.add("MAPFRE Insular");
        arrayList.add("Alpha Insurance");
        return arrayList;
    }

    public static ArrayList<String> PRICES_HighLow() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("₱ 14,842 /year");
        arrayList.add("₱ 14,786 /year");
        arrayList.add("₱ 14,672 /year");
        arrayList.add("₱ 14,445 /year");
        arrayList.add("₱ 13,821 /year");
        arrayList.add("₱ 12,573 /year");
        return arrayList;
    }
    public static ArrayList<String> HEADERS_HighLow() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Alpha Insurance");
        arrayList.add("Pacific Union Insurance");
        arrayList.add("PhilsFirst Insurance");
        arrayList.add("PNB General Insurers");
        arrayList.add("MAPFRE Insular");
        arrayList.add("QBE Seaboard");
        return arrayList;
    }

    public static ArrayList<String> HEADERS_LoanStatusNo() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("QBE Seaboard");
        arrayList.add("PhilsFirst Insurance");
        arrayList.add("Pacific Union Insurance");
        arrayList.add("Alpha Insurance");
        arrayList.add("MAPFRE Insular");
        arrayList.add("PNB General Insurers");
        return arrayList;
    }
}
