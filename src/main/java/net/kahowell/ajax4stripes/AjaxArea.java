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
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

import net.kahowell.ajax4stripes.support.Initializer;
import net.sourceforge.stripes.exception.StripesJspException;
import net.sourceforge.stripes.tag.LinkTagSupport;
import net.sourceforge.stripes.util.HtmlUtil;

/**
 * Tag that renders an update-able area and registers the area with
 * client-side JavaScript code. Also loads the client-side JS
 * necessary to support the simplified AJAX methods.
 * 
 * @author  Kevin Howell
 */
public class AjaxArea extends LinkTagSupport implements BodyTag {
	
	private static final Properties PROPERTIES = new Properties();
	
	private String dataSelector = "";
	
	static {
		try {
			PROPERTIES.load(Function.class.getResourceAsStream("/ajax4stripes/templates.properties"));
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load templates", e);
		}
	}
	
	@Override
	public int doStartTag() throws JspException {
		writeOpenTag(getPageContext().getOut(), "div");
		return Tag.EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			writeCloseTag(getPageContext().getOut(), "div");
			getPageContext().getOut().write("<script>");
			new Initializer(getPageContext()).initMissing();
			addAjaxArea(getId());
			writeCloseTag(getPageContext().getOut(), "script");
		} catch (Exception e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}

	private void addAjaxArea(String id) throws IOException, StripesJspException {
		getPageContext().getOut().write(
			MessageFormat.format(
				PROPERTIES.getProperty("ajaxAreaRegistration"), 
				HtmlUtil.encode(getId()),
				buildUrl(),
				HtmlUtil.encode(getDataSelector())
			)
		);
	}

	public int doAfterBody() throws JspException {
		return SKIP_BODY;
	}

	public void doInitBody() throws JspException {}

	public String getDataSelector() {
		return dataSelector;
	}

	public void setDataSelector(String dataSelector) {
		this.dataSelector = dataSelector;
	}
	
}
