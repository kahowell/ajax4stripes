ajax4stripes
============

**ajax4stripes is a work-in-progress**

ajax4stripes aims to provide simpler, easier-to-use AJAX functionality for
the [Stripes Framework][1] than what is available [out-of-the-box][2] (See 
also [this][3]).

[1]: http://www.stripesframework.org
[2]: http://www.stripesframework.org/display/stripes/AJAX
[3]: http://www.stripesframework.org/display/stripes/AJAX+even+easier

ajax4stripes is inspired by Ajax4jsf (now a part of [RichFaces][richfaces]).
The goal with ajax4stripes is to make using AJAX with Stripes as easy as using
some tags and simple JS functions.

[richfaces]: http://www.jboss.org/richfaces

Tags
====

init
----
### Description
Inserts initialization code, loading jQuery (if necessary) and the client-side
code.
### Example
	<a4s:init />

ajaxArea
--------
### Description
Defines an area which can be refreshed using `ajax4stripes.refresh('<id>')`.

### Example
#### JSP
	<a4s:ajaxArea id="counter" beanclass="${actionBean.class.name}"
		event="updateCounter" />
#### JavaScript
	ajax4stripes.refresh('counter');

jsFunction
----------
### Description
Generates a JavaScript function which can be used to refresh a page element
using a specified `ActionBean` `Resolution`. The Resolution is specified the
same way as a `stripes:url` or `stripes:link` tag. **Should be used inside a
script tag.**

### Example
#### JSP
	<a4s:jsFunction name="plusOne" beanclass="${actionBean.class.name}"
		event="plusOne" elementId="counter" />
#### JavaScript
	plusOne();
