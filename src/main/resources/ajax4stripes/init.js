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