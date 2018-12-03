package org.zigi.tool.jpassrem.generator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StringGenerator implements Iterator<String> {

	private char[] characters = null;
	private int length;
	private int minLength;
	private int maxLength;
	private List<Integer> indexes = null;

	private StringGenerator(char[] chars, int minLength, int maxLength) {
		this.characters = chars;
		this.length = minLength + 1;
		this.minLength = minLength;
		this.maxLength = maxLength + 1;
	}

	public static StringGenerator getInstance(char[] chars, int minLength, int maxLength) {
		StringGenerator instance = new StringGenerator(chars, minLength, maxLength);
		instance.initialize();
		return instance;
	}

	private void initialize() {
		this.indexes = new ArrayList<Integer>(length);
		for (int i = 0; i < length; i++)
			indexes.add(0);
	}

	private void initialize(int length) {
		this.length = length + 1;
		this.indexes = new ArrayList<Integer>(this.length);
		for (int i = 0; i < this.length; i++)
			indexes.add(0);
	}

	public boolean hasNext() {
		return length <= maxLength && indexes.get(length - 1) == 0;
	}

	private void nextIndex() {
		int i = indexes.get(0);
		if (i == characters.length - 1) {
			indexes.set(0, 0);
			nextIndex(1);
		} else {
			indexes.set(0, i + 1);
		}
	}

	private void nextIndex(int index) {
		if (index == length - 1) {
			initialize(length);
		} else {
			int i = indexes.get(index);
			if (i == characters.length - 1) {
				indexes.set(index, 0);
				nextIndex(index + 1);
			} else {
				indexes.set(index, i + 1);
			}
		}
	}

	public String next() {
		StringBuilder sb = new StringBuilder();
		for (int i = length - 2; i >= 0; i--)
			sb.append(characters[indexes.get(i)]);

		nextIndex();

		return sb.toString();
	}

}
