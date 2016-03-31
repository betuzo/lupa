define([
    'backbone'
], function(Backbone){

    var EgresoModel = Backbone.Model.extend({

        urlRoot: 'egreso',

        defaults: {
            recaudadorId: '',
            recaudadorNombre: '',
            eventoId: '',
            eventoNombre: '',
            monto: '',
            comentario: '',
            factura: 'novalid',
            fechaRegistro: (new Date()).getTime(),
            fechaRegistroDes: '',
            enabled: 'false'
        },

        initialize: function() {
        },

        validation: {
            recaudadorId: {
                required: false,
                msg: 'Por favor especifique el recaudador'
            },
            eventoId: {
                required: false,
                msg: 'Por favor especifique un evento'
            },
            monto: {
                required: true,
                msg: 'Por favor especifique un monto correcto'
            },
            factura: {
                required: false
            }
        }

    });

	return EgresoModel;
});
