package com.kooobao.common.json;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.junit.Test;

public class JsonMapWriterTest extends JsonMapWriter {

	@Test
	public void testToString() {
		JsonMapWriter writer = new JsonMapWriter();

		Map<String, Collection<String>> maps = new LinkedMap();
		List<String> list1 = new ArrayList<String>();
		list1.add("x");
		list1.add("y");
		list1.add("z");
		list1.add("w");

		maps.put("list1", list1);

		list1 = new ArrayList<String>();
		list1.add("x1");
		list1.add("y1");
		list1.add("z1");
		list1.add("w1");

		maps.put("list2", list1);
		writer.write(maps);

		assertEquals(
				"{\"list1\":[\"x\",\"y\",\"z\",\"w\"],\"list2\":[\"x1\",\"y1\",\"z1\",\"w1\"]}",
				writer.toString());
	}

}
