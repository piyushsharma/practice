package com.dsandalgos.design;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchPrice {





    private Double readUrl() throws Exception {


        URL url = new URL("https://www.amazon.com/MaGeek-Samsung-Motorola-Android-Smartphones/dp/B00WMARI3S?pf_rd_p=afc07c5e-17f3-4f0c-a365-f5fa885c2d1e&pd_rd_wg=XnCOc&pf_rd_r=YG6SKJPAAC9V9P58Z4MS&ref_=pd_gw_unk&pd_rd_w=Klo5Y&pd_rd_r=a01f7da9-1f15-4e2f-8049-af9f2c4c9297");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);
        con.setRequestProperty("Accept-Encoding", "identity");

        con.setInstanceFollowRedirects(true);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();


        final String htmlOutput = content.toString();

        final String match = "class=\"a-size-base a-color-price header-price a-text-normal\">$";

        int index = htmlOutput.indexOf(match);
        index += match.length();
        StringBuilder priceStr = new StringBuilder();
        while(htmlOutput.charAt(index) != '<') {
            priceStr.append(htmlOutput.charAt(index));
            ++index;
        }

        Double price = Double.parseDouble(priceStr.toString());

        return price;
    }

    public static void main(String[] args) throws Exception {

        Double price = new FetchPrice().readUrl();
        System.out.println(price);

    }






}
