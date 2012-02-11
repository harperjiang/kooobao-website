package com.kooobao.fr.domain.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FileStorageTest extends FileStorage {

	@Test
	public void testGetAbbreviatedPath() {
		FileStorage fs = new FileStorage();
		fs.setPath("C:\\abcdef\\asdfasdf");
		assertEquals("asdfasdf",fs.getAbbreviatedPath());
	}
}
