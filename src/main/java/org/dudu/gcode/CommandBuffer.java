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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.BitSet;

public class CommandBuffer {
	final int code;

	final BitSet header;

	byte[] content;

	public CommandBuffer(int code, BitSet header) {
		super();
		this.code = code;
		this.header = header;
	}

	private CommandBuffer(int code, BitSet header, byte[] content) {
		super();
		this.code = code;
		this.header = header;
		this.content = content;
	}

	public int getCode() {
		return code;
	}

	public BitSet getHeader() {
		return header;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public DataInputStream getContentAsDataStream() {
		return new DataInputStream(new ByteArrayInputStream(content));
	}

	public static CommandBuffer decode(DataInputStream dataStream)
			throws IOException {
		short code = dataStream.readShort();
		short length = dataStream.readShort();
		byte[] header = new byte[2];
		header[1] = dataStream.readByte();
		header[0] = dataStream.readByte();
		byte[] content = new byte[length];
		dataStream.read(content);

		return new CommandBuffer(code, BitSet.valueOf(header), content);
	}

	public byte[] encode() {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(
				content.length + 6);
		try {
			DataOutputStream dataOutStream = new DataOutputStream(outputStream);

			dataOutStream.writeShort(code);
			dataOutStream.writeShort(content.length);

			byte[] headerBigEndian = header.toByteArray();
			if (headerBigEndian.length > 1) {
				dataOutStream.writeByte(headerBigEndian[1]);
			} else {
				dataOutStream.writeByte(0);
			}
			dataOutStream.writeByte(headerBigEndian[0]);
			dataOutStream.write(content);

		} catch (IOException e) {
			// can't figure out how this can happen, but we never know...
			throw new RuntimeException(e);
		}

		return outputStream.toByteArray();
	}
}
