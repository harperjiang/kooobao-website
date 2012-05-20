package com.kooobao.common.web.verifycode;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class ImageWriterHelper {

	public void write(Graphics graphic, String data, int width, int height) {

		Font old = graphic.getFont();
		Font newFont = old.deriveFont(16f);
		graphic.setFont(newFont);
		FontMetrics fm = graphic.getFontMetrics();
		int fHeight = fm.getAscent();
		int fWidth = fm.stringWidth(data);
		graphic.drawString(data, (width - fWidth) / 2, (height + fHeight) / 2);
		graphic.setFont(old);
	}
}
