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

import net.kahowell.ajax4stripes.support.Initializer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class Init extends TagSupport {

	private final Initializer init = new Initializer(pageContext);
	
	private static final String JQUERY_PRESENT = "jQueryPresent";
	
	@Override
	public int doEndTag() throws JspException {
		try {
            boolean jQueryPresent = Boolean.parseBoolean(pageContext.getServletContext()
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
