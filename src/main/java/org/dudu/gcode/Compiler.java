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

import java.io.*;

public class Compiler {
	public static byte[] parse(InputStream program) throws IOException, CompilerException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		try (
				InputStreamReader is = new InputStreamReader(program);
                LineNumberReader br = new LineNumberReader(is)) {

			String line;
			while ((line = br.readLine()) != null) {
				CommandDescriptor candidateCommand = null;
				int candidateCommandLength = 0;

                if (line.trim().isEmpty()) {
                    continue;
                }
                
				for (CommandDescriptor c : GCodeCommand.lookupTable.values()) {
					if (line.startsWith(c.getName())
							&& c.getName().length() > candidateCommandLength) {
						candidateCommand = c;
						candidateCommandLength = c.getName().length();
					}
				}
				
				if (candidateCommand != null) {
                    try {
                        outStream.write(candidateCommand.encode(line.substring(candidateCommandLength)));
                    } catch (Exception e) {
                        throw new CompilerException(br.getLineNumber(), line, e);
                    }
				} else {
					throw new CompilerException(br.getLineNumber(), line, "Unknown command");
				}
			}
		}

		return outStream.toByteArray();
	}
}
