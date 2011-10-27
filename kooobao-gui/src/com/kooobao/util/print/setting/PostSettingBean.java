package com.kooobao.util.print.setting;

import java.awt.Dimension;
import java.awt.Rectangle;

import com.kooobao.util.print.ExpressOrderSettingBean;

public class PostSettingBean extends ExpressOrderSettingBean {

	public PostSettingBean() {
		super();

		setSize(new Dimension(652, 358));

		setToNameLoc(new Rectangle(85, 145, 72, 16));
		setToAddressLoc(new Rectangle(110, 170, 230, 80));
		setToPhoneLoc(new Rectangle(250, 145, 170, 16));
		setToMobileLoc(new Rectangle(233, 182, 150, 16));

		setRemarkLoc(new Rectangle(250, 120, 170, 16));

		setFromSignLoc(new Rectangle(400, 320, 72, 16));
		setDateLoc(new Rectangle(100, 330, 70, 16));

		setFromNameLoc(new Rectangle(85, 235, 72, 16));
		setFromAddressLoc(new Rectangle(110, 260, 230, 45));
		setFromPhoneLoc(new Rectangle(250, 235, 170, 16));
		setFromMobileLoc(new Rectangle(512, 182, 150, 16));
	}

}
