// -------------------------------------------------------------------------
/**
 * Utility class containing validation/evaluation/conversion operations for
 * prefix and postfix arithmetic expressions.
 *
 * @author Senán d'Art
 * @version 1/12/15 13:03:48
 */

public class Arith {
	private static final String EMPTY = "$";
	private static final String PLUS = "+";
	private static final String MINUS = "-";
	private static final String MULT = "*";
	private static final String DIV = "/";

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
	 *         Method: 1)Take farthest right operand in a row (+ - / 2 4 5 -> /)
	 * 
	 *         2)Perform calculation on subsequent two numbers (+ - / 2 4 5 -> 2 /
	 *         4). Store result in location of the second number. ( + - / 2 4 5 -> +
	 *         - / 2 2 5).
	 * 
	 *         3) Replace the operand and first number with EMPTY, (+ - / 2 2 5 -> +
	 *         - EMPTY EMPTY 2 5). 4) In this case we can ignore the calculation
	 *         part.
	 **/
	public static boolean validatePrefixOrder(String prefixLiterals[]) {
		if(prefixLiterals == null || prefixLiterals.length == 0)
			return false;
		while (moreThanOnePopulated(prefixLiterals)) {
			int op = findRightMostOperand(prefixLiterals);
			int lNum = findLeftMostInt(prefixLiterals);
			if (op == -1 || op > lNum || lNum == -1) {// more than 1 item left in the array and no operands, operand is
														// ahead of numbers, no numbers left
				return false;
			} else {
				prefixLiterals[op] = EMPTY;
				prefixLiterals[lNum] = EMPTY;
			}
		}
		return true;
	}

	static int findLeftMostInt(String[] theArray) {
		int mostLeft = -1;
		if (theArray != null) {
			for (int i = 0; i < theArray.length; i++) {
				String theChar = theArray[i];
				if (!isOperator(theChar) && !theChar.equals(EMPTY)) {
					return i;
				}
			}
		}
		return mostLeft;
	}

	static int findRightMostOperand(String[] theArray) {
		int mostRight = -1;
		if (theArray != null) {
			for (int i = 0; i < theArray.length; i++) {
				String theChar = theArray[i];
				if (isOperator(theChar)) {
					mostRight = i;
				} else if (theChar.equals(EMPTY)) {
				} else {
					return mostRight;
				}
			}
		}
		return mostRight;
	}

	static boolean moreThanOnePopulated(String[] theArray) {
		int count = 0;
		for (int i = 0; i < theArray.length; i++) {
			if (!theArray[i].equals(EMPTY))
				count++;
			if (count > 1)
				return true;
		}
		return false;

	}

	static boolean isOperator(String theOp) {
		if (theOp.equals(PLUS) || theOp.equals(MINUS) || theOp.equals(DIV) || theOp.equals(MULT))
			return true;
		return false;
	}

//  static boolean stringCharsValid(String theString[]) {
//	  for(int i = 0; i < theString.length; i++) {
//		  if(isOperator(theString[i])
//	  }
//	  return true;
//  }

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
		// TODO
		return false;
	}

	// ~ Evaluation methods
	// ..........................................................

	/**
	 * Evaluation method for prefix notation.
	 *
	 * @param prefixLiterals : an array containing the string literals in prefix
	 *                       order. The method assumes that each of these literals
	 *                       can be one of: - "+", "-", "*", or "/" - or a valid
	 *                       string representation of an integer.
	 *
	 * @return the integer result of evaluating the expression
	 **/
	public static int evaluatePrefixOrder(String prefixLiterals[]) {
		// TODO
		return -1;
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
		// TODO
		return -1;
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
		// TODO
		return null;
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
		// TODO
		return null;
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
		// TODO
		return null;
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
		// TODO
		return null;
	}

}
