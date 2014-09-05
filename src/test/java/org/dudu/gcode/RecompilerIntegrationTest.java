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

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RunWith(JUnit4.class)
public class RecompilerIntegrationTest {

	byte[] compileResource(String resourceName) throws IOException {
		InputStream stream = getClass().getClassLoader().getResourceAsStream(
				resourceName);
		return Compiler.parse(stream);
	}

	String decompileResource(String resourceName) throws IOException {
		InputStream stream = getClass().getClassLoader().getResourceAsStream(
				resourceName);
		return Decompiler.parse(stream);
	}

	public void assertRecompilation(String programName) throws IOException {
		byte[] compile = compileResource("samples/" + programName + ".xxl");
		String decompile = Decompiler.parse(new ByteArrayInputStream(compile));
		byte[] recompile = Compiler.parse(new ByteArrayInputStream(decompile.getBytes()));

		Assert.assertArrayEquals(compile, recompile);
	}

	@Test
	public void pgm2D() throws IOException {
		assertRecompilation("2D");
	}

	@Test
	public void pgmBASE() throws IOException {
		assertRecompilation("BASE");
	}

	@Test
	public void pgmBEAD() throws IOException {
		assertRecompilation("BEAD");
	}

	@Test
	public void pgmBEAG() throws IOException {
		assertRecompilation("BEAG");
	}

	@Test
	public void pgmBEAULIEU() throws IOException {
		assertRecompilation("BEAULIEU");
	}

	@Test
	public void pgmBP() throws IOException {
		assertRecompilation("BP");
	}

	@Test
	public void pgmBTB70D() throws IOException {
		assertRecompilation("BTB70D");
	}

	@Test
	public void pgmBTB70G() throws IOException {
		assertRecompilation("BTB70G");
	}

	@Test
	public void pgmBTH70G() throws IOException {
		assertRecompilation("BTH70G");
	}

	@Test
	public void pgmC50GR() throws IOException {
		assertRecompilation("C50GR");
	}

	@Test
	public void pgmCOFM197D() throws IOException {
		assertRecompilation("COFM197D");
	}

	@Test
	public void pgmCOFM197G() throws IOException {
		assertRecompilation("COFM197G");
	}

	@Test
	public void pgmCONTOUR() throws IOException {
		assertRecompilation("CONTOUR");
	}

	@Test
	public void pgmE1() throws IOException {
		assertRecompilation("E1");
	}

	@Test
	public void pgmE2() throws IOException {
		assertRecompilation("E2");
	}

	@Test
	public void pgmE3() throws IOException {
		assertRecompilation("E3");
	}

	@Test
	public void pgmENTSUPOR() throws IOException {
		assertRecompilation("ENTSUPOR");
	}

	@Test
	public void pgmES21() throws IOException {
		assertRecompilation("ES21");
	}

	@Test
	public void pgmES2() throws IOException {
		assertRecompilation("ES2");
	}

	@Test
	public void pgmES31() throws IOException {
		assertRecompilation("ES31");
	}

	@Test
	public void pgmES3() throws IOException {
		assertRecompilation("ES3");
	}

	@Test
	public void pgmES411() throws IOException {
		assertRecompilation("ES411");
	}

	@Test
	public void pgmES41() throws IOException {
		assertRecompilation("ES41");
	}

	@Test
	public void pgmES4() throws IOException {
		assertRecompilation("ES4");
	}

	@Test
	public void pgmESSAIPER() throws IOException {
		assertRecompilation("ESSAIPER");
	}

	@Test
	public void pgmESSAI() throws IOException {
		assertRecompilation("ESSAI");
	}

	@Test
	public void pgmHOTTE44D() throws IOException {
		assertRecompilation("HOTTE44D");
	}

	@Test
	public void pgmHOTTE44G() throws IOException {
		assertRecompilation("HOTTE44G");
	}

	@Test
	public void pgmLOLO() throws IOException {
		assertRecompilation("LOLO");
	}

	@Test
	public void pgmMB70D() throws IOException {
		assertRecompilation("MB70D");
	}

	@Test
	public void pgmMB70G() throws IOException {
		assertRecompilation("MB70G");
	}

	@Test
	public void pgmMB70SEFD() throws IOException {
		assertRecompilation("MB70SEFD");
	}

	@Test
	public void pgmMB70SEG() throws IOException {
		assertRecompilation("MB70SEG");
	}

	@Test
	public void pgmMB70TCAD() throws IOException {
		assertRecompilation("MB70TCAD");
	}

	@Test
	public void pgmMB70TCAG() throws IOException {
		assertRecompilation("MB70TCAG");
	}

	@Test
	public void pgmMB78D() throws IOException {
		assertRecompilation("MB78D");
	}

	@Test
	public void pgmMB78FOD() throws IOException {
		assertRecompilation("MB78FOD");
	}

	@Test
	public void pgmMB78FOG() throws IOException {
		assertRecompilation("MB78FOG");
	}

	@Test
	public void pgmMB78G() throws IOException {
		assertRecompilation("MB78G");
	}

	@Test
	public void pgmMB78SED() throws IOException {
		assertRecompilation("MB78SED");
	}

	@Test
	public void pgmMB78SEFD() throws IOException {
		assertRecompilation("MB78SEFD");
	}

	@Test
	public void pgmMB78SEFG() throws IOException {
		assertRecompilation("MB78SEFG");
	}

	@Test
	public void pgmMB78SEG() throws IOException {
		assertRecompilation("MB78SEG");
	}

	@Test
	public void pgmMB78TD() throws IOException {
		assertRecompilation("MB78TD");
	}

	@Test
	public void pgmMB78TFD() throws IOException {
		assertRecompilation("MB78TFD");
	}

	@Test
	public void pgmMB78TFG() throws IOException {
		assertRecompilation("MB78TFG");
	}

	@Test
	public void pgmMB78TG() throws IOException {
		assertRecompilation("MB78TG");
	}

	@Test
	public void pgmMH55D() throws IOException {
		assertRecompilation("MH55D");
	}

	@Test
	public void pgmMH55FD() throws IOException {
		assertRecompilation("MH55FD");
	}

	@Test
	public void pgmMH55FG() throws IOException {
		assertRecompilation("MH55FG");
	}

	@Test
	public void pgmMH55G() throws IOException {
		assertRecompilation("MH55G");
	}

	@Test
	public void pgmMH70D() throws IOException {
		assertRecompilation("MH70D");
	}

	@Test
	public void pgmMH70FD() throws IOException {
		assertRecompilation("MH70FD");
	}

	@Test
	public void pgmMH70FG() throws IOException {
		assertRecompilation("MH70FG");
	}

	@Test
	public void pgmMH70G() throws IOException {
		assertRecompilation("MH70G");
	}

	@Test
	public void pgmMISEAUPO() throws IOException {
		assertRecompilation("MISEAUPO");
	}

	@Test
	public void pgmPDEROUIN() throws IOException {
		assertRecompilation("PDEROUIN");
	}

	@Test
	public void pgmPGD() throws IOException {
		assertRecompilation("PGD");
	}

	@Test
	public void pgmPORTECIN() throws IOException {
		assertRecompilation("PORTECIN");
	}

	@Test
	public void pgmPXD() throws IOException {
		assertRecompilation("PXD");
	}

	@Test
	public void pgmR8300() throws IOException {
		assertRecompilation("R8300");
	}

	@Test
	public void pgmR833D() throws IOException {
		assertRecompilation("R833D");
	}

	@Test
	public void pgmR833G() throws IOException {
		assertRecompilation("R833G");
	}

	@Test
	public void pgmR8510() throws IOException {
		assertRecompilation("R8510");
	}

	@Test
	public void pgmR8() throws IOException {
		assertRecompilation("R8");
	}

	@Test
	public void pgmR9() throws IOException {
		assertRecompilation("R9");
	}

	@Test
	public void pgmRAN650AR() throws IOException {
		assertRecompilation("RAN650AR");
	}

	@Test
	public void pgmSP2A() throws IOException {
		assertRecompilation("SP2A");
	}

	@Test
	public void pgmSP2D() throws IOException {
		assertRecompilation("SP2D");
	}

	@Test
	public void pgmSPDX() throws IOException {
		assertRecompilation("SPDX");
	}

	@Test
	public void pgmT19() throws IOException {
		assertRecompilation("T19");
	}

	@Test
	public void pgmT8X55() throws IOException {
		assertRecompilation("T8X55");
	}

	@Test
	public void pgmTB30() throws IOException {
		assertRecompilation("TB30");
	}

	@Test
	public void pgmTB30P() throws IOException {
		assertRecompilation("TB30P");
	}

	@Test
	public void pgmTB40D() throws IOException {
		assertRecompilation("TB40D");
	}

	@Test
	public void pgmTB40() throws IOException {
		assertRecompilation("TB40");
	}

	@Test
	public void pgmTB40P() throws IOException {
		assertRecompilation("TB40P");
	}

	@Test
	public void pgmTB45() throws IOException {
		assertRecompilation("TB45");
	}

	@Test
	public void pgmTB45P() throws IOException {
		assertRecompilation("TB45P");
	}

	@Test
	public void pgmTB50() throws IOException {
		assertRecompilation("TB50");
	}

	@Test
	public void pgmTB50P() throws IOException {
		assertRecompilation("TB50P");
	}

	@Test
	public void pgmTB55() throws IOException {
		assertRecompilation("TB55");
	}

	@Test
	public void pgmTB55P() throws IOException {
		assertRecompilation("TB55P");
	}

	@Test
	public void pgmTB60HOTE() throws IOException {
		assertRecompilation("TB60HOTE");
	}

	@Test
	public void pgmTB60() throws IOException {
		assertRecompilation("TB60");
	}

	@Test
	public void pgmTB60P() throws IOException {
		assertRecompilation("TB60P");
	}

	@Test
	public void pgmTB70() throws IOException {
		assertRecompilation("TB70");
	}

	@Test
	public void pgmTB75() throws IOException {
		assertRecompilation("TB75");
	}

	@Test
	public void pgmTB75P() throws IOException {
		assertRecompilation("TB75P");
	}

	@Test
	public void pgmTB80() throws IOException {
		assertRecompilation("TB80");
	}

	@Test
	public void pgmTB80P() throws IOException {
		assertRecompilation("TB80P");
	}

	@Test
	public void pgmTB90() throws IOException {
		assertRecompilation("TB90");
	}

	@Test
	public void pgmTB90P() throws IOException {
		assertRecompilation("TB90P");
	}

	@Test
	public void pgmTBH45() throws IOException {
		assertRecompilation("TBH45");
	}

	@Test
	public void pgmTBH50() throws IOException {
		assertRecompilation("TBH50");
	}

	@Test
	public void pgmTBH60FOU() throws IOException {
		assertRecompilation("TBH60FOU");
	}

	@Test
	public void pgmTBH60() throws IOException {
		assertRecompilation("TBH60");
	}

	@Test
	public void pgmTBH70D() throws IOException {
		assertRecompilation("TBH70D");
	}

	@Test
	public void pgmTBH80() throws IOException {
		assertRecompilation("TBH80");
	}

	@Test
	public void pgmTBHFOAV() throws IOException {
		assertRecompilation("TBHFOAV");
	}

	@Test
	public void pgmTBURHG() throws IOException {
		assertRecompilation("TBURHG");
	}

	@Test
	public void pgmTESTA() throws IOException {
		assertRecompilation("TESTA");
	}

	@Test
	public void pgmTESTB() throws IOException {
		assertRecompilation("TESTB");
	}

	@Test
	public void pgmTH30() throws IOException {
		assertRecompilation("TH30");
	}

	@Test
	public void pgmTH40() throws IOException {
		assertRecompilation("TH40");
	}

	@Test
	public void pgmTH45() throws IOException {
		assertRecompilation("TH45");
	}

	@Test
	public void pgmTH50() throws IOException {
		assertRecompilation("TH50");
	}

	@Test
	public void pgmTH60HOTE() throws IOException {
		assertRecompilation("TH60HOTE");
	}

	@Test
	public void pgmTH60() throws IOException {
		assertRecompilation("TH60");
	}

	@Test
	public void pgmTH650D() throws IOException {
		assertRecompilation("TH650D");
	}

	@Test
	public void pgmTH650G() throws IOException {
		assertRecompilation("TH650G");
	}

	@Test
	public void pgmTH70() throws IOException {
		assertRecompilation("TH70");
	}

	@Test
	public void pgmTH80() throws IOException {
		assertRecompilation("TH80");
	}

	@Test
	public void pgmTH85() throws IOException {
		assertRecompilation("TH85");
	}

	@Test
	public void pgmTOURNIQT() throws IOException {
		assertRecompilation("TOURNIQT");
	}

	@Test
	public void pgmVB36() throws IOException {
		assertRecompilation("VB36");
	}
}
