define([
    'backbone'
], function(Backbone){

    var IngresoModel = Backbone.Model.extend({

        urlRoot: 'ingreso',

        defaults: {
            donadorId: '',
            donadorNombre: '',
            recaudadorId: '',
            recaudadorNombre: '',
            monto: '',
            comentario: '',
            fichaPago: 'novalid',
            fechaRegistro: (new Date()).getTime(),
            fechaRegistroDes: '',
            visibilidad: 'PUBLICA',
            enabled: 'false'
        },

        initialize: function() {
        },

        validation: {
            donadorId: {
                required: true
            },
            recaudadorId: {
                required: true
            },
            monto: {
                required: true
            },
            visibilidad: {
                required: true
            },
            fichaPago: {
                required: true
            }
        }

    });

	return IngresoModel;
});
