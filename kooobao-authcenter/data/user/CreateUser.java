package user;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.kooobao.authcenter.domain.entity.User;

public class CreateUser {

	public static void main(String[] args) {
		EntityManager em = Persistence.createEntityManagerFactory(
				"kooobao-authcenter").createEntityManager();
		User user = new User();
		user.setId("cndebbie");
		user.setEncryptedPass(User.encryptPass("debbie1984"));
		Map<String,String> map = new HashMap<String,String>();
		map.put("gsm", "gsm");
		user.setSystems(map);
		
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}
}
