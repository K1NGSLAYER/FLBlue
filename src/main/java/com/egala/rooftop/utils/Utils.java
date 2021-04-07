package com.egala.rooftop.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
public class Utils {
    private final Random RANDOM = new SecureRandom();
    private final String ALPHA_NUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String generateConfirmationCode(int length) {
        return generateRandomString(length);
    }

    private String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder();

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHA_NUMERIC.charAt(RANDOM.nextInt(ALPHA_NUMERIC.length())));
        }
        return new String(returnValue);
    }

    /*
    * Get an array of all null fields in a class
    * */
    public static String[] getNullPropertyNames(Object source){
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyFields = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null){
                emptyFields.add(pd.getName());
            }
        }
        String[] result = new String[emptyFields.size()];
        return  emptyFields.toArray(result);
    }

    public String generateTournamentID(String gameTitle){
        String tournamentID = gameTitle.replaceAll("\\s","").substring(0,4);
        tournamentID = tournamentID + generateConfirmationCode(11);
        return tournamentID;
    }
}
