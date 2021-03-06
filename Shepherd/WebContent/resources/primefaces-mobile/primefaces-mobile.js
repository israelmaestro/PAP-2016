PrimeFaces.ajax.AjaxUtils.updateElement = function(d, c){
	if(d == PrimeFaces.VIEW_STATE){
		PrimeFaces.ajax.AjaxUtils.updateState.call(this, c)
	}
	else{
		if(d == PrimeFaces.VIEW_ROOT){
			document.open();
			document.write(c);
			document.close()
		}
		else{
			$(PrimeFaces.escapeClientId(d)).replaceWith(c);
			if($.mobile){
				var b = $(PrimeFaces.escapeClientId(d)).parent(), a = b
						.find(":input, button, a[data-role='button'], ul");
				a
						.filter("[type='text'],[type='tel'],[type='range'],[type='number'],[type='email'],[type='password'],textarea")
						.textinput();
				a.filter("[type='radio'], [type='checkbox']").checkboxradio();
				a.filter("select:not([data-role='slider'])").selectmenu();
				a.filter(":jqmData(type='range')").slider();
				a.filter("select[data-role='slider']").slider();
				a.filter("ul[data-role='listview']").listview();
				a
						.filter("button, [type='button'], [type='submit'], [type='reset'], [type='image']")
						.button();
				a.filter("a").buttonMarkup();
				b.find(":jqmData(role='fieldcontain')").fieldcontain();
				b.find(":jqmData(role='controlgroup')").controlgroup();
				b.find("div[data-role='collapsible']").collapsible();
				b.find("div[data-role='collapsibleset']").collapsibleset();
				b.find("div[data-role='navbar']").navbar()
			}
		}
	}
};
PrimeFaces.navigate = function(b, a){
	a.changeHash = false;
	a.reverse = a.reverse == "true" || a.reverse == true ? true : false;
	$.mobile.changePage(b, a)
};
PrimeFaces.widget.InputText = PrimeFaces.widget.BaseWidget.extend({
	init : function(a){
		this._super(a);
		this.input = this.jq.is(":input") ? this.jq : this.jq
				.children(":input");
		if(this.cfg.behaviors){
			PrimeFaces.attachBehaviors(this.input, this.cfg.behaviors)
		}
	}
});
PrimeFaces.widget.InputTextarea = PrimeFaces.widget.BaseWidget.extend({
	init : function(a){
		this._super(a);
		this.input = this.jq.is(":input") ? this.jq : this.jq
				.children(":input");
		this.cfg.rowsDefault = this.input.attr("rows");
		this.cfg.colsDefault = this.input.attr("cols");
		if(this.cfg.autoResize){
			this.setupAutoResize()
		}
		if(this.cfg.maxlength){
			this.applyMaxlength()
		}
		if(this.cfg.behaviors){
			PrimeFaces.attachBehaviors(this.input, this.cfg.behaviors)
		}
	},
	setupAutoResize : function(){
		var a = this;
		this.input.keyup(function(){
			a.resize()
		}).focus(function(){
			a.resize()
		}).blur(function(){
			a.resize()
		})
	},
	resize : function(){
		var d = 0, a = this.input.val().split("\n");
		for(var b = a.length - 1; b >= 0; --b){
			d += Math.floor(a[b].length / this.cfg.colsDefault + 1)
		}
		var c = d >= this.cfg.rowsDefault ? d + 1 : this.cfg.rowsDefault;
		this.input.attr("rows", c)
	},
	applyMaxlength : function(){
		var a = this;
		this.input.keyup(function(d){
			var c = a.input.val(), b = c.length;
			if(b > a.cfg.maxlength){
				a.input.val(c.substr(0, a.cfg.maxlength))
			}
		})
	}
});
PrimeFaces.widget.SelectBooleanCheckbox = PrimeFaces.widget.BaseWidget.extend({
	init : function(a){
		this._super(a);
		this.input = $(this.jqId + "_input");
		if(this.cfg.behaviors){
			PrimeFaces.attachBehaviors(this.input, this.cfg.behaviors)
		}
	}
});
PrimeFaces.widget.SelectManyCheckbox = PrimeFaces.widget.BaseWidget.extend({
	init : function(a){
		this._super(a);
		this.inputs = this.jq.find(":checkbox:not(:disabled)");
		if(this.cfg.behaviors){
			PrimeFaces.attachBehaviors(this.inputs, this.cfg.behaviors)
		}
	}
});
PrimeFaces.widget.SelectOneRadio = PrimeFaces.widget.BaseWidget.extend({
	init : function(a){
		this._super(a);
		this.inputs = this.jq.find(":radio:not(:disabled)");
		if(this.cfg.behaviors){
			PrimeFaces.attachBehaviors(this.inputs, this.cfg.behaviors)
		}
	}
});
