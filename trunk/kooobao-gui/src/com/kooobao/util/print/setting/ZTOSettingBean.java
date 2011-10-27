package com.kooobao.util.print.setting;

import java.awt.Dimension;
import java.awt.Rectangle;

import com.kooobao.util.print.ExpressOrderSettingBean;

public class ZTOSettingBean extends ExpressOrderSettingBean {

	public ZTOSettingBean() {
		super();
		setSize(new Dimension(652, 358));

		setFromNameLoc(new Rectangle(100, 80, 72, 16));
		setFromAddressLoc(new Rectangle(100, 110, 250, 180));
		setFromPhoneLoc(new Rectangle(100, 200, 170, 16));
		setFromMobileLoc(new Rectangle(100, 200, 170, 16));

		setFromSignLoc(new Rectangle(85, 284, 72, 16));
		setDateLoc(new Rectangle(71, 298, 120, 16));

		setRemarkLoc(new Rectangle(350, 310, 170, 16));
		
		setToNameLoc(new Rectangle(425, 80, 72, 16));
		setToAddressLoc(new Rectangle(425, 110, 245, 180));
		setToPhoneLoc(new Rectangle(425, 200, 170, 16));
		setToMobileLoc(new Rectangle(425, 200, 170, 16));
	}
}
