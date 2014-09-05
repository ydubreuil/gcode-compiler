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

import java.util.ArrayList;
import java.util.HashMap;

public class GCodeCommand {
	public static final CFCoordinate A = new CFCoordinate("A");
	public static final CFShort C = new CFShort("C");
	public static final CFShort D = new CFShort("D");
	public static final CFCoordinate H = new CFCoordinate("H");
	public static final CFCoordinate I = new CFCoordinate("I");
	public static final CFShort IC = new CFShort("IC");
	public static final CFCoordinate J = new CFCoordinate("J");
	public static final CFShort R = new CFShort("R");
	public static final CFInt S = new CFInt("S");
	public static final CFShort T = new CFShort("T");
	public static final CFBitArray T_ARRAY = new CFBitArray("T", 12);
	public static final CFShort V = new CFShort("V");
	public static final CFCoordinate X = new CFCoordinate("X");
	public static final CFCoordinate Y = new CFCoordinate("Y");
	public static final CFCoordinate Z = new CFCoordinate("Z");

	public static final CFCoordinate DX = new CFCoordinate("DX");
	public static final CFCoordinate DY = new CFCoordinate("DY");
	public static final CFCoordinate DZ = new CFCoordinate("DZ");

	public static final CFString SUBPGM = new CFString("/", 8);
	public static final CFString PANEL = new CFString("-", 2);
	public static final CFString TOOLBELT = new CFString("/", 8);
	public static final CFString COMMENT = new CFString("", 34);
	public static final CFString LABEL = new CFString("", 8);
	public static final CFString UNIT = new CFString("*", 2);

	public static final CFShort UNNAMED_SHORT = new CFShort("");

	public static final CFInt UNUSED_INT = new CFInt("UNUSED_INT");
	public static final CFShort UNUSED_SHORT = new CFShort("UNUSED_SHORT");

	public static final CommandDescriptor[] getCommandDescriptors() {
		ArrayList<CommandDescriptor> commands = new ArrayList<>();
		commands.add(new CommandDescriptor(0x0001, "H", 1, new CField[] { DX, DY, DZ, PANEL, T, C, R, UNIT, TOOLBELT }));
		commands.add(new CommandDescriptor(0x0002, "F", 0, new CField[] { UNNAMED_SHORT }));
		commands.add(new CommandDescriptor(0x0003, "N", 1, new CField[] { X, Y, Z, V }));
		commands.add(new CommandDescriptor(0x0004, "C", 0, new CField[] { UNNAMED_SHORT }));
		commands.add(new CommandDescriptor(0x0005, "B", 1, new CField[] { X, Y, Z, 	UNUSED_INT, V, S, T_ARRAY }));
		commands.add(new CommandDescriptor(0x0007, "S", 1, new CField[] { SUBPGM, X, Y, Z }));
		commands.add(new CommandDescriptor(0x0008, "G0", 1, new CField[] { X, Y, Z, UNUSED_INT, V, S, T_ARRAY }));
		commands.add(new CommandDescriptor(0x0009, "G1", 1, new CField[] { X, Y, Z, V }));
		commands.add(new CommandDescriptor(0x000A, "G2", 1, new CField[] { X, Y, Z, I, J, V }));
		commands.add(new CommandDescriptor(0x000B, "G3", 1, new CField[] { X, Y, Z, I, J, V }));
		commands.add(new CommandDescriptor(0x000D, "IX", 0, new CField[] { UNNAMED_SHORT }));
		commands.add(new CommandDescriptor(0x000E, "IY", 0, new CField[] { UNNAMED_SHORT }));
		commands.add(new CommandDescriptor(0x000F, "SX", 0, new CField[] { UNNAMED_SHORT }));
		commands.add(new CommandDescriptor(0x0010, "SY", 0, new CField[] { UNNAMED_SHORT }));
		commands.add(new CommandDescriptor(0x0011, "SB", 0, new CField[] { UNNAMED_SHORT }));
		commands.add(new CommandDescriptor(0x0012, "RB", 0, new CField[] { UNNAMED_SHORT }));
		commands.add(new CommandDescriptor(0x0013, "WB", 0, new CField[] { UNNAMED_SHORT, D }));
		commands.add(new CommandDescriptor(0x0018, "SD", 0, new CField[] { UNNAMED_SHORT, UNNAMED_SHORT }));
		commands.add(new CommandDescriptor(0x0019, "O", 1, new CField[] { X, Y, Z }));
		commands.add(new CommandDescriptor(0x0022, "G5", 1, new CField[] { X, Y, Z, V }));
		commands.add(new CommandDescriptor(0x0028, "G0R", 1, new CField[] { X, Y, Z, H, A, IC, V }));
		commands.add(new CommandDescriptor(0x0029, "G1R", 1, new CField[] { X, Y, Z, H, V }));
		commands.add(new CommandDescriptor(0x002A, "G2R", 1, new CField[] { X, Y, Z, H, I, J, V }));
		commands.add(new CommandDescriptor(0x002B, "G3R", 1, new CField[] { X, Y, Z, H, I, J, V }));
		commands.add(new CommandDescriptor(0x002C, "G5R", 1, new CField[] { X, Y, Z, H, V }));
		commands.add(new CommandDescriptor(0x0033, "GOTO", 1, new CField[] { LABEL }));
		commands.add(new CommandDescriptor(0x0036, ".", 0, new CField[] { LABEL }));
		commands.add(new CommandDescriptor(0x0064, "*", 0, new CField[] { COMMENT }));

		return commands.toArray(new CommandDescriptor[] {});
	}

	public static final HashMap<Integer, CommandDescriptor> lookupTable = new HashMap<>();

	static {
		for (CommandDescriptor c : GCodeCommand.getCommandDescriptors()) {
			lookupTable.put(c.getCode(), c);
		}
	}
}
