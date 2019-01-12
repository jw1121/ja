package com.data.integration.utility;

import java.util.ArrayList;
import java.util.List;

public class StringConvert {

    public static String toUpper(String txt) {
        if(txt == null) return null;

        return txt.toUpperCase();
    }

    public static List<String> toUpper(List<String> txts) {
        if(txts == null || txts.isEmpty()) { return null;}

        List<String> newTxts = new ArrayList<String>();
        for(String txt : txts) {
            if(txt != null) {
                newTxts.add(txt.toUpperCase());
            }
        }
        return newTxts;
    }

    public static List<List<String>> listToUpper(List<List<String>> txtInTxt) {
        if(txtInTxt == null || txtInTxt.isEmpty()) { return null;}

        List<List<String>> newTxtInTxt = new ArrayList<>();
        for(List<String> txts : txtInTxt) {
            if(txts == null || txts.isEmpty()) { newTxtInTxt.add(null);}

            List<String> newTxts = new ArrayList<String>();
            for(String txt : txts) {
                if(txt != null) {
                    newTxts.add(txt.toUpperCase());
                }
            }
            newTxtInTxt.add(newTxts);
        }

        return newTxtInTxt;
    }
}
