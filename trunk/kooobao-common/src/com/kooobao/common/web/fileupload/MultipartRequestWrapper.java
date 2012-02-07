/*
 * Copyright 2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kooobao.common.web.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;

import com.kooobao.common.util.ConfigLoader;

public class MultipartRequestWrapper extends HttpServletRequestWrapper {

	HttpServletRequest request = null;
	HashMap<String, Object> parametersMap = null;
	ServletFileUpload fileUpload = null;
	HashMap<String, FileBean> files = null;
	int maxSize;
	int thresholdSize;
	String repositoryPath;

	public MultipartRequestWrapper(HttpServletRequest request, int maxSize,
			int thresholdSize, String repositoryPath) {
		super(request);
		this.request = request;
		this.maxSize = maxSize;
		this.thresholdSize = thresholdSize;
		this.repositoryPath = repositoryPath;
	}

	private void parseRequest() {
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		fileItemFactory.setSizeThreshold(thresholdSize);
		if (repositoryPath != null && repositoryPath.trim().length() > 0)
			fileItemFactory.setRepository(new File(repositoryPath));

		fileUpload = new ServletFileUpload(fileItemFactory);
		fileUpload.setFileItemFactory(fileItemFactory);
		fileUpload.setSizeMax(maxSize);

		String charset = request.getCharacterEncoding();
		fileUpload.setHeaderEncoding(charset);
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload();

		parametersMap = new HashMap<String, Object>();
		files = new HashMap<String, FileBean>();

		String path = ConfigLoader.getInstance().load("fileupload", "folder");

		try {
			// Parse the request
			FileItemIterator fiter = upload.getItemIterator(request);
			while (fiter.hasNext()) {
				FileItemStream item = fiter.next();
				if (item.isFormField()) {
					addTextParameter(item.getFieldName(),
							Streams.asString(item.openStream()));
				} else {
					FileBean fb = new FileBean();
					String fileName = path + File.separator
							+ UUID.randomUUID().toString();
					FileOutputStream fos = new FileOutputStream(fileName);
					int size = IOUtils.copy(item.openStream(), fos);
					fos.close();
					fb.setOriginName(item.getName());
					fb.setPath(fileName);
					fb.setSize(size);
					fb.setContentType(item.getContentType());

					files.put(item.getFieldName(), fb);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// Add the query string paramters
		for (Iterator it = request.getParameterMap().entrySet().iterator(); it
				.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			String[] valuesArray = (String[]) entry.getValue();
			for (int i = 0; i < valuesArray.length; i++) {
				addTextParameter((String) entry.getKey(), valuesArray[i]);
			}
		}
	}

	private void addTextParameter(String name, String value) {
		if (!parametersMap.containsKey(name)) {
			String[] valuesArray = { value };
			parametersMap.put(name, valuesArray);
		} else {
			String[] storedValues = (String[]) parametersMap.get(name);
			int lengthSrc = storedValues.length;
			String[] valuesArray = new String[lengthSrc + 1];
			System.arraycopy(storedValues, 0, valuesArray, 0, lengthSrc);
			valuesArray[lengthSrc] = value;
			parametersMap.put(name, valuesArray);
		}
	}

	public Enumeration getParameterNames() {
		if (parametersMap == null)
			parseRequest();

		return Collections.enumeration(parametersMap.keySet());
	}

	public String getParameter(String name) {
		if (parametersMap == null)
			parseRequest();

		String[] values = (String[]) parametersMap.get(name);
		if (values == null)
			return null;
		return values[0];
	}

	public String[] getParameterValues(String name) {
		if (parametersMap == null)
			parseRequest();

		return (String[]) parametersMap.get(name);
	}

	@Override
	public Map getParameterMap() {
		if (parametersMap == null)
			parseRequest();
		return parametersMap;
	}

	// Hook for the x:inputFileUpload tag.
	public FileBean getFile(String fieldName) {
		if (files == null)
			parseRequest();
		return files.get(fieldName);
	}

	/**
	 * Not used internally by MyFaces, but provides a way to handle the uploaded
	 * files out of MyFaces.
	 */
	public Map<String, FileBean> getFiles() {
		return files;
	}
}
