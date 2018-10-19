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
	 * 
	 *         Worst case running time: O(n)
	 * 
	 *         Reason: the first if statement takes constant time O(1) as each of
	 *         the comparisons take constant time. The for loop runs n times. Inside
	 *         the for loop are 2 constant time comparisons -> n * O(1) = O(n)
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
	// Worst case running time: O(1)
	//
	// The String.equals() function has a running time of O(n) where n is the length
	// of the string. In this case the length is 1 -> the function has a running
	// time of O(1). This results in a running time for the function of O(1) * 4 =
	// O(1). (src. java docs).
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
 * Data Structures Used In Code: Array (String and Int)
 * 
 * 
 * 
 * 
 */
