package com.kooobao.common.web.verifycode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

public class VerifyCodeManager {

	private static VerifyCodeManager instance = new VerifyCodeManager();

	public static VerifyCodeManager getInstance() {
		return instance;
	}

	private Map<VerifyKey, String> datas = new ConcurrentHashMap<VerifyKey, String>();

	public VerifyCodeManager() {
		super();
		setRandomGen(new HanziRandomGenerator());
	}

	public ImageWriter generate(VerifyKey key) {
		String data = getRandomGen().generate(4);
		JpgImageWriter giw = new JpgImageWriter();
		giw.setContent(data);
		synchronized (key) {
			datas.put(key, data);
		}
		return giw;
	}

	public VerifyKey createKey() {
		return new VerifyKey();
	}

	public boolean verify(VerifyKey vkey, String input) {
		if (StringUtils.isEmpty(input))
			return false;
		synchronized (vkey) {
			return input.equals(datas.get(vkey));
		}
	}

	private RandomGenerator randomGen;

	public RandomGenerator getRandomGen() {
		return randomGen;
	}

	public void setRandomGen(RandomGenerator randomGen) {
		this.randomGen = randomGen;
	}

}
