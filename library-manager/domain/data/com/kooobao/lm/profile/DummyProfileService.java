package com.kooobao.lm.profile;

import java.math.BigDecimal;

import com.kooobao.lm.profile.entity.Address;
import com.kooobao.lm.profile.entity.Operator;
import com.kooobao.lm.profile.entity.Visitor;

public abstract class DummyProfileService implements ProfileService {

	public Visitor getVisitor(String id) {
		Visitor v = new Visitor();
		v.setId(id);
		v.setName("赵无敌");
		v.setLevel(1);
		v.setDeposit(new BigDecimal("37"));
		Address addr = new Address();
		addr.setName("张三");
		addr.setLocation("大猪头大傻瓜你脑子有问题吗没有没有问题");
		addr.setPhone("1232141313113");

		v.setAddress(addr);
		v.addAddress(addr);

		Address addr2 = new Address();
		addr2.setName("李四");
		addr2.setLocation("小猪头小傻瓜你没有没有问题脑子有问题吗");
		addr2.setPhone("23322323");

		v.getAvailableAddresses().add(addr2);

		return v;
	}

	public Visitor saveVisitor(Visitor visitor) {
		
		return null;
	}

	public void register(String email, String password) {
		

	}

	public void redeem(Visitor visitor, BigDecimal amount) {
		

	}

	public Operator getOperator(String id) {
		
		return null;
	}
}
