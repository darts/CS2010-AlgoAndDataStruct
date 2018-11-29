import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class test {
	public static final int MAX_LENGTH_BITS = 65536;// Max num. of bits (2 bytes -> short)
	public static final int MAX_LENGTH_BYTES = MAX_LENGTH_BITS / 8;
//	public static void main(String[] args) {
//		String s = "hello";
//		System.out.println(s.length());
//		System.out.println(s.getBytes(StandardCharsets.UTF_16).length);
//		
//		System.out.println(s.hashCode());
//		System.out.println(new String(s.getBytes(StandardCharsets.UTF_16),StandardCharsets.UTF_16).hashCode());
//		System.out.println(new String(s.getBytes(StandardCharsets.UTF_16),StandardCharsets.UTF_16));
//	
//		int[][] intArr = {new int[1], new int[1]};
//		System.out.println(intArr.length);
//		System.out.println(System.currentTimeMillis());
//		Timer timeoutTimer = new Timer();
//		timeoutTimer.schedule(new TimerTask() {
//			public void run() {
//				System.out.println(System.currentTimeMillis());
//				timeoutTimer.cancel();
//				timeoutTimer.cancel();
//			}
//		}, 7000);// Resend in 7 seconds
//		
//		System.out.println(timeoutTimer.purge());
//		System.out.println(timeoutTimer.getClass());
		//timeoutTimer = null;
//		
//		byte num = 10;
//		System.out.println(num);
//		System.out.println((int)num);
//		File theFile = new File("testStuff.txt");
//		BufferedReader theReader = null;
//		try {
//			theReader = new BufferedReader(new FileReader(theFile));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String fData = "";
//		String ln = "";
//		try {
//			while((ln = theReader.readLine()) != null)
//				fData += ln;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			theReader.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(fData);
//		byte[][] bA = splitStr(fData);
//		for(byte[] a : bA)
//			System.out.println(a);
////		byte[] b = bA[0] + bA[1];
//		System.out.println(bA[0].length + "  == length");
//		byte[] data = new byte[bA[0].length + 2];
//		byte[] contentArray = Arrays.copyOfRange(data,2 - 1,data.length -1);
//		String content = new String(contentArray, StandardCharsets.UTF_16);
//		System.out.println(content);
//		
//		System.out.println(Arrays.toString(getWindowLocs((byte)0,(byte)5,5,10)));
//		
//	}
	
	private static byte[][] splitStr(String theString) {// split a string into a series of byte arrays
		byte[] strByteArr = theString.getBytes(StandardCharsets.UTF_16);// string -> byte[]
		int noOfStrings = strByteArr.length / test.MAX_LENGTH_BYTES;// number of byte[] required
		if (strByteArr.length % test.MAX_LENGTH_BYTES != 0)// check for a shorter byte[] on the end
			noOfStrings++;
		System.out.println(noOfStrings + " strings");
		byte[][] resByte = new byte[noOfStrings][];// new byte array array
		int start = 0;
		// if the length of the array is less than the max, use it as the max
		int end = (strByteArr.length < test.MAX_LENGTH_BYTES) ? strByteArr.length : test.MAX_LENGTH_BYTES;
		for (int i = 0; i < resByte.length; i++) {// for every byte array
			System.out.println(end + " end");
//			System.out.println(Arrays.copyOfRange(strByteArr, start, end));
			resByte[i] = Arrays.copyOfRange(strByteArr, start, end);// copy section of byte[]
//			System.out.println(Arrays.toString(rsString));
			start += test.MAX_LENGTH_BYTES + 1;
			end = ((end + test.MAX_LENGTH_BYTES) > strByteArr.length) ? strByteArr.length
					: (end + test.MAX_LENGTH_BYTES);
		}
		return resByte;
	}

	public static byte[] getWindowLocs(byte winMax, byte winMin, int winWidth, int size) {
		byte[] resArr = new byte[winWidth];
		int j = 0;
		if (winMax > winMin) {
			for (byte i = winMin; i < winMax; i++) {
				System.out.println("i=" + i + "   j=" + j);
				resArr[j++] = i;
				
			}
			return resArr;
		}
		for (byte i = winMin; i < size; i++) {
			resArr[j++] = i;
		}
		for (byte i = 0; i < winMax; i++) {
			resArr[j++] = i;
		}
		return resArr;
	}
}
