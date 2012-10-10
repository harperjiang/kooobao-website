package com.kooobao.crm.common.wordsplit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

public class DefaultWordServiceTest {

	@Test
	public void testFindWords() {
		List<List<String>> source = new ArrayList<List<String>>();
		for (int i = 0; i < 12; i++)
			source.add(null);
		List<String> source0 = new ArrayList<String>();
		source0.add("上海");
		source.set(0, source0);

		List<String> source4 = new ArrayList<String>();
		source4.add("信息");
		source.set(4, source4);

		List<String> source6 = new ArrayList<String>();
		source6.add("技术");
		source.set(6, source6);

		List<String> source8 = new ArrayList<String>();
		source8.add("有限公司");
		source.set(8, source8);

		Stack<Integer> indices = new Stack<Integer>();
		indices.push(0);
		Stack<Word> words = new Stack<Word>();
		List<List<Word>> resultContainer = new ArrayList<List<Word>>();

		new DefaultWordService().findWords(source, indices, words,
				resultContainer);

		assertEquals(1, resultContainer.size());
	}

	@Test
	public void testFilter() {
		String input = "上海术有信息技术有限公司";
		List<Word> words = new ArrayList<Word>();
		words.add(new Word(0, "上海"));
		words.add(new Word(4, "信息"));
		words.add(new Word(6, "技术"));
		words.add(new Word(8, "有限公司"));
		Collection<String> result = new DefaultWordService().filter(input,
				words);
		assertEquals(5, result.size());
	}

	@Test
	public void testFindWord() {
		DefaultWordService dws = new DefaultWordService();
		dws.setDictDao(new DictDao() {
			@Override
			public List<String> getWord(String part) {
				List<String> result = new ArrayList<String>();
				if (part.startsWith("上"))
					result.add("上海");
				if (part.startsWith("信"))
					result.add("信息");
				if (part.startsWith("技"))
					result.add("技术");
				if (part.startsWith("有限"))
					result.add("有限公司");
				return result;
			}
		});

		Collection<String> result = dws.splitWord("上海术有信息技术有限公司");
		assertEquals(5,result.size());
		assertTrue(result.contains("上海"));
		assertTrue(result.contains("术有"));
		assertTrue(result.contains("信息"));
		assertTrue(result.contains("技术"));
		assertTrue(result.contains("有限公司"));
	}
}
