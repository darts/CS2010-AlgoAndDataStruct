// -------------------------------------------------------------------------
/**
 * Utility class containing validation/evaluation/conversion operations for
 * prefix and postfix arithmetic expressions.
 *
 * @author Senán d'Art
 * @version 1/12/15 13:03:48
 */

public class Arith {
	private static final String PLUS = "+";
	private static final String MINUS = "-";
	private static final String MULT = "*";
	private static final String DIV = "/";
	private static final String L_BRACKET = "(";
	private static final String R_BRACKET = ")";

	// ~ Validation methods
	// ..........................................................

	/**
	 * Validation method for prefix notation.
	 *
	 * @param prefixLiterals : an array containing the string literals hopefully in
	 *                       prefix order. The method assumes that each of these
	 *                       literals can be one of: - "+", "-", "*", or "/" - or a
	 *                       valid string representation of an integer.
	 *
	 * @return true if the parameter is indeed in prefix notation, and false
	 *         otherwise.
	 *
	 *         Worst Case Running Time: O(N), Omega(1) 
	 *         Note: N being the number of items in the array.
	 *         Reason:The first if statement takes constant time O(1) as each of the 
	 *         comparisons take constant time. The variable assignment also takes constant
	 *         time O(1). The for-loop takes between O(1) and O(N) time as the loop can
	 *         exit after 1 iteration if the counter variable reaches 0, or it can run
	 *         N times if this does not happen. The final if statement takes constant
	 *         time. 
	 *         Worst case -> O(1) + O(N) + O(1) = O(N)
	 *         Shortest possible time -> O(1) + O(1) + O(1) = O(1) -> Omega(1)
	 * 
	 **/
	public static boolean validatePrefixOrder(String prefixLiterals[]) {
		if (prefixLiterals == null || prefixLiterals.length == 0)// determine if the string can be valid
			return false;
		int counter = 1;
		for (int i = 0; i < prefixLiterals.length; i++) { // step through each element in the array
			if (counter <= 0)
				return false;
			if (isOperator(prefixLiterals[i]))
				counter++;
			else
				counter--;
		}
		if (counter == 0)
			return true;
		return false;
	}

	// Determine if a given string is an operand.
	// Worst case running time: Theta(1)
	//
	// The String.equals() function has a running time of O(n) where n is the length
	// of the string. In this case the length is 1 -> the function has a running
	// time of O(1). This results in a running time for the function of O(1) * 4 =
	// O(1). (src. java docs). Since the function can only have constant running
	// time, we can define it in a more tight bound as Theta(1).
	private static boolean isOperator(String theOp) {
		if (theOp.equals(PLUS) || theOp.equals(MINUS) || theOp.equals(DIV) || theOp.equals(MULT))
			return true;
		return false;
	}

	/**
	 * Validation method for postfix notation.
	 *
	 * @param postfixLiterals : an array containing the string literals hopefully in
	 *                        postfix order. The method assumes that each of these
	 *                        literals can be one of: - "+", "-", "*", or "/" - or a
	 *                        valid string representation of an integer.
	 *
	 * @return true if the parameter is indeed in postfix notation, and false
	 *         otherwise.
	 *         
	 *         This function is very similar to the validatePrefixOrder() function.
	 *         
	 *         Worst Case Running Time: O(N), Omega(1) 
	 *         Note: N being the number of items in the array.
	 *         Reason:The first if statement takes constant time O(1) as each of the 
	 *         comparisons take constant time. The variable assignment also takes constant
	 *         time O(1). The for-loop takes between O(1) and O(N) time as the loop can
	 *         exit after 1 iteration if the counter variable reaches 0, or it can run
	 *         N times if this does not happen. The final if statement takes constant
	 *         time. 
	 *         Worst case -> O(1) + O(N) + O(1) = O(N)
	 *         Shortest possible time -> O(1) + O(1) + O(1) = O(1) -> Omega(1)
	 **/

	public static boolean validatePostfixOrder(String postfixLiterals[]) {
		if (postfixLiterals == null || postfixLiterals.length == 0)
			return false;
		int counter = 0;
		for (int i = 0; i < postfixLiterals.length; i++) {
			if (!isOperator(postfixLiterals[i]))
				counter++;
			else {
				counter -= 2;
				if (counter < 0)
					return false;
				counter++;
			}
		}
		if (counter == 1)
			return true;
		return false;
	}

	// ~ Evaluation methods
	// ..........................................................

	/**
	 * Evaluation method for prefix notation.
	 *
	 * @param prefixLiterals : an array containing the string literals in prefix
	 *                       order. The method assumes that each of these literals
	 *                       can be one of: "+", "-", "*", or "/" or a valid string
	 *                       representation of an integer.
	 *
	 * @return the integer result of evaluating the expression.
	 * 
	 **/

	public static int evaluatePrefixOrder(String prefixLiterals[]) {
		int[] numStack = new int[(prefixLiterals.length / 2) + 1];
		int lastDigitPointer = -1;
		for (int i = prefixLiterals.length - 1; i >= 0; i--) {
			if (isOperator(prefixLiterals[i])) {
				numStack[lastDigitPointer - 1] = performCalc(prefixLiterals[i], numStack[lastDigitPointer],
						numStack[lastDigitPointer - 1]);
				lastDigitPointer--;
			} else {
				numStack[lastDigitPointer + 1] = Integer.parseInt(prefixLiterals[i]);
				lastDigitPointer++;
			}
		}
		return numStack[0];
	}

	
	/*
	 * 	Worst case running time: Theta(1)
	 *	Reason: the String.equals() function has a running time of O(N) where N 
	 *	is the size of the string being compared. In this case however N = 1 so
	 *	the function has a running time of 1. Arithmetic operations take constant 
	 *  time O(1). Since each of the if statements are performed sequentially, we 
	 *  get a running time of O(1) + O(1) + O(1) = O(1). But the function will 
	 *  never be faster or slower than this so we can say the true running time 
	 *  is Theta(1).
	 */
	private static int performCalc(String op, int opL, int opR) {
		if (op.equals(PLUS))
			return opL + opR;
		else if (op.equals(MULT))
			return opL * opR;
		else if (op.equals(MINUS))
			return opL - opR;
		else
			return opL / opR;
	}

	/**
	 * Evaluation method for postfix notation.
	 *
	 * @param postfixLiterals : an array containing the string literals in postfix
	 *                        order. The method assumes that each of these literals
	 *                        can be one of: - "+", "-", "*", or "/" - or a valid
	 *                        string representation of an integer.
	 *
	 * @return the integer result of evaluating the expression
	 **/
	public static int evaluatePostfixOrder(String postfixLiterals[]) {
		int[] numStack = new int[(postfixLiterals.length / 2) + 1];
		int lastDigitPointer = -1;
		for (int i = 0; i < postfixLiterals.length; i++) {
			if (isOperator(postfixLiterals[i])) {
				numStack[lastDigitPointer - 1] = performCalc(postfixLiterals[i], numStack[lastDigitPointer - 1],
						numStack[lastDigitPointer]);
				lastDigitPointer--;
			} else {
				numStack[lastDigitPointer + 1] = Integer.parseInt(postfixLiterals[i]);
				lastDigitPointer++;
			}
		}
		return numStack[0];
	}

	// ~ Conversion methods
	// ..........................................................

	/**
	 * Converts prefix to postfix.
	 *
	 * @param prefixLiterals : an array containing the string literals in prefix
	 *                       order. The method assumes that each of these literals
	 *                       can be one of: - "+", "-", "*", or "/" - or a valid
	 *                       string representation of an integer.
	 *
	 * @return the expression in postfix order.
	 **/
	public static String[] convertPrefixToPostfix(String prefixLiterals[]) {
		String[] resStack = new String[prefixLiterals.length];
		int lastDigitPointer = -1;
		for (int i = prefixLiterals.length - 1; i >= 0; i--) {
			String itemRead = prefixLiterals[i];
			if (isOperator(itemRead)) {
				String conCat = resStack[lastDigitPointer] + " " + resStack[lastDigitPointer - 1] + " " + itemRead;
				resStack[--lastDigitPointer] = conCat;
			} else {
				resStack[++lastDigitPointer] = itemRead;
			}
		}
		String itemToDelimit = resStack[0];

		String[] res = itemToDelimit.split(" ");
		int i = 0;
		for (int j = 0; j < res.length; j++) {
			prefixLiterals[i++] = res[j];
		}

		return prefixLiterals;
	}

	/**
	 * Converts postfix to prefix.
	 *
	 * @param prefixLiterals : an array containing the string literals in postfix
	 *                       order. The method assumes that each of these literals
	 *                       can be one of: - "+", "-", "*", or "/" - or a valid
	 *                       string representation of an integer.
	 *
	 * @return the expression in prefix order.
	 **/
	public static String[] convertPostfixToPrefix(String postfixLiterals[]) {
		String[] resStack = new String[postfixLiterals.length];
		int lastDigitPointer = -1;
		for (int i = 0; i < postfixLiterals.length; i++) {
			String itemRead = postfixLiterals[i];
			if (isOperator(itemRead)) {
				String conCat = itemRead + " " + resStack[lastDigitPointer - 1] + " " + resStack[lastDigitPointer];
				resStack[--lastDigitPointer] = conCat;
			} else {
				resStack[++lastDigitPointer] = itemRead;
			}
		}
		String itemToDelimit = resStack[0];

		String[] res = itemToDelimit.split(" ");
		int i = 0;
		for (int j = 0; j < res.length; j++) {
			postfixLiterals[i++] = res[j];
		}

		return postfixLiterals;
	}

	/**
	 * Converts prefix to infix.
	 *
	 * @param infixLiterals : an array containing the string literals in prefix
	 *                      order. The method assumes that each of these literals
	 *                      can be one of: - "+", "-", "*", or "/" - or a valid
	 *                      string representation of an integer.
	 *
	 * @return the expression in infix order.
	 **/
	public static String[] convertPrefixToInfix(String prefixLiterals[]) {
		String[] resStack = new String[prefixLiterals.length];
		int lastDigitPointer = -1;
		for (int i = prefixLiterals.length - 1; i >= 0; i--) {
			String itemRead = prefixLiterals[i];
			if (isOperator(itemRead)) {
				String conCat = L_BRACKET + " " + resStack[lastDigitPointer--] + " " + itemRead + " "
						+ resStack[lastDigitPointer] + " " + R_BRACKET;
				resStack[lastDigitPointer] = conCat;
			} else {
				resStack[++lastDigitPointer] = itemRead;
			}
		}
		String[] outStack = new String[(prefixLiterals.length * 2) - 1];
		String itemToDelimit = resStack[0];
		int i = 0;
		String[] res = itemToDelimit.split(" ");
		for (int j = 0; j < res.length; j++) {
			outStack[i] = res[j];
			i++;
		}

		return outStack;
	}

	/**
	 * Converts postfix to infix.
	 *
	 * @param infixLiterals : an array containing the string literals in postfix
	 *                      order. The method assumes that each of these literals
	 *                      can be one of: - "+", "-", "*", or "/" - or a valid
	 *                      string representation of an integer.
	 *
	 * @return the expression in infix order.
	 **/
	public static String[] convertPostfixToInfix(String postfixLiterals[]) {
		String[] resStack = new String[postfixLiterals.length];
		int lastDigitPointer = -1;
		for (int i = 0; i < postfixLiterals.length; i++) {
			String itemRead = postfixLiterals[i];
			if (isOperator(itemRead)) {
				String conCat = L_BRACKET + " " + resStack[lastDigitPointer - 1] + " " + itemRead + " "
						+ resStack[lastDigitPointer--] + " " + R_BRACKET;
				resStack[lastDigitPointer] = conCat;
			} else {
				resStack[++lastDigitPointer] = itemRead;
			}
		}
		String[] outStack = new String[(postfixLiterals.length * 2) - 1];
		int i = 0;
		String itemToDelimit = resStack[i];

		String[] res = itemToDelimit.split(" ");
		for (int j = 0; j < res.length; j++) {
			outStack[i] = res[j];
			i++;
		}
		return outStack;
	}

}

/*
 * Data Structures Used In Code: 
 * int (primitive)
 * String (object)
 * Array (String and int)
 * 
 * Methods called for each Data Structure:
 * int - none, item is primitive - has no functions
 * 
 * String - .equals() method. This method runs in time O(N) where N are the number of items 
 * 			in the String being compared. I used this method exclusively with strings of 
 * 			size 1 which resulted in a running time of O(1) (also Theta(1)).
 * 
 * 
 */
