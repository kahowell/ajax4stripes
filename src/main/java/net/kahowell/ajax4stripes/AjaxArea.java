package net.kahowell.ajax4stripes;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

import org.apache.commons.io.IOUtils;

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

	private static final String INIT_FLAG = "_ajax4stripes_init";
	
	private String dataSelector;
	
	private String name;
	
	@Override
	public int doStartTag() throws JspException {
		writeOpenTag(getPageContext().getOut(), "div");
		return Tag.EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			getPageContext().include(getName());
		} catch (Exception e) {
			throw new JspException(e);
		}
		writeCloseTag(getPageContext().getOut(), "div");
		try {
			getPageContext().getOut().write("<script>");
		} catch (IOException e) {
			throw new JspException(e);
		}
		if (pageContext.getAttribute(INIT_FLAG) == null) {
			pageContext.setAttribute(INIT_FLAG, true);
			try {
				initAjax4Stripes();
			} catch (IOException e) {
				throw new JspException(e);
			}
		}
		try {
			addAjaxArea(getId());
		} catch (IOException e) {
			throw new JspException(e);
		}
		writeCloseTag(getPageContext().getOut(), "script");
		
		return EVAL_PAGE;
	}

	private void addAjaxArea(String id) throws IOException, StripesJspException {
		getPageContext().getOut().write(
			MessageFormat.format(
				"ajax4stripes._areas[''{0}''] = '{' url :  ''{1}'', dataSelector : ''{2}'' '}';", 
				HtmlUtil.encode(getId()),
				buildUrl(),
				HtmlUtil.encode(getDataSelector())
			)
		);
	}

	private void initAjax4Stripes() throws IOException {
		InputStream jQuery = getClass().getResourceAsStream("/ajax4stripes/jquery-1.9.1.min.js");
		InputStream initScript = getClass().getResourceAsStream("/ajax4stripes/init.js");
		IOUtils.copy(jQuery, getPageContext().getOut());
		IOUtils.copy(initScript, getPageContext().getOut());
	}

	public int doAfterBody() throws JspException {
		return SKIP_BODY;
	}

	public void doInitBody() throws JspException {}

	public String getDataSelector() {
		if (dataSelector == null) {
			return "";
		}
		return dataSelector;
	}

	public void setDataSelector(String dataSelector) {
		this.dataSelector = dataSelector;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
