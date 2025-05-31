package com.brainteaser;

import java.util.Arrays;

/**
 * Created by Piyush Sharma
 */
public class StringEncoding {

    // the string to byte depends on what character encoding is being used.
    // unless specified, that is dependent on operating system and locale
    // so this might return different values on different machines

    public static void main(String[] args) throws Exception {

        char[] chars = new char[]{'\u0097'};
        String str = new String(chars);
        byte[] bytes = str.getBytes();
        System.out.println(Arrays.toString(bytes));


//        for (char c⁯‮h = 0; c⁯‮h < Character.MAX_VALUE; c⁯‮h++)
//            if (Character.isJavaIdentifierPart(c⁯‮h) && !Character.isJavaIdentifierStart(c⁯‮h))
//                System.out.printf("%04x <%s>%n", (int) c⁯‮h, "" + c⁯‮h);

        int i = 5;
        i = i++ + ++i - i-- - --i;  // 5 + 7 - 7 - 5
        System.out.println(i);

        int x = 5;
        int y = 10;
        int z = ++x * y--;
        System.out.println(z);
        System.out.println(y);
    }
}
