package com.kooobao.util.print.setting;

import java.awt.Dimension;
import java.awt.Rectangle;

import com.kooobao.util.print.ExpressOrderSettingBean;

public class QFSettingBean extends ExpressOrderSettingBean {

	public QFSettingBean() {
		super();

		setSize(new Dimension(652, 358));

		setFromNameLoc(new Rectangle(80, 180, 72, 16));
		setFromAddressLoc(new Rectangle(80, 140, 200, 180));
		setFromPhoneLoc(new Rectangle(250, 180, 170, 16));
		setFromMobileLoc(new Rectangle(250, 180, 170, 16));

		setRemarkLoc(new Rectangle(250, 80, 170, 16));

		setFromSignLoc(new Rectangle(90, 340, 72, 16));
		setDateLoc(new Rectangle(170, 340, 120, 16));

		setToNameLoc(new Rectangle(80, 295, 72, 16));
		setToAddressLoc(new Rectangle(80, 235, 250, 180));
		setToPhoneLoc(new Rectangle(250, 295, 170, 16));
		setToMobileLoc(new Rectangle(250, 295, 170, 16));
	}

}
