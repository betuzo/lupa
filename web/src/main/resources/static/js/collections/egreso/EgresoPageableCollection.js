define([
	'backbone',
	'backbonePaginator',
    'models/egreso/EgresoModel'
], function(Backbone, backbonePaginator, EgresoModel){

    var EgresoPageableCollection = Backbone.PageableCollection.extend({
        model: EgresoModel,
        url: "egreso",
        state: {
            pageSize: 10
        },
        mode: "client" // page entirely on the client side
    });

	return EgresoPageableCollection;
});