package com.kooobao.wechat.reply.game.word;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Processor {

	public static void main(String[] args) throws Exception {
		processTOEFL();
	}

	protected static void processTOEFL() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(Thread
				.currentThread()
				.getContextClassLoader()
				.getResourceAsStream(
						"com/kooobao/wechat/reply/game/word/TOEFL.origin")));
		PrintWriter pw = new PrintWriter(new FileOutputStream("TOEFL.txt"));
		String line = null;
		Pattern pattern = Pattern.compile("^([a-zA-Z\\-\\(\\) ,]+)[\t ]+(.*)$");
		while ((line = br.readLine()) != null) {
			Matcher matcher = pattern.matcher(line);
			if (!matcher.matches())
				System.out.println(line);
			else {
				pw.println(MessageFormat.format("{0}\t{1}", matcher.group(1),
						matcher.group(2)));
			}
		}
		pw.close();
		br.close();
	}

	protected static void processGRE() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(Thread
				.currentThread()
				.getContextClassLoader()
				.getResourceAsStream(
						"com/kooobao/wechat/reply/game/word/GRE.origin")));
		PrintWriter pw = new PrintWriter(new FileOutputStream("GRE.txt"));
		String line = null;
		Pattern pattern = Pattern
				.compile("^([0-9]+)#[\t 　]*([a-zA-Z \\-,'’\\(\\)]+)#[\t 　]*([a-z．\\.]*)(.*)$");
		Map<String, String> words = new HashMap<String, String>();
		while ((line = br.readLine()) != null) {
			Matcher matcher = pattern.matcher(line);
			if (!matcher.matches())
				System.out.println(line);
			else {
				pw.println(MessageFormat.format("{0}\t{1}", matcher.group(2),
						matcher.group(4)));
			}
		}
		pw.close();
		br.close();
	}
}
