function __ajax4stripes_findJQuery() {
	if (typeof(__ajax4stripes_jquery_present) == 'undefined') {
		return jQuery;
	}
	else {
		return jQuery.noConflict(true);
	}
}

var ajax4stripes = {
	$ : __ajax4stripes_findJQuery(),
	_areas : {},
	refresh : function(id, func) {
		ajaxArea = this._areas[id];
		data = this.$(ajaxArea.dataSelector).serialize();
		this.$('#' + id).load(ajaxArea.url, data, func);
	}
};