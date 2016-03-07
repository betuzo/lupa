define([
	'backbone',
    'models/ingreso/TipoVisibilidadModel'
], function(Backbone, TipoVisibilidadModel){

    var IngresoCollection = Backbone.Collection.extend({
        model: TipoVisibilidadModel,
        url: 'tipovisibilidad'
    });

	return IngresoCollection;
});