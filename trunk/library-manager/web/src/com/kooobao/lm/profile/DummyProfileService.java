package com.kooobao.lm.profile;

import java.math.BigDecimal;

import com.kooobao.lm.profile.entity.Address;
import com.kooobao.lm.profile.entity.PersonalInfo;
import com.kooobao.lm.profile.entity.Visitor;

public class DummyProfileService implements ProfileService {

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
		v.getAvailableAddresses().add(addr);

		Address addr2 = new Address();
		addr2.setName("李四");
		addr2.setLocation("小猪头小傻瓜你没有没有问题脑子有问题吗");
		addr2.setPhone("23322323");

		v.getAvailableAddresses().add(addr2);

		return v;
	}

	@Override
	public PersonalInfo getPersonalInfo(Visitor visitor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonalInfo savePersonalInfo(PersonalInfo personalInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Visitor saveVisitor(Visitor visitor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean activateUser(String activateId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void register(String email, String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearInactivateVisitors() {
		// TODO Auto-generated method stub
		
	}
}
