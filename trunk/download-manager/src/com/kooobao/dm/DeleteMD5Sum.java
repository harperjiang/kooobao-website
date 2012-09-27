package com.kooobao.dm;

import java.io.File;

public class DeleteMD5Sum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File f = new File("/home/harper");
		deleteMD5Sum(f);
	}

	protected static void deleteMD5Sum(File f) {
		if (f.isFile() && f.getName().equals("md5sum"))
			f.delete();
		if (f.isDirectory())
			for (File child : f.listFiles())
				deleteMD5Sum(child);
	}

}
