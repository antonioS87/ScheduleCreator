package com.example.schedulecreator.DateUtils;

public class CharacterUtil {

    public static String getFirstTwoCharactersCRO( String string ){
        String firstTwoChar = null;
        if(string != null && string.length() >= 2){
            firstTwoChar = string.substring(0,2);
        }

        if( string.substring(0,2).toLowerCase().equals( "nj" )||
            string.substring(0,2).toLowerCase().equals("lj") ||
            string.substring(0,2).toLowerCase().equals("dž")) {
            firstTwoChar = firstTwoChar + string.substring(2,3);
        }


        return firstTwoChar;
    }
}
