package com.dsandalgos.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Piyush Sharma
 */
public class UniqueRecords {

    class PhoneKey {
        String phoneNumber;
        Set<String> email;
        boolean duplicate;
    }

    class EmailKey {
        String email;
        Set<String> phoneNumber;
        boolean duplicate;
    }


    public int uniqueRecords(String[][] input) {

        Map<String, Set<String>> emailsMap = new HashMap<>();
        Map<String, Set<String>> phoneNumbersMap = new HashMap<>();

        for(String[] s : input) {
            Set<String> emails = phoneNumbersMap.get(s[0]);
            if(emails == null) {
                emails = new HashSet<>();
            }
            emails.add(s[1]);
            phoneNumbersMap.put(s[0], emails);

            Set<String> phoneNumbers = emailsMap.get(s[1]);
            if(phoneNumbers == null) {
                phoneNumbers = new HashSet<>();
            }
            phoneNumbers.add(s[0]);
            phoneNumbersMap.put(s[1], phoneNumbers);
        }

        for(String phoneNumber : phoneNumbersMap.keySet()) {

            Set<String> emails = phoneNumbersMap.get(phoneNumber);

            Set<String> duplicatePhoneNumbers = new HashSet<>();

            for(String email : emails) {
                Set<String> dpn = emailsMap.get(email);
                duplicatePhoneNumbers.addAll(dpn);
            }


        }


        return phoneNumbersMap.size();
    }



    public static void main(String[] args) {

        String[] a1 = {"412", "a@b"};
        String[] a2 = {"123", "c@d"};
        String[] a3 = {"412", "c@d"};
//        String[] a4 = {"412", "a@b"};
//        String[] a5 = {"412", "a@b"};
//        String[] a6 = {"412", "a@b"};






    }




}
