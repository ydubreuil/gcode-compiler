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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class MainClass {
	static void usage() {
		System.out.println("GCode compiler/decompiler");
		System.out.println("Usage: java -jar gcode-compiler.jar <-c|-d> infile [outfile]");
		System.out.println();
		System.out.println("  -c: compile XXL text file infile to PGM outfile");
		System.out.println("  -d: decompile PGM binary file infile to XXL outfile");
		System.out.println();
		System.out.println("Report bugs to https://github.com/ydubreuil/gcode-compiler");
	}

	static void compile(String inFile, String outFileArg) throws IOException {
		String outFile = outFileArg;
		if (outFile == null) {
			outFile = inFile.substring(0, inFile.lastIndexOf('.')) + ".pgm";
		}

		try (FileOutputStream fout = new FileOutputStream(outFile)) {
            try {
                fout.write(Compiler.parse(new FileInputStream(inFile)));
            } catch (CompilerException e) {
                e.printError();
            }
        }
	}

	static void decompile(String inFile, String outFileArg) throws IOException {
		String outFile = outFileArg;
		if (outFile == null) {
			outFile = inFile.substring(0, inFile.lastIndexOf('.')) + ".xxl";
		}

		try (FileWriter fout = new FileWriter(outFile)) {
			fout.write(Decompiler.parse(new FileInputStream(inFile)));
		}
	}

	public static void main(String[] args) throws IOException {
		if (args.length == 2 || args.length == 3) {
			String infile = args[1];
			String outfile = null;
			if (args.length == 3) {
				outfile = args[2];
			}

			switch (args[0]) {
			case "-c":
				compile(infile, outfile);
				break;
			case "-d":
				decompile(infile, outfile);
				break;
			default:
				System.out.println("Unknown argument: " + args[0]);
				System.out.println();
				usage();
			}
		} else {
			usage();
		}
	}
}
