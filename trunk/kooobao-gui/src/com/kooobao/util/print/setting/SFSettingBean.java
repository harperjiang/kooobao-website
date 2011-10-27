package com.kooobao.util.print.setting;

import java.awt.Dimension;
import java.awt.Rectangle;

import com.kooobao.util.print.ExpressOrderSettingBean;

public class SFSettingBean extends ExpressOrderSettingBean {

	public SFSettingBean() {
		super();
		setSize(new Dimension(612, 397));

		setFromNameLoc(new Rectangle(219, 108, 72, 16));
		setFromAddressLoc(new Rectangle(71, 128, 184, 45));
		setFromPhoneLoc(new Rectangle(0, 0, 0, 0));
		setFromMobileLoc(new Rectangle(125, 170, 150, 16));

		setFromSignLoc(new Rectangle(496, 241, 72, 16));
		setDateLoc(new Rectangle(454, 264, 70, 16));

		setToNameLoc(new Rectangle(219, 224, 72, 16));
		setToAddressLoc(new Rectangle(71, 241, 184, 45));
		setToPhoneLoc(new Rectangle(0, 0, 0, 0));
		setToMobileLoc(new Rectangle(125, 283, 150, 16));
	}
}
