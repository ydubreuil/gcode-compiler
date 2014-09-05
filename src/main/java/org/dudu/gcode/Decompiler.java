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
import java.io.IOException;
import java.io.InputStream;

public class Decompiler {
	
	public static String parse(InputStream stream) throws IOException {
		DataInputStream dataStream = new DataInputStream(stream);
		StringBuilder program = new StringBuilder();
		
		// iterate on commands
		while (dataStream.available() > 0) {
			CommandBuffer commandBuffer = CommandBuffer.decode(dataStream);

			CommandDescriptor command = GCodeCommand.lookupTable.get(commandBuffer.getCode());
			if (command == null) {
				System.out.println("Unknown code " + commandBuffer.getCode());
			} else {
				command.decode(commandBuffer, program);
				program.append("\n");
			}
		}
		
		return program.toString();
	}
}
