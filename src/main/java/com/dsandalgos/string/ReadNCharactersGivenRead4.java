package com.dsandalgos.string;

import com.dsandalgos.util.Reader4;

/**
 * Created by Piyush Sharma
 */

/*
The API: int read4(char *buf) reads 4 characters at a time from a file.
The return value is the actual number of characters read.
    For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.


*/

public class ReadNCharactersGivenRead4 extends Reader4 {

    /*
    // Note: The read function will only be called once for each test case.
    */

    // take note that read4 returning 4 could mean the last 4 bytes of the file.
    // Also, we need to make sure that the buffer is not copied more than n bytes,
    // for this we copy the remaining bytes (n – readBytes) or the number of bytes read, whichever is smaller.
    public int read(char[] buf, int n) {

        char[] read4buffer = new char[4];
        int readBytes = 0;
        boolean eof = false;
        while(!eof && readBytes < n) {

            int b = read4(read4buffer);
            // end of file
            if(b < 4) eof = true;
            int bytes = Math.min(n - readBytes, b);
            System.arraycopy(read4buffer, 0, buf, readBytes, bytes);
            readBytes += bytes;
        }

        return readBytes;
    }

    public int readV2(char[] buf, int n) {

        int counter = 0;
        char[] buf4 = new char[4];

        while(n > 0) {
            int x = read4(buf4);
            if(x == 0) {
                break;
            }

            int toCopy = n >= x ? x : n;

            System.arraycopy(buf4, 0, buf, counter, toCopy);
            counter += toCopy;
            n -= toCopy;
        }
        return counter;

    }



    char[] gbuf = new char[4];
    int gbufOffset = 0;
    int gbufSize = 0;

    public int readReEntrantV2(char[] buf, int n) {

        int counter = 0;

        while(n > 0) {

            if(gbufSize == 0) {
                gbufOffset = 0;
                gbufSize = read4(gbuf);
                if(gbufSize == 0) {
                    break;
                }
            }

            int toCopy = n >= gbufSize ? gbufSize : n;

            System.arraycopy(gbuf, gbufOffset, buf, counter, toCopy);
            counter += toCopy;
            gbufSize -= toCopy;
            if(gbufSize != 0) {
                gbufOffset += toCopy;
            }
            n -= toCopy;
        }

        return counter;
    }

     /*
    // Note: The read function will only be called multiple times.
    */

    // maintain states
    private char[] read4buffer =  new char[4];
    private int offset = 0;
    // We need to make sure that if there was anything left in the read4 buffer, we read that
    private int bufSizeLeftInRead4Buffer = 0;

    public int readReenterant(char[] buf, int n) {
        int readBytes = 0;
        boolean eof = false;
        while(!eof && readBytes < n) {

            if(bufSizeLeftInRead4Buffer == 0) {
                offset = 0;
                bufSizeLeftInRead4Buffer = read4(read4buffer);
                if (bufSizeLeftInRead4Buffer < 4) eof = true;
            }

            int bytes = Math.min(n - readBytes, bufSizeLeftInRead4Buffer);
            System.arraycopy(read4buffer, offset, buf, readBytes, bytes);

            // update the bufsize left in read 4 buffer
            bufSizeLeftInRead4Buffer -= bytes;

            // if we did not read all that was in the read4buffer, next time it is either eof
            // or we have to read just the remaining bytes starting at the offset (offset + bytes)
            if(bufSizeLeftInRead4Buffer != 0) {
                offset += bytes;
            }

            readBytes += bytes;
        }

        return readBytes;
    }
}
