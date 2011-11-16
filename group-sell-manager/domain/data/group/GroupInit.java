package group;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.kooobao.gsm.domain.entity.group.Group;
import com.kooobao.gsm.domain.entity.group.GroupStatus;

public class GroupInit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("GroupSellManagerDomain");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Group group = new Group();
		group.setStatus(GroupStatus.IN_PROGRESS.name());
		group.setName("爸妈网第69号团购");

		em.persist(group);

		em.getTransaction().commit();
	}

}
