var ajax4stripes = {
	$ : jQuery.noConflict(true),
	_areas : {},
	refresh : function(id) {
		ajaxArea = this._areas[id];
		data = this.$(ajaxArea.dataSelector).serialize();
		this.$('#' + id).load(ajaxArea.url, data);
	}
};