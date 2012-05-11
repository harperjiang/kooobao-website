package com.kooobao.lm.profile;

import java.util.HashMap;
import java.util.Map;

public class DummyProfileService implements ProfileService {

	public Visitor getVisitor(String id) {
		Visitor v = new Visitor();
		v.setId(id);
		Address addr = new Address();
		addr.setName("张三");
		addr.setLocation("大猪头大傻瓜你脑子有问题吗没有没有问题");
		addr.setPhone("1232141313113");

		v.setAddress(addr);
		v.getAvailableAddresses().add(addr);

		Address addr2 = new Address();
		addr2.setName("李四");
		addr2.setLocation("小猪头小傻瓜你没有没有问题脑子有问题吗");
		addr2.setPhone("23322323");

		v.getAvailableAddresses().add(addr2);

		return v;
	}
}