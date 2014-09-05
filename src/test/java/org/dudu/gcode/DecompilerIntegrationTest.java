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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RunWith(JUnit4.class)
public class DecompilerIntegrationTest {

	String decompileResource(String resourceName) throws IOException {
		InputStream stream = getClass().getClassLoader().getResourceAsStream(
				resourceName);
		return Decompiler.parse(stream);
	}

	String getResourceContent(String resourceName) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(getClass()
				.getClassLoader().getResourceAsStream(resourceName)));
		StringBuilder sb = new StringBuilder();

		String currentLine;
		while ((currentLine = br.readLine()) != null) {
			sb.append(currentLine);
			sb.append("\n");
		}

		return sb.toString();
	}

	public void assertDecompilation(String programName) throws IOException {
		String originalDecompile = getResourceContent("samples/" + programName
				+ ".xxl");
		String decompile = decompileResource("samples/" + programName + ".PGM");
		Assert.assertEquals(originalDecompile, decompile);
	}

	@Test
	public void pgm2D() throws IOException {
		assertDecompilation("2D");
	}

	@Test
	public void pgmBASE() throws IOException {
		assertDecompilation("BASE");
	}

	@Test
	public void pgmBEAD() throws IOException {
		assertDecompilation("BEAD");
	}

	@Test
	public void pgmBEAG() throws IOException {
		assertDecompilation("BEAG");
	}

	@Test
	public void pgmBEAULIEU() throws IOException {
		assertDecompilation("BEAULIEU");
	}

	@Test
	public void pgmBP() throws IOException {
		assertDecompilation("BP");
	}

	@Test
	public void pgmBTB70D() throws IOException {
		assertDecompilation("BTB70D");
	}

	@Test
	public void pgmBTB70G() throws IOException {
		assertDecompilation("BTB70G");
	}

	@Test
	public void pgmBTH70G() throws IOException {
		assertDecompilation("BTH70G");
	}

	@Test
	public void pgmC50GR() throws IOException {
		assertDecompilation("C50GR");
	}

	@Test
	public void pgmCOFM197D() throws IOException {
		assertDecompilation("COFM197D");
	}

	@Test
	public void pgmCOFM197G() throws IOException {
		assertDecompilation("COFM197G");
	}

	@Test
	public void pgmCONTOUR() throws IOException {
		assertDecompilation("CONTOUR");
	}

	@Test
	public void pgmE1() throws IOException {
		assertDecompilation("E1");
	}

	@Test
	public void pgmE2() throws IOException {
		assertDecompilation("E2");
	}

	@Test
	public void pgmE3() throws IOException {
		assertDecompilation("E3");
	}

	@Test
	public void pgmENTSUPOR() throws IOException {
		assertDecompilation("ENTSUPOR");
	}

	@Test
	public void pgmES21() throws IOException {
		assertDecompilation("ES21");
	}

	@Test
	public void pgmES2() throws IOException {
		assertDecompilation("ES2");
	}

	@Test
	public void pgmES31() throws IOException {
		assertDecompilation("ES31");
	}

	@Test
	public void pgmES3() throws IOException {
		assertDecompilation("ES3");
	}

	@Test
	public void pgmES411() throws IOException {
		assertDecompilation("ES411");
	}

	@Test
	public void pgmES41() throws IOException {
		assertDecompilation("ES41");
	}

	@Test
	public void pgmES4() throws IOException {
		assertDecompilation("ES4");
	}

	@Test
	public void pgmESSAIPER() throws IOException {
		assertDecompilation("ESSAIPER");
	}

	@Test
	public void pgmESSAI() throws IOException {
		assertDecompilation("ESSAI");
	}

	@Test
	public void pgmHOTTE44D() throws IOException {
		assertDecompilation("HOTTE44D");
	}

	@Test
	public void pgmHOTTE44G() throws IOException {
		assertDecompilation("HOTTE44G");
	}

	@Test
	public void pgmLOLO() throws IOException {
		assertDecompilation("LOLO");
	}

	@Test
	public void pgmMB70D() throws IOException {
		assertDecompilation("MB70D");
	}

	@Test
	public void pgmMB70G() throws IOException {
		assertDecompilation("MB70G");
	}

	@Test
	public void pgmMB70SEFD() throws IOException {
		assertDecompilation("MB70SEFD");
	}

	@Test
	public void pgmMB70SEG() throws IOException {
		assertDecompilation("MB70SEG");
	}

	@Test
	public void pgmMB70TCAD() throws IOException {
		assertDecompilation("MB70TCAD");
	}

	@Test
	public void pgmMB70TCAG() throws IOException {
		assertDecompilation("MB70TCAG");
	}

	@Test
	public void pgmMB78D() throws IOException {
		assertDecompilation("MB78D");
	}

	@Test
	public void pgmMB78FOD() throws IOException {
		assertDecompilation("MB78FOD");
	}

	@Test
	public void pgmMB78FOG() throws IOException {
		assertDecompilation("MB78FOG");
	}

	@Test
	public void pgmMB78G() throws IOException {
		assertDecompilation("MB78G");
	}

	@Test
	public void pgmMB78SED() throws IOException {
		assertDecompilation("MB78SED");
	}

	@Test
	public void pgmMB78SEFD() throws IOException {
		assertDecompilation("MB78SEFD");
	}

	@Test
	public void pgmMB78SEFG() throws IOException {
		assertDecompilation("MB78SEFG");
	}

	@Test
	public void pgmMB78SEG() throws IOException {
		assertDecompilation("MB78SEG");
	}

	@Test
	public void pgmMB78TD() throws IOException {
		assertDecompilation("MB78TD");
	}

	@Test
	public void pgmMB78TFD() throws IOException {
		assertDecompilation("MB78TFD");
	}

	@Test
	public void pgmMB78TFG() throws IOException {
		assertDecompilation("MB78TFG");
	}

	@Test
	public void pgmMB78TG() throws IOException {
		assertDecompilation("MB78TG");
	}

	@Test
	public void pgmMH55D() throws IOException {
		assertDecompilation("MH55D");
	}

	@Test
	public void pgmMH55FD() throws IOException {
		assertDecompilation("MH55FD");
	}

	@Test
	public void pgmMH55FG() throws IOException {
		assertDecompilation("MH55FG");
	}

	@Test
	public void pgmMH55G() throws IOException {
		assertDecompilation("MH55G");
	}

	@Test
	public void pgmMH70D() throws IOException {
		assertDecompilation("MH70D");
	}

	@Test
	public void pgmMH70FD() throws IOException {
		assertDecompilation("MH70FD");
	}

	@Test
	public void pgmMH70FG() throws IOException {
		assertDecompilation("MH70FG");
	}

	@Test
	public void pgmMH70G() throws IOException {
		assertDecompilation("MH70G");
	}

	@Test
	public void pgmMISEAUPO() throws IOException {
		assertDecompilation("MISEAUPO");
	}

	@Test
	public void pgmPDEROUIN() throws IOException {
		assertDecompilation("PDEROUIN");
	}

	@Test
	public void pgmPGD() throws IOException {
		assertDecompilation("PGD");
	}

	@Test
	public void pgmPORTECIN() throws IOException {
		assertDecompilation("PORTECIN");
	}

	@Test
	public void pgmPXD() throws IOException {
		assertDecompilation("PXD");
	}

	@Test
	public void pgmR8300() throws IOException {
		assertDecompilation("R8300");
	}

	@Test
	public void pgmR833D() throws IOException {
		assertDecompilation("R833D");
	}

	@Test
	public void pgmR833G() throws IOException {
		assertDecompilation("R833G");
	}

	@Test
	public void pgmR8510() throws IOException {
		assertDecompilation("R8510");
	}

	@Test
	public void pgmR8() throws IOException {
		assertDecompilation("R8");
	}

	@Test
	public void pgmR9() throws IOException {
		assertDecompilation("R9");
	}

	@Test
	public void pgmRAN650AR() throws IOException {
		assertDecompilation("RAN650AR");
	}

	@Test
	public void pgmSP2A() throws IOException {
		assertDecompilation("SP2A");
	}

	@Test
	public void pgmSP2D() throws IOException {
		assertDecompilation("SP2D");
	}

	@Test
	public void pgmSPDX() throws IOException {
		assertDecompilation("SPDX");
	}

	@Test
	public void pgmT19() throws IOException {
		assertDecompilation("T19");
	}

	@Test
	public void pgmT8X55() throws IOException {
		assertDecompilation("T8X55");
	}

	@Test
	public void pgmTB30() throws IOException {
		assertDecompilation("TB30");
	}

	@Test
	public void pgmTB30P() throws IOException {
		assertDecompilation("TB30P");
	}

	@Test
	public void pgmTB40D() throws IOException {
		assertDecompilation("TB40D");
	}

	@Test
	public void pgmTB40() throws IOException {
		assertDecompilation("TB40");
	}

	@Test
	public void pgmTB40P() throws IOException {
		assertDecompilation("TB40P");
	}

	@Test
	public void pgmTB45() throws IOException {
		assertDecompilation("TB45");
	}

	@Test
	public void pgmTB45P() throws IOException {
		assertDecompilation("TB45P");
	}

	@Test
	public void pgmTB50() throws IOException {
		assertDecompilation("TB50");
	}

	@Test
	public void pgmTB50P() throws IOException {
		assertDecompilation("TB50P");
	}

	@Test
	public void pgmTB55() throws IOException {
		assertDecompilation("TB55");
	}

	@Test
	public void pgmTB55P() throws IOException {
		assertDecompilation("TB55P");
	}

	@Test
	public void pgmTB60HOTE() throws IOException {
		assertDecompilation("TB60HOTE");
	}

	@Test
	public void pgmTB60() throws IOException {
		assertDecompilation("TB60");
	}

	@Test
	public void pgmTB60P() throws IOException {
		assertDecompilation("TB60P");
	}

	@Test
	public void pgmTB70() throws IOException {
		assertDecompilation("TB70");
	}

	@Test
	public void pgmTB75() throws IOException {
		assertDecompilation("TB75");
	}

	@Test
	public void pgmTB75P() throws IOException {
		assertDecompilation("TB75P");
	}

	@Test
	public void pgmTB80() throws IOException {
		assertDecompilation("TB80");
	}

	@Test
	public void pgmTB80P() throws IOException {
		assertDecompilation("TB80P");
	}

	@Test
	public void pgmTB90() throws IOException {
		assertDecompilation("TB90");
	}

	@Test
	public void pgmTB90P() throws IOException {
		assertDecompilation("TB90P");
	}

	@Test
	public void pgmTBH45() throws IOException {
		assertDecompilation("TBH45");
	}

	@Test
	public void pgmTBH50() throws IOException {
		assertDecompilation("TBH50");
	}

	@Test
	public void pgmTBH60FOU() throws IOException {
		assertDecompilation("TBH60FOU");
	}

	@Test
	public void pgmTBH60() throws IOException {
		assertDecompilation("TBH60");
	}

	@Test
	public void pgmTBH70D() throws IOException {
		assertDecompilation("TBH70D");
	}

	@Test
	public void pgmTBH80() throws IOException {
		assertDecompilation("TBH80");
	}

	@Test
	public void pgmTBHFOAV() throws IOException {
		assertDecompilation("TBHFOAV");
	}

	@Test
	public void pgmTBURHG() throws IOException {
		assertDecompilation("TBURHG");
	}

	@Test
	public void pgmTESTA() throws IOException {
		assertDecompilation("TESTA");
	}

	@Test
	public void pgmTESTB() throws IOException {
		assertDecompilation("TESTB");
	}

	@Test
	public void pgmTH30() throws IOException {
		assertDecompilation("TH30");
	}

	@Test
	public void pgmTH40() throws IOException {
		assertDecompilation("TH40");
	}

	@Test
	public void pgmTH45() throws IOException {
		assertDecompilation("TH45");
	}

	@Test
	public void pgmTH50() throws IOException {
		assertDecompilation("TH50");
	}

	@Test
	public void pgmTH60HOTE() throws IOException {
		assertDecompilation("TH60HOTE");
	}

	@Test
	public void pgmTH60() throws IOException {
		assertDecompilation("TH60");
	}

	@Test
	public void pgmTH650D() throws IOException {
		assertDecompilation("TH650D");
	}

	@Test
	public void pgmTH650G() throws IOException {
		assertDecompilation("TH650G");
	}

	@Test
	public void pgmTH70() throws IOException {
		assertDecompilation("TH70");
	}

	@Test
	public void pgmTH80() throws IOException {
		assertDecompilation("TH80");
	}

	@Test
	public void pgmTH85() throws IOException {
		assertDecompilation("TH85");
	}

	@Test
	public void pgmTOURNIQT() throws IOException {
		assertDecompilation("TOURNIQT");
	}

	@Test
	public void pgmVB36() throws IOException {
		assertDecompilation("VB36");
	}
}
