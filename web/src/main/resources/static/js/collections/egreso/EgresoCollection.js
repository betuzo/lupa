define([
	'backbone',
    'models/egreso/EgresoModel'
], function(Backbone, EgresoModel){

    var EgresoCollection = Backbone.Collection.extend({
        model: EgresoModel,
        url: 'egreso'
    });

	return EgresoCollection;
});