define([
    'backbone'
], function(Backbone){

    var EgresoTotalModel = Backbone.Model.extend({

        urlRoot: 'egresototal',

        defaults: {
            totalPendientes: 0,
            totalMontoPendiente: 0,
            totalMonto: 0
        },

        initialize: function() {
        }
    });

	return EgresoTotalModel;
});
