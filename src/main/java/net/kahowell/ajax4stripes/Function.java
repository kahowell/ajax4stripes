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
package net.kahowell.ajax4stripes;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import javax.servlet.jsp.JspException;

import net.kahowell.ajax4stripes.support.Initializer;
import net.sourceforge.stripes.tag.LinkTagSupport;

/** 
 * Class for the jsFunction tag, which generates a JavaScript function which
 * can be used to call an ActionBean's Resolution to refresh an on-page
 * element. 
 * 
 * @author  Kevin Howell
 */
public class Function extends LinkTagSupport {
	
	private static final Properties PROPERTIES = new Properties();
	
	private String name;
	
	private String dataSelector = "";
	
	private String elementId;
	
	static {
		try {
			PROPERTIES.load(Function.class.getResourceAsStream("/ajax4stripes/templates.properties"));
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load templates", e);
		}
	}
	
	@Override
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		String function = MessageFormat.format(
			PROPERTIES.getProperty("function"),
			name,
			dataSelector,
			elementId,
			buildUrl()
		);
		try {
			new Initializer(getPageContext()).initMissing();
			getPageContext().getOut().write(function);
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataSelector() {
		return dataSelector;
	}

	public void setDataSelector(String dataSelector) {
		this.dataSelector = dataSelector;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

}
