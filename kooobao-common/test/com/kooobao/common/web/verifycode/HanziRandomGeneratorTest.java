package com.kooobao.common.web.verifycode;

import org.junit.Test;

public class HanziRandomGeneratorTest {

	@Test
	public void testGenerate() {
		String a = new HanziRandomGenerator().generate(3);
		System.out.println(a);
	}

}
