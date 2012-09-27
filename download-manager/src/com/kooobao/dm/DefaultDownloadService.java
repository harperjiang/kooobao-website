package com.kooobao.dm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import com.kooobao.common.util.ConfigLoader;

public class DefaultDownloadService implements DownloadService {

	@Override
	public InputStream getFileContent(long fileId, long position) {
		FileItem item = getFileItemDao().find(fileId);
		if (null == item)
			throw new IllegalArgumentException("File doesn't exist");

		String rootPath = ConfigLoader.getInstance()
				.load("config", "ROOT_PATH");
		if (null == rootPath)
			throw new IllegalArgumentException("ROOT_PATH undefined");

		String filePath = rootPath + item.getFilePath();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			fis.skip(position);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return fis;
	}

	@Override
	public void indexFiles() {
		try {
			String rootPath = ConfigLoader.getInstance().load("config",
					"ROOT_PATH");
			if (null == rootPath)
				throw new IllegalArgumentException("ROOT_PATH undefined");
			File rootFile = new File(rootPath);
			indexFile(rootFile);
		} catch (Exception e) {
			LoggerFactory.getLogger(getClass()).error(
					"Error occurred while indexing files", e);
		}
	}

	public void indexFile(FileItem file) {
		try {
			String rootPath = ConfigLoader.getInstance().load("config",
					"ROOT_PATH");
			if (null == rootPath)
				throw new IllegalArgumentException("ROOT_PATH undefined");
			File rootFile = new File(rootPath + file.getFilePath());
			indexFile(rootFile);
		} catch (Exception e) {
			LoggerFactory.getLogger(getClass()).error(
					"Error occurred while indexing files", e);
		}
	}

	protected void indexFile(File rootFile) {
		if (rootFile.isDirectory()) {
			// Check MD5 Hash for the files under this directory
			Object[] subList = ArrayUtils.removeElement(rootFile.list(),
					"md5sum");
			Arrays.sort(subList);
			String md5 = DigestUtils.md5Hex(StringUtils.join(subList, ';'));
			File md5file = new File(rootFile.getAbsolutePath() + File.separator
					+ "md5sum");
			if (!md5file.exists()) {
				reindex(rootFile);
			} else {
				// Check the md5sum file content
				BufferedReader br = null;
				try {
					br = new BufferedReader(new InputStreamReader(
							new FileInputStream(md5file)));
					String line = br.readLine();
					if (null == line || !line.equals(md5))
						reindex(rootFile);
				} catch (IOException e) {
					reindex(rootFile);
				} finally {
					if (null != br)
						try {
							br.close();
						} catch (IOException e) {
							throw new RuntimeException(
									"Exception while trying to close stream", e);
						}
				}
			}
			for (File dir : rootFile.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return pathname.isDirectory();
				}
			})) {
				indexFile(dir);
			}
		}
	}

	protected void reindex(File rootDir) {
		String rootPath = ConfigLoader.getInstance()
				.load("config", "ROOT_PATH");
		String rootDirPath = rootDir.getAbsolutePath().replaceFirst(rootPath,
				"");
		FileItem rootItem = null;
		try {
			rootItem = getFileItemDao().findByPath(rootDirPath);
		} catch (NoResultException e) {

		}
		for (File file : rootDir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return !pathname.getName().equals("md5sum");
			}
		})) {
			String filePath = file.getAbsolutePath().replaceFirst(rootPath, "");
			try {
				getFileItemDao().findByPath(filePath);
			} catch (NoResultException e) {
				FileItem item = new FileItem();
				item.setParent(rootItem);
				item.setFile(file.isFile());
				item.setFilePath(filePath);
				item.setDisplayName(file.getName());
				getFileItemDao().store(item);
			}
		}
		// Generate MD5 file for the path
		try {
			Object[] subList = ArrayUtils.removeElement(rootDir.list(),
					"md5sum");
			Arrays.sort(subList);
			String md5 = DigestUtils.md5Hex(StringUtils.join(subList, ';'));
			PrintWriter pw = new PrintWriter(new FileOutputStream(
					rootDir.getAbsolutePath() + File.separator + "md5sum"));
			pw.println(md5);
			pw.close();
		} catch (IOException e) {
			// It's okay to fail when generating MD5 files
		}
	}

	@Override
	public FileItem getFile(long fileId) {
		return getFileItemDao().find(fileId);
	}

	@Override
	public List<FileItem> listFiles(FileItem root) {
		return getFileItemDao().getFiles(root);
	}

	private FileItemDao fileItemDao;

	public FileItemDao getFileItemDao() {
		return fileItemDao;
	}

	public void setFileItemDao(FileItemDao fileItemDao) {
		this.fileItemDao = fileItemDao;
	}

}
