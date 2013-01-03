package com.kooobao.common.json;

import java.io.StringReader;
import java.util.Map;

import com.kooobao.common.json.parser.MapJsonParser;
import com.kooobao.common.json.parser.ParseException;

/**
 * JsonMapReader is a simplified version of json reader that output only maps,
 * collections and strings. It doesn't support java beans.
 * 
 * @author harper
 * 
 */
public class JsonMapReader {

	public Map<String, Object> read(String value) {
		try {
			return new MapJsonParser(new StringReader(value)).result();
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
