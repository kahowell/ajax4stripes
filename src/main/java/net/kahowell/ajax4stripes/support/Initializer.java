package net.kahowell.ajax4stripes.support;

/*
   Copyright 2013 Kevin Howell, Matt Kilpatrick, contributors

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.jsp.PageContext;

import org.apache.commons.io.IOUtils;

/**
 * Helper class that initializes client-side JS libraries.
 * 
 * @author Kevin Howell
 */
public class Initializer {

	PageContext context;
	
	/**
	 * Binds the initializer to the given PageContext
	 * 
	 * @param context  PageContext to use in IO operations
	 */
	public Initializer(PageContext context) {
		this.context = context;
	}
	
	/** Attribute name that is used to track whether jQuery is initialized or not */
	public static final String JQUERY_INIT_FLAG = "_ajax4stripes_jquery_init";
	
	/** Attribute name that is used to track whether ajax4stripes is initialized or not */
	public static final String INIT_FLAG = "_ajax4stripes_init";
	
	/**
	 * Initializes missing client-side libraries.
	 * @throws IOException  if unable to open libraries or on IO error 
	 */
	public void initMissing() throws IOException {
		initJQueryIfNotPresent();
		initAjax4StripesIfNotPresent();
	}
	
	/**
	 * Initializes JQuery library by including it into the page.
	 * @throws IOException  if unable to open libraries or on IO error 
	 */
	public void initJQueryIfNotPresent() throws IOException {
		if (context.getAttribute(JQUERY_INIT_FLAG) == null) {
			initJQuery();
			context.setAttribute(JQUERY_INIT_FLAG, true);
		}
	}
	
	/**
	 * Initializes Ajax4Stripes client-side code.
	 * @throws IOException  if unable to open libraries or on IO error 
	 */
	public void initAjax4StripesIfNotPresent() throws IOException {
		if (context.getAttribute(INIT_FLAG) == null) {
			initAjax4Stripes();
			context.setAttribute(INIT_FLAG, true);
		}
	}
	
	private void initJQuery() throws IOException {
		InputStream jQuery = getClass().getResourceAsStream("/ajax4stripes/jquery-1.9.1.min.js");
		IOUtils.copy(jQuery, context.getOut());
	}

	private void initAjax4Stripes() throws IOException {
		InputStream initScript = getClass().getResourceAsStream("/ajax4stripes/init.js");
		IOUtils.copy(initScript, context.getOut());
	}

}
