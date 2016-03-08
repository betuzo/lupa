define([
    'backbone'
], function(Backbone){

    var IngresoTotalModel = Backbone.Model.extend({

        urlRoot: 'ingresototal',

        defaults: {
            totalPendientes: 0,
            totalMontoPendiente: 0,
            totalMonto: 0
        },

        initialize: function() {
        }
    });

	return IngresoTotalModel;
});
