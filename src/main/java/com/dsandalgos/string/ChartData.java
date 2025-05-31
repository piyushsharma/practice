package com.dsandalgos.string;


import java.util.ArrayList;
import java.util.List;


public class ChartData {

    class IntervalData {
        private int startTimestamp;
        private int minPrice;
        private int maxPrice;
        private int firstPrice;
        private int lastPrice;

        public IntervalData(int startTimestamp) {
            this.startTimestamp = startTimestamp;
            this.minPrice = Integer.MAX_VALUE;
            this.maxPrice = Integer.MIN_VALUE;
            this.firstPrice = -1;
            this.lastPrice = -1;
        }

        public int getStartTimestamp() {
            return startTimestamp;
        }

        public void setStartTimestamp(int startTimestamp) {
            this.startTimestamp = startTimestamp;
        }

        public int getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(int minPrice) {
            this.minPrice = minPrice;
        }

        public int getMaxPrice() {
            return maxPrice;
        }

        public void setMaxPrice(int maxPrice) {
            this.maxPrice = maxPrice;
        }

        public int getFirstPrice() {
            return firstPrice;
        }

        public void setFirstPrice(int firstPrice) {
            this.firstPrice = firstPrice;
        }

        public int getLastPrice() {
            return lastPrice;
        }

        public void setLastPrice(int lastPrice) {
            this.lastPrice = lastPrice;
        }

        @Override
        public String toString() {
            return "[" + startTimestamp + "," + firstPrice + "," + lastPrice +
                    "," + minPrice + "," + maxPrice + "]";
        }
    }

    /**
     *
     * @param pricesAndTimestamp - comma separated list of data points represented by price:timestamp
     *                           - eg. "1:0,1:10,2:11,3:35"
     *
     * @return String that represents IntervalData for every 10 seconds starting from first data point
     */
    private String parsePriceTimestamps(String pricesAndTimestamp) {
        if(pricesAndTimestamp == null || pricesAndTimestamp.length() == 0) {
            return "";
        }

        final String[] input = pricesAndTimestamp.split(",");
        int[][] points = new int[input.length][2];
        int i = 0;
        for(String point : input) {
            final String[] data = point.split(":");
            points[i][0] = Integer.parseInt(data[0]);
            points[i][1] = Integer.parseInt(data[1]);
            ++i;
        }

        final List<String> result = new ArrayList<>();

        int startTimestamp = points[0][1] - (points[0][1] % 10);
        int endTimestamp = points[points.length - 1][1] - (points[points.length - 1][1] % 10);

        int index = 0;
        IntervalData prevDataPoint = new IntervalData(startTimestamp);
        while(startTimestamp < endTimestamp + 10) {

            final int curEnd = startTimestamp + 10;
            final IntervalData curData = new IntervalData(startTimestamp);

            while(index < points.length && points[index][1] < curEnd) {
                // assuming that price = -1 doesn't make sense
                if(curData.getFirstPrice() == -1) {
                    curData.setFirstPrice(points[index][0]);
                }
                curData.setLastPrice(points[index][0]);

                int minPrice = Math.min(points[index][0], curData.getMinPrice());
                curData.setMinPrice(minPrice);

                int maxPrice = Math.max(points[index][0], curData.getMaxPrice());
                curData.setMaxPrice(maxPrice);

                ++index;
            }

            // no data point was found for this interval, copy previous
            if(curData.getFirstPrice() == -1) {
                curData.setFirstPrice(prevDataPoint.getFirstPrice());
                curData.setLastPrice(prevDataPoint.getLastPrice());
                curData.setMinPrice(prevDataPoint.getMinPrice());
                curData.setMaxPrice(prevDataPoint.getMaxPrice());
            }

            result.add(curData.toString());

            prevDataPoint = curData;
            startTimestamp = curEnd;
        }

        final StringBuilder sb = new StringBuilder();
        for(String point : result) {
            sb.append(point);
            sb.append(",");
        }
        // remove extra "," - not using some library functions in case you want to run this in codesignal
        return sb.substring(0, sb.length() - 1);
    }


    private void testPricesToTimestampParsing() {
        final String input = "1:0,1:10,2:11,3:35";
        // startTimestamp,firstPrice,lastPrice,minPrice,maxPrice
        final String expected = "[0,1,1,1,1],[10,1,2,1,2],[20,1,2,1,2],[30,3,3,3,3]";
        final String result = parsePriceTimestamps(input);
        System.out.println(expected.equals(result));
        System.out.println(result);

        final String inputEdgeCase = "3:35,2:36,4:37,10:59";
        // startTimestamp,firstPrice,lastPrice,minPrice,maxPrice
        final String expectedEC = "[30,3,4,2,4],[40,3,4,2,4],[50,10,10,10,10]";
        final String resultEC = parsePriceTimestamps(inputEdgeCase);
        System.out.println(expectedEC.equals(resultEC));
        System.out.println(resultEC);
    }

    public static void main(String[] args) {
        ChartData cd = new ChartData();
        cd.testPricesToTimestampParsing();
    }

}
