ajax4stripes
============

*ajax4stripes is a work-in-progress*

ajax4stripes aims to provide simpler, easier-to-use AJAX functionality for
the [Stripes Framework](1) than what is available [out-of-the-box](2) (See 
also [this](3)).

	[1]: http://www.stripesframework.org
	[2]: http://www.stripesframework.org/display/stripes/AJAX
	[3]: http://www.stripesframework.org/display/stripes/AJAX+even+easier

ajax4stripes is inspired by ajax4jsf (now known as [RichFaces](richfaces)).
The goal with ajax4stripes is to make using AJAX with Stripes as easy as using
some tags and simple JS functions.

	<a4s:ajaxArea id="counter" beanclass="${actionBean.class.name}"
		event="updateCounter" name="counter.jsp" />

In a JavaScript call, simply:

	ajax4stripes.refresh('counter');
