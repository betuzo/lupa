define([
	'backbone',
    'models/ingreso/IngresoModel'
], function(Backbone, IngresoModel){

    var IngresoCollection = Backbone.Collection.extend({
        model: IngresoModel,
        url: 'ingreso'
    });

	return IngresoCollection;
});