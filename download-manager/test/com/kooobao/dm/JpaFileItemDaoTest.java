package com.kooobao.dm;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class JpaFileItemDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private JpaFileItemDao fileItemDao;

	@Before
	public void prepareData() {
		FileItem root = new FileItem();
		root.setFileId(1);
		root.setDisplayName("Good File");
		root.setFilePath("abcd");

		fileItemDao.store(root);

		FileItem child1 = new FileItem();
		child1.setFileId(100);
		child1.setParent(root);
		child1.setDisplayName("Child File");
		child1.setFilePath("gddd");
		fileItemDao.store(child1);
	}

	@Test
	public void testGetFiles() {
		FileItem root = fileItemDao.find(1);
		List<FileItem> children = fileItemDao.getFiles(root);

		assertEquals(1, children.size());
		assertEquals(100, children.get(0).getFileId());
		assertEquals(root, children.get(0).getParent());
		assertEquals("gddd", children.get(0).getFilePath());
		assertEquals("Child File", children.get(0).getDisplayName());
	}

	@Test
	public void testFindByPath() {
		assertEquals(100, fileItemDao.findByPath("gddd").getFileId());
	}

}
