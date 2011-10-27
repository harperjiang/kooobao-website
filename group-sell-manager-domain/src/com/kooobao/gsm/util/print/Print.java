package com.kooobao.gsm.util.print;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.harper.frm.gui.swing.print.ComponentPrintable;

import com.kooobao.gsm.domain.entity.delivery.Delivery;
import com.kooobao.util.print.ContactInfo;
import com.kooobao.util.print.ExpressOrderLayout;
import com.kooobao.util.print.setting.PostSettingBean;

public class Print {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EntityManager em = Persistence.createEntityManagerFactory(
				"GroupSellManagerDomain").createEntityManager();
		// ,
		List<Delivery> deliveryList = em
				.createQuery(
						"select d from Delivery d where d.status = 'CREATED' and (d.number is null or d.number = '') and d.company = '邮政'",
						Delivery.class).getResultList();

		for (Delivery delivery : deliveryList) {
			// if (!parse(delivery))
			// continue;
			ExpressOrderLayout orderLayout = compose(delivery);
			print(orderLayout);
		}
	}

	// private static boolean parse(Delivery delivery) {
	// return delivery.getContact().getAddress().contains("上海");
	// // || delivery.getContact().getAddress().contains("北京")
	// // || delivery.getContact().getAddress().contains("浙江")
	// // || delivery.getContact().getAddress().contains("江苏");
	// }

	private static void print(ExpressOrderLayout orderLayout) {
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(new ComponentPrintable(orderLayout));

		try {
			job.print();
		} catch (PrinterException e) {
			throw new RuntimeException(e);
		}

	}

	private static ExpressOrderLayout compose(Delivery delivery) {
		ExpressOrderLayout layout = new ExpressOrderLayout(new PostSettingBean());
		ContactInfo from = new ContactInfo();
		ContactInfo to = new ContactInfo();
		from.setName("舒扬");
		from.setAddress("上海市浦东新区浦建路1288弄12号101室");
		from.setPhone("13072110258");
		layout.setFromInfo(from);
		to.setName(delivery.getContact().getName());
		to.setAddress(delivery.getContact().getAddress());
		to.setPhone(delivery.getContact().getPhone());
		layout.setToInfo(to);

		layout.setRemark("第" + delivery.getOrder().getRefNumber() + "号");
		return layout;
	}

}
