package com.kooobao.ecom.crm.common.wordsplit;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.ecom.crm.common.wordsplit.DefaultDictDao;
import com.kooobao.ecom.crm.common.wordsplit.WordTreeNode;

@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class DefaultDictDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	DefaultDictDao dictDao;

	@Before
	public void init() {
		WordTreeNode root = new WordTreeNode(' ', false);
		root.setId(-1);

		WordTreeNode wtn0 = new WordTreeNode('Z', false);
		wtn0.setId(1);
		wtn0.setParent(root);

		WordTreeNode wtn01 = new WordTreeNode('H', true);
		wtn01.setId(2);
		wtn01.setParent(wtn0);

		WordTreeNode wtn02 = new WordTreeNode('G', true);
		wtn02.setId(3);
		wtn02.setParent(wtn0);

		dictDao.store(root);
	}

	@Test
	public void testGetWord() {
		dictDao.init();
		List<String> words = dictDao.getWord("ZHZG");
		assertEquals(2, words.size());
	}

	@Test
	public void testInit() {
		dictDao.init();
		assertEquals(1, dictDao.root.getChildren().size());
		WordTreeNode wtn = dictDao.root.getChildren().get('Z');
		assertEquals(2, wtn.children.size());
	}

}
