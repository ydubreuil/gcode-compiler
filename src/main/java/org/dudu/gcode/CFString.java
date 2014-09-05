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

public class CFString extends CField {
	public CFString(String fieldName, int size) {
		super(fieldName, size);
	}

	@Override
	public void decompileBinaryField(DataInputStream stream, StringBuilder program) throws IOException {
		byte[] buff = new byte[size];
		stream.read(buff);
		program.append(fieldName);

		for (byte b : buff) {
			if (b == '\0') {
				break;
			}
			program.append((char) b);
		}
	}

	@Override
	public void compileFieldToBinary(String value, DataOutputStream stream)
			throws IOException {
		int i;
		for (i = 0; i < value.length(); i++) {
			stream.writeByte(value.charAt(i));
		}

		if (i < size) {
			stream.write(new byte[size - i]);
		}
	}
}
