package net.kahowell.ajax4stripes;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import net.kahowell.ajax4stripes.support.Initializer;

public class Init extends TagSupport {

	private final Initializer init = new Initializer(pageContext);
	
	private boolean jQueryPresent;
	
	@Override
	public int doEndTag() throws JspException {
		try {
			if (jQueryPresent) {
				pageContext.setAttribute(Initializer.JQUERY_INIT_FLAG, true);
				pageContext.getOut().write("var __ajax4stripes_jquery_present = true;");
			}
			init.initMissing();
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}

	public boolean isjQueryPresent() {
		return jQueryPresent;
	}

	public void setjQueryPresent(boolean jQueryPresent) {
		this.jQueryPresent = jQueryPresent;
	}
}
