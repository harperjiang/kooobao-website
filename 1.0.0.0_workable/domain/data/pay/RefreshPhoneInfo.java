package pay;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.apache.commons.lang.StringUtils;

import com.kooobao.gsm.domain.entity.delivery.Delivery;

public class RefreshPhoneInfo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String patternStr = ".*(\\d{11}).*";
		Pattern pattern = Pattern.compile(patternStr);
		EntityManager em = Persistence.createEntityManagerFactory(
				"GroupSellManagerDomain").createEntityManager();

		em.getTransaction().begin();
		List<Delivery> delivery = em.createQuery("select d from Delivery d",
				Delivery.class).getResultList();

		for (Delivery d : delivery) {
			d.getContact().setAddress(d.getOrder().getAddress());
			d.getContact().setName(d.getOrder().getContact().getName());
			d.getContact().setPhone(d.getOrder().getContact().getPhone());
			Matcher matcher = pattern.matcher(d.getContact().getAddress());
			if (matcher.matches()
					&& StringUtils.isEmpty(d.getContact().getPhone())) {
				d.getContact().setPhone(matcher.group(1));
			}
		}

		em.getTransaction().commit();
	}
}
