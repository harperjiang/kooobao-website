package com.kooobao.common.json;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Map;

import org.junit.Test;

public class JsonMapReaderTest extends JsonMapReader {

	@Test
	public void testRead() {
		String input = "{\"list1\":[\"x\",\"y\",\"z\",\"w\"],\"list2\":[\"x1\",\"y1\",\"z1\",\"w1\",\"k1\"],\"list3\":[\"x\"]}";
		Map<String, Object> result = new JsonMapReader().read(input);

		assertEquals(4, ((Collection) result.get("list1")).size());
	}

}
