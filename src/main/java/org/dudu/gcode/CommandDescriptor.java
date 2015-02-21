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

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;

public class CommandDescriptor {
	final int code;

	final String name;

	final int headerOffset;

	final CField[] fields;

	protected CommandDescriptor(int i, String name, int headerOffset, CField[] fields) {
		this.code = i;
		this.name = name;
		this.headerOffset = headerOffset;
		this.fields = fields;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public CField[] getFields() {
		return fields;
	}

	public void decode(CommandBuffer commandBuffer, StringBuilder program) throws IOException {
		program.append(name);
		BitSet header = commandBuffer.getHeader();
		DataInputStream commandStream = commandBuffer.getContentAsDataStream();

		int i = headerOffset;
		int toSkip = 0;
		for (CField f : fields) {
			if (header.get(i)) {
				if (headerOffset != 0) {
					program.append(" ");
				}

				if (toSkip > 0) {
					commandStream.skip(toSkip);
					toSkip = 0;
				}

				f.decompileBinaryField(commandStream, program);
			} else {
				toSkip += f.getSize();
			}

			i++;
		}
	}

	public byte[] encode(String content) throws IOException {
		ByteArrayOutputStream fieldsOutputStream = new ByteArrayOutputStream(64);
		DataOutputStream fieldsDataOutputStream = new DataOutputStream(fieldsOutputStream);
		BitSet header = new BitSet(16);
		CommandBuffer cb = new CommandBuffer(code, header);

		Iterator<CField> fieldIterator = Arrays.asList(fields).iterator();
		CField currentField;
		int currentFieldId = 0;

		boolean lastToken = false;
		String remainingContent = content;
		String token;

		do {
			int skippedSize = 0;

			// the first token doesn't have a prefix if headerOffset == 0
			if (headerOffset == 0 && currentFieldId == 0) {
				currentField = fieldIterator.next();
			} else {
				remainingContent = remainingContent.trim();
				while(!remainingContent.trim().startsWith((currentField = fieldIterator.next()).getFieldName())) {
					skippedSize += currentField.getSize();
					currentFieldId++;
                    if (!fieldIterator.hasNext()) {
                        throw new IllegalArgumentException("Unable to parse this: " + remainingContent);
                    }
				}
			}

			int nextToken = remainingContent.indexOf(" ");
			if (nextToken != -1 && fieldIterator.hasNext()) {
				token = remainingContent.substring(0, nextToken);
				remainingContent = remainingContent.substring(nextToken);
			} else {
				token = remainingContent;
				lastToken = true;
			}

			if (skippedSize > 0) {
				fieldsDataOutputStream.write(new byte[skippedSize]);
			}

			header.set(currentFieldId + headerOffset);
			String value = token.substring(currentField.getFieldName().length());
			currentField.compileFieldToBinary(value, fieldsDataOutputStream);

			currentFieldId++;
		} while (!lastToken);

		cb.setContent(fieldsOutputStream.toByteArray());
		return cb.encode();
	}

	@Override
	public String toString() {
		return "CommandDescriptor [code=" + code + ", name=" + name
				+ ", headerOffset=" + headerOffset + ", fields="
				+ Arrays.toString(fields) + "]";
	}
}
