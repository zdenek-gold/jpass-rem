package org.zigi.tool.jpassrem.generator;

import org.apache.commons.lang.ArrayUtils;

public class Utils {
	public static final char[] ALPH_LOW = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	public static final char[] ALPH_UPP = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	public static final char[] NUM = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	public static char[] concateArrays(char[] array1, char[] array2) {
		return ArrayUtils.addAll(array1, array2);
	}

	public static char[] concateArrays(char[] array1, char[] array2, char[] array3) {
		return ArrayUtils.addAll(ArrayUtils.addAll(array1, array2), array3);
	}
}
