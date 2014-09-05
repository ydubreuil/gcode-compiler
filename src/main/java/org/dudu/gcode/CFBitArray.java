package org.dudu.gcode;

/*
 * #%L
 * GCode compiler/decompiler
 * %%
 * Copyright (C) 2013 Yoann Dubreuil
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.StringTokenizer;

public class CFBitArray extends CField {
	public CFBitArray(String fieldName, int size) {
		super(fieldName, size);
	}

	public BitSet decodeBitSet(DataInputStream stream) throws IOException {
		// inverse byte array to decode it as big endian
		byte[] bitArray = new byte[size];
		for (int i = size; i > 0; i--) {
			bitArray[i - 1] = stream.readByte();
		}
		BitSet tSet = BitSet.valueOf(bitArray);
		return tSet;
	}

	@Override
	public void decompileBinaryField(DataInputStream stream, StringBuilder program) throws IOException {
		BitSet bitArray = decodeBitSet(stream);

		if (!bitArray.isEmpty()) {
			program.append(fieldName);
			for (int i = 0; i < 12 * 8; i++) {
				if (bitArray.get(i)) {
					program.append(i + 1);
					program.append(" ");
				}
			}
		}
	}

	@Override
	public void compileFieldToBinary(String value, DataOutputStream stream)
			throws IOException {

		BitSet bitArray = new BitSet(size * 8);
		StringTokenizer st = new StringTokenizer(value, " ");
		while(st.hasMoreTokens()) {
			bitArray.set(Integer.valueOf(st.nextToken()) - 1);
		}

		byte[] bigEndianArray = bitArray.toByteArray();
		if (bigEndianArray.length < size) {
			stream.write(new byte[size - bigEndianArray.length]);
		}
		for (int i = bigEndianArray.length; i > 0; i--) {
			stream.writeByte(bigEndianArray[i - 1]);
		}
	}
}
