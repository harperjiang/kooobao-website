package com.kooobao.util.print.setting;

import java.awt.Dimension;
import java.awt.Rectangle;

import com.kooobao.util.print.ExpressOrderSettingBean;

public class YTOSettingBean extends ExpressOrderSettingBean {

	public YTOSettingBean() {
		super();
		setSize(new Dimension(652, 358));

		setFromNameLoc(new Rectangle(100, 80, 72, 16));
		setFromAddressLoc(new Rectangle(85, 112, 170, 45));
		setFromPhoneLoc(new Rectangle(113, 170, 170, 16));
		setFromMobileLoc(new Rectangle(113, 193, 150, 16));

		setFromSignLoc(new Rectangle(85, 284, 72, 16));
		setDateLoc(new Rectangle(71, 298, 70, 16));

		setToNameLoc(new Rectangle(346, 179, 72, 16));
		setToAddressLoc(new Rectangle(340, 112, 170, 45));
		setToPhoneLoc(new Rectangle(496, 80, 170, 16));
		setToMobileLoc(new Rectangle(496, 65, 150, 16));
	}
}
