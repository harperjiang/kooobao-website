package com.kooobao.util.print.setting;

import java.awt.Dimension;
import java.awt.Rectangle;

import org.harper.frm.gui.swing.print.border.LineBorder;

import com.kooobao.util.print.ExpressOrderSettingBean;

public class QFSettingBean extends ExpressOrderSettingBean {

	public QFSettingBean() {
		super();

		setSize(new Dimension(652, 358));

		setFromNameLoc(new Rectangle(80, 90, 72, 16));
		setFromAddressLoc(new Rectangle(80, 140, 200, 180));
		setFromPhoneLoc(new Rectangle(250, 195, 170, 16));
		setFromMobileLoc(new Rectangle(250, 195, 170, 16));

		setRemarkLoc(new Rectangle(340, 110, 200, 80));

		setFromSignLoc(new Rectangle(70, 375, 72, 16));
		setDateLoc(new Rectangle(150, 375, 120, 16));

		setToNameLoc(new Rectangle(80, 235, 72, 16));
		setToAddressLoc(new Rectangle(80, 295, 250, 180));
		setToPhoneLoc(new Rectangle(80, 350, 172, 16));
		setToMobileLoc(new Rectangle(80, 350, 172, 16));
	}

}
