define([
	'backbone',
	'backbonePaginator',
    'models/ingreso/IngresoModel'
], function(Backbone, backbonePaginator, IngresoModel){

    var IngresoPageableCollection = Backbone.PageableCollection.extend({
        model: IngresoModel,
        url: "ingreso",
        state: {
            pageSize: 10
        },
        mode: "client" // page entirely on the client side
    });

	return IngresoPageableCollection;
});