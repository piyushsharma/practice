package com.dsandalgos.misc;

/**
 * An image smoother is a filter of the size 3 x 3 that can be applied to each cell of an image by
 *  rounding down the average of the cell and the eight surrounding cells 
 * (i.e., the average of the nine cells in the blue smoother). 
 * 
 * If one or more of the surrounding cells of a cell is not present, 
 * we do not consider it in the average (i.e., the average of the four cells in the red smoother).

Given an m x n integer matrix img representing the grayscale of an image, return the image after applying the smoother on each cell of it.


Example 1:
Input: img = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[0,0,0],[0,0,0],[0,0,0]]
Explanation:
For the points (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
For the points (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
For the point (1,1): floor(8/9) = floor(0.88888889) = 0

Example 2:
Input: img = [[100,200,100],[200,50,200],[100,200,100]]
Output: [[137,141,137],[141,138,141],[137,141,137]]
Explanation:
For the points (0,0), (0,2), (2,0), (2,2): floor((100+200+200+50)/4) = floor(137.5) = 137
For the points (0,1), (1,0), (1,2), (2,1): floor((200+200+50+200+100+100)/6) = floor(141.666667) = 141
For the point (1,1): floor((50+200+200+200+200+100+100+100+100)/9) = floor(138.888889) = 138

*/

public class ImageSmoother {
  
    public int[][] imageSmoother(int[][] img) {
        
        int[][] result = new int[img.length][img[0].length];


        for(int i = 0; i < img.length; ++i) {
            for(int j = 0; j < img[0].length; ++j) {
                result[i][j] = (int) calculateSmoother(img, i, j);
            }
        }
        return result;

    }


    // use bit operations 

    public int[][] imageSmootherConstantSpaceV2(int[][] img) {

    
        // we know img values are b/w 0 - 255

        // for Y = p . X + r
        // if r is between 0 to X - 1 (remainder)
        // then p = Y/X and r = Y % X
        // for us X = 256 

        int m = img.length;
        int n = img[0].length;
    
        for(int i = 0; i < img.length; ++i) {
            for(int j = 0; j < img[0].length; ++j) {

                int sum = 0;
                int count = 0;

                for(int x = i - 1; x <= i + 1; ++x) {
                    for(int y = j - 1; y <= j; ++y) {

                        if(x >= 0 && x < m && y >=0 && y < n) {

                            sum += img[x][y] & 256;
                            ++count;
                        }

                    }
                }
                img[i][j] |= (sum/count) << 8;
            }
        }


         // Extract the smoothed value from encoded img[i][j].
         for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                img[i][j] >>= 8;
            }
        }

        return img;

    }


    public int[][] imageSmootherConstantSpace(int[][] img) {

    
        // we know img values are b/w 0 - 255

        // for Y = p . X + r
        // if r is between 0 to X - 1 (remainder)
        // then p = Y/X and r = Y % X
        // for us X = 256 

        int m = img.length;
        int n = img[0].length;
    
        for(int i = 0; i < img.length; ++i) {
            for(int j = 0; j < img[0].length; ++j) {

                double sum = 0;
                double count = 0;

                for(int x = i - 1; x <= i + 1; ++x) {
                    for(int y = j - 1; y <= j; ++y) {

                        if(x >= 0 && x < m && y >=0 && y < n) {

                            sum += img[x][y] % 256;
                            ++count;
                        }

                    }
                }
                img[i][j] += (sum/count)*256;
            }
        }


         // Extract the smoothed value from encoded img[i][j].
         for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                img[i][j] /= 256;
            }
        }

        return img;

    }


    private double calculateSmoother(int[][] img, int i, int j) {

        int m = img.length;
        int n = img[0].length;

        int[] check = new int[]{-1, 0, 1} ;


        double sum = 0;
        int count = 0;


        for(int p = 0; p < check.length; ++p) {
            for(int q = 0; q < check.length; ++q) {

                int rowdiff = check[p];
                int coldiff = check[q];

                if(i+rowdiff >=0 && i+rowdiff < m && j+coldiff >= 0 && j+coldiff < n) {
                    sum += img[i+rowdiff][j+coldiff];
                    ++count;
                }
            }
        }

        return sum/count;
    }

    private double calculateSmootherV2(int[][] img, int i, int j) {

        int m = img.length;
        int n = img[0].length;

        double sum = img[i][j];
        int count = 1;

        if(i - 1 >= 0) {
            sum += img[i-1][j];
            ++count;

            if(j - 1 >= 0) {
                sum += img[i-1][j-1];                
                ++count;
            }

            if(j+1 < n) {
                sum += img[i-1][j+1];
                ++count;
            }
        }

        if(j - 1 >= 0) {
            sum += img[i][j-1];
            ++count;
        }

        if(j + 1 < n) {
            sum += img[i][j+1];
            ++count;
        } 
        
        if(i + 1 < m) {

            sum += img[i+1][j];
            ++count;

            if(j - 1 >= 0) {
                sum += img[i+1][j-1];
                ++count;
            }

            if(j + 1 < n) {
                sum += img[i+1][j+1];
                ++count;
            }
        }

        return sum/count;

    }

}
