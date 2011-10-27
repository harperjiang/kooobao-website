package com.kooobao.util.print;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import org.harper.frm.gui.swing.manager.BindingManager;
import org.harper.frm.gui.swing.manager.JComboBinding;
import org.harper.frm.gui.swing.manager.JTextBinding;
import org.harper.frm.gui.swing.mvc.Controller;
import org.harper.frm.gui.swing.print.ComponentPrintable;

public class PrintExpressOrderController extends Controller {

	private PrintExpressOrderFrame frame;

	protected BindingManager manager;

	protected PrintExpressOrderBean bean;

	public PrintExpressOrderController() {
		super();

		frame = new PrintExpressOrderFrame(this);
		bean = new PrintExpressOrderBean();
		initManager();
	}

	private void initManager() {
		manager = new BindingManager(bean);
		manager.addBinding(new JTextBinding(frame.getRefNumberField(),
				"refNumber"));
		manager.addBinding(new JComboBinding(frame.getCompanyCombo(), "company"));
		manager.addBinding(new JTextBinding(
				frame.getFromPanel().getNameField(), "fromName"));
		manager.addBinding(new JTextBinding(frame.getFromPanel()
				.getAddressArea(), "fromAddress"));
		manager.addBinding(new JTextBinding(frame.getFromPanel()
				.getMobileField(), "fromMobile"));
		manager.addBinding(new JTextBinding(frame.getToPanel().getNameField(),
				"toName"));
		manager.addBinding(new JTextBinding(
				frame.getToPanel().getAddressArea(), "toAddress"));
		manager.addBinding(new JTextBinding(
				frame.getToPanel().getMobileField(), "toMobile"));
		manager.addBinding(new JTextBinding(frame.getRemarkArea(), "remark"));
		manager.loadAll();
	}

	public void setBean(PrintExpressOrderBean bean) {
		this.bean = bean;
		this.manager.setBean(bean);
	}

	public void load() {

	}

	public void print() {

		// Get Config Bean According to Company
		ExpressOrderSettingBean printbean = bean.getCompany().getPrintSetting();

		ExpressOrderLayout layout = new ExpressOrderLayout(printbean);

		ContactInfo from = new ContactInfo();
		from.setName(bean.getFromName());
		from.setAddress(bean.getFromAddress());
		from.setMobile(bean.getFromMobile());

		ContactInfo to = new ContactInfo();
		to.setName(bean.getToName());
		to.setAddress(bean.getToAddress());
		to.setMobile(bean.getToMobile());

		layout.setFromInfo(from);
		layout.setToInfo(to);
		layout.setRemark(bean.getRemark());
		bean.save();

		// Print
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(new ComponentPrintable(layout));
		if (job.printDialog()) {
			try {
				job.print();
			} catch (PrinterException e) {
				throw new RuntimeException(e);
			}

		}
	}

	public static void main(String[] args) throws Exception {
		new PrintExpressOrderController();
	}
}
