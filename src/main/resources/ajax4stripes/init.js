var ajax4stripes = {
	$ : jQuery.noConflict(true);
	_areas : {},
	refresh : function(id) {
		ajaxArea = _areas[id];
		data = $(ajaxArea.dataSelector).serialize();
		$('#' + id).load(ajaxArea.url, data);
	}
};