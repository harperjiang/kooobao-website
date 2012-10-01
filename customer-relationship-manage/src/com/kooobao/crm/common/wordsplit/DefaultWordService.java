package com.kooobao.crm.common.wordsplit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import org.springframework.util.CollectionUtils;

public class DefaultWordService implements WordService {

	@Override
	public Collection<String> splitWord(String input) {
		List<List<String>> allWords = new ArrayList<List<String>>();
		for (int i = 0; i < input.length(); i++) {
			allWords.add(getDictDao().getWord(input.substring(i)));
		}

		List<List<Word>> resultContainer = new ArrayList<List<Word>>();

		Stack<Integer> indices = new Stack<Integer>();
		Stack<Word> words = new Stack<Word>();
		indices.push(0);

		findWords(allWords, indices, words, resultContainer);

		List<Word> max = null;
		for (List<Word> wordList : resultContainer) {
			if (max == null || wordList.size() > max.size())
				max = wordList;
		}

		return filter(input, max);
	}

	protected Collection<String> filter(String input, List<Word> words) {
		List<String> result = new ArrayList<String>();

		int i = 0;
		for (Word word : words) {
			if (word.getLocation() != i) {
				result.add(input.substring(i, word.getLocation()));
				i = word.getLocation();
			}
			result.add(word.getContent());
			i += word.getContent().length();
		}
		if (i != input.length())
			result.add(input.substring(i));

		return result;
	}

	protected void findWords(List<List<String>> source, Stack<Integer> indices,
			Stack<Word> words, List<List<Word>> resultContainer) {
		int currentIndex = indices.peek();
		while (currentIndex < source.size()
				&& CollectionUtils.isEmpty(source.get(currentIndex))) {
			currentIndex++;
		}
		if (currentIndex >= source.size()
				&& CollectionUtils.isEmpty(source.get(currentIndex))) {
			return;
		}
		indices.pop();
		indices.push(currentIndex);
		for (String currentWord : source.get(currentIndex)) {
			words.push(new Word(currentIndex, currentWord));
			if (currentWord.length() + currentIndex == source.size()) {// Found
				output(resultContainer, words);
			} else {
				indices.push(currentIndex + currentWord.length());
				findWords(source, indices, words, resultContainer);
				indices.pop();
			}
			words.pop();
		}
	}

	private void output(List<List<Word>> resultContainer, Stack<Word> wordStack) {
		List<Word> words = new ArrayList<Word>();
		words.addAll(wordStack);
		resultContainer.add(words);
	}

	protected int minWordSize(List<String> words) {
		int min = -1;
		for (String word : words)
			if (min < 0 || word.length() < min)
				min = word.length();
		return min;
	}

	private DictDao dictDao;

	public DictDao getDictDao() {
		return dictDao;
	}

	public void setDictDao(DictDao dictDao) {
		this.dictDao = dictDao;
	}

}
