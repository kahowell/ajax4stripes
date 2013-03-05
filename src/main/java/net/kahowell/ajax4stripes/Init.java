package net.kahowell.ajax4stripes;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import net.kahowell.ajax4stripes.support.Initializer;

public class Init extends TagSupport {

	private final Initializer init = new Initializer(pageContext);
	
	private static final String JQUERY_PRESENT = "jQueryPresent";
	
	@Override
	public int doEndTag() throws JspException {
		try {
            boolean jQueryPresent = Boolean.parseBoolean(pageContext.getServletConfig()
                    .getInitParameter(JQUERY_PRESENT));
            if (jQueryPresent) {
				pageContext.setAttribute(Initializer.JQUERY_INIT_FLAG, true);
			}
			init.initMissing();
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}
}
