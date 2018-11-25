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
	 *		   Optimality: I believe my implementation of this function is optimal as it 
	 *		   has minimal memory usage (only 2 defined variables) and as soon as an array
	 *		   is determined to be invalid, returns false. This prevents the wasting of 
	 *		   compute time.
	 *
	 *         Worst Case Running cost: O(N), Omega(1) 
	 *         Note: N being the number of items in the array.
	 *         Reason:The first if statement takes constant time O(1) as each of the 
	 *         comparisons take constant time. The variable assignment also takes constant
	 *         time O(1). The for-loop takes between O(1) and O(N) time as the loop can
	 *         exit after 1 iteration if the counter variable reaches 0, or it can run
	 *         N times if this does not happen. The final if statement takes constant
	 *         time. 
	 *         Worst case -> O(1) + O(N) + O(1) = O(N)
	 *         Shortest possible cost -> O(1) + O(1) + O(1) = O(1) -> Omega(1)
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
	
	//Optimality: I believe my implementation of this function is optimal as it 
	//requires very little memory (the only variable is the one created by java
	//when the function is called). It is also very light on compute time with
	//the function returning a result as soon as it is available and not running
	//redundant checks.
	
	// Worst case running cost: Theta(1)
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
	 *         Optimality: I believe my implementation of this function is optimal as it 
	 *		   only creates two local variables (both ints -> 32 bits -> 64 bits total).
	 *		   If the input is determined to be invalid, the function will immediately 
	 *		   return false. This prevents the wasting of compute time. 
	 *         
	 *         Note:This function is very similar to the validatePrefixOrder() function.
	 *         Worst Case Running Cost: O(N), Omega(1) 
	 *         Note: N being the number of items in the array.
	 *         Reason:The first if statement takes constant time O(1) as each of the 
	 *         comparisons take constant time. The variable assignment also takes constant
	 *         time O(1). The for-loop takes between O(1) and O(N) time as the loop can
	 *         exit after 1 iteration if the counter variable reaches 0, or it can run
	 *         N times if this does not happen. The final if statement takes constant
	 *         time. 
	 *         Worst case -> O(1) + O(N) + O(1) = O(N)
	 *         Shortest possible cost -> O(1) + O(1) + O(1) = O(1) -> Omega(1)
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
	 * 		Optimality: I believe my implementation of this function is optimal as it 
	 *		allocates only as much memory as is necessary to complete the problem. The
	 *		stack size is limited to the maximum possible size required to compute the 
	 *		result. There is no unnecessary duplication of resources and calculations
	 *		are performed efficiently. 
	 * 		Worst case running cost: Theta(N)
	 * 		Reason: the creation of an array and assignment of an integer value take
	 * 		constant time O(1). The for loop runs N times (N being the size of the 
	 * 		array). The performCalc() method in the array runs in constant time O(1),
	 * 		the Integer.parseInt() method has a running cost of O(L) where L is the 
	 * 		length of the string passed to it. Since this number is not related to the
	 * 		size of the input we will consider it to be a cost of O(1). This results
	 * 		in a running cost of O(1) + ( O(N) * O(1) ) = O(N). This can be narrowed
	 * 		down more effectively if we consider that the only variable is the length
	 * 		of the input N. Therefore we can conclude there is a running cost of 
	 * 		Theta(N).
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
	 *  Optimality: I believe my implementation of this function is optimal as it 
	 * 	does not create any local variables other than the ones creates by the JVM 
	 * 	on calling the function. These variables are all required by the function.
	 * 	Compute time is also kept minimal through the use of immediate returns 
	 * 	after a result is determined.
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
	 * 
	 * 		Optimality: I believe my implementation of this function is optimal as it 
	 *		allocates only as much memory as is necessary to complete the problem. The
	 *		stack size is limited to the maximum possible size required to compute the 
	 *		result. There is no unnecessary duplication of resources and calculations
	 *		are performed efficiently. 
	 * 		Note: Same running cost and explanation as evaluatePrefixOrder().		
	 * 		Worst case running cost: Theta(N)
	 * 		Reason: the creation of an array and assignment of an integer value take
	 * 		constant time O(1). The for loop runs N times (N being the size of the 
	 * 		array). The performCalc() method in the array runs in constant time O(1),
	 * 		the Integer.parseInt() method has a running cost of O(L) where L is the 
	 * 		length of the string passed to it. Since this number is not related to the
	 * 		size of the input we will consider it to be a cost of O(1). This results
	 * 		in a running cost of O(1) + ( O(N) * O(1) ) = O(N). This can be narrowed
	 * 		down more effectively if we consider that the only variable is the length
	 * 		of the input N. Therefore we can conclude there is a running cost of 
	 * 		Theta(N).
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
	 * 
	 * 		Optimality: I believe my implementation of this function is optimal as it 
	 *		allocates only as much memory as is necessary to complete the problem. The
	 *		stack size is limited to the maximum possible size required to compute the 
	 *		result. There is no unnecessary duplication of resources. The methods used
	 *		are efficient and not wasteful.
	 * 		Worst case running cost: Theta(N)
	 * 		Reason: The array and integer assignment take constant time O(1). The
	 * 		for loop runs N times -> O(N). Delimiting the string can take a maximum
	 * 		time of O(N). This results in O(1) + O(N) + O(N) = O(N). However since
	 * 		there is no scenario where the function will run in more or less than O(N)
	 * 		time we can conclude that it has a running cost of Theta(N).
	 * 
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
		return resStack[0].split(" ");
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
	 * 
	 * 		Optimality: I believe my implementation of this function is optimal as it 
	 *		allocates only as much memory as is necessary to complete the problem. The
	 *		stack size is limited to the maximum possible size required to compute the 
	 *		result. There is no unnecessary duplication of resources. The methods used
	 *		are efficient and not wasteful.
	 * 		Note: same as convertPrefixToPostfix()		
	 * 		Worst case running cost: Theta(N)
	 * 		Reason: The array and integer assignment take constant time O(1). The
	 * 		for loop runs N times -> O(N). Delimiting the string can take a maximum
	 * 		time of O(N). This results in O(1) + O(N) + O(N) = O(N). However since
	 * 		there is no scenario where the function will run in more or less than O(N)
	 * 		time we can conclude that it has a running cost of Theta(N).
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

		return resStack[0].split(" ");
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
	 * 	 	
	 * 		Optimality: I believe my implementation of this function is optimal as it 
	 *		allocates only as much memory as is necessary to complete the problem. The
	 *		stack size is limited to the maximum possible size required to compute the 
	 *		result. There is no unnecessary duplication of resources. The methods used
	 *		are efficient and not wasteful.
	 * 		Note: same as convertPrefixToPostfix()		
	 * 		Worst case running cost: Theta(N)
	 * 		Reason: The array and integer assignment take constant time O(1). The
	 * 		for loop runs N times -> O(N). Delimiting the string can take a maximum
	 * 		time of O(N). This results in O(1) + O(N) + O(N) = O(N). However since
	 * 		there is no scenario where the function will run in more or less than O(N)
	 * 		time we can conclude that it has a running cost of Theta(N).
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
		return resStack[0].split(" ");
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
	 * 	 	
	 * 		Optimality: I believe my implementation of this function is optimal as it 
	 *		allocates only as much memory as is necessary to complete the problem. The
	 *		stack size is limited to the maximum possible size required to compute the 
	 *		result. There is no unnecessary duplication of resources. The methods used
	 *		are efficient and not wasteful.
	 * 		Note: same as convertPrefixToPostfix()		
	 * 		Worst case running cost: Theta(N)
	 * 		Reason: The array and integer assignment take constant time O(1). The
	 * 		for loop runs N times -> O(N). Delimiting the string can take a maximum
	 * 		time of O(N). This results in O(1) + O(N) + O(N) = O(N). However since
	 * 		there is no scenario where the function will run in more or less than O(N)
	 * 		time we can conclude that it has a running cost of Theta(N).
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
		return resStack[0].split(" ");
	}

}

/*
 * Data Structures Used In Code: 
 * int (primitive)
 * Integer (object)
 * String (object)
 * Array (String and int)
 * 
 * Methods called for each Data Structure:
 * int - none, item is primitive - has no functions
 * 
 * Integer - .parseInt() method. This method has a running cost of O(N) where N is the 
 * 			 length of the string passed to the method. 
 * 
 * String - .equals() method. This method runs in time O(N) where N are the number of items 
 * 			in the String being compared. I used this method exclusively with strings of 
 * 			size 1 which resulted in a running time of O(1) (also Theta(1)).
 * 		  - .split() method. This method uses regular expressions to determine where to 
 * 			split a string into subStrings, it then calls the .subString() method in the
 * 			Java String class. This method copies the required range from the internal 
 * 			char array in the String object. This operation takes constant time O(1). Since
 * 			the subString method is called m times with m being the number of items in the 
 * 			resulting array: the cost is O(m). However since m is a fraction of N (the size
 * 			of the original array) it can be written as N/x with x being some positive 
 * 			integer. This results in an actual asymptotic running time of O(N).
 * 
 * Array - no array methods are called. Only array accesses are used which have a cost of 
 * 		   O(1).
 * 
 * 
 */
