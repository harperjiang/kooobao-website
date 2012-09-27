package com.kooobao.dm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "/application-context.xml" })
public class DefaultDownloadServiceTest extends
		AbstractJUnit4SpringContextTests {

	@Resource
	private DefaultDownloadService downloadService;

	@Resource
	private MemoryFileItemDao memoryFileItemDao;

	@Test
	public void testReindex() {
		File file = new File("/home/harper/test");
		File md5file = new File(file.getAbsolutePath() + File.separator
				+ "md5sum");
		md5file.delete();

		downloadService.reindex(file);

		assertTrue(md5file.exists());

		assertNotNull(memoryFileItemDao.findByPath("/dsdsasa"));
	}

	@Test
	public void testIndexFile() {
		File file = new File("/home/harper/test");
		File md5file = new File(file.getAbsolutePath() + File.separator
				+ "md5sum");
		File childmd5file = new File(file.getAbsolutePath() + "/child/md5sum");
		childmd5file.delete();

		downloadService.indexFile(file);

		assertTrue(md5file.exists());

		assertTrue(childmd5file.exists());
	}

	@Test
	public void testGetFileContent() {
		fail("Not yet implemented");
	}

	@Test
	public void testIndexFiles() {
		downloadService.indexFiles();
	}

}
