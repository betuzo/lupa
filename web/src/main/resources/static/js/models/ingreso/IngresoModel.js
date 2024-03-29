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
            eventoId: '',
            eventoNombre: '',
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
                required: true,
                msg: 'Por favor especifique el donador'
            },
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
            visibilidad: {
                required: true,
                msg: 'Por favor especifique la visibilidad'
            },
            fichaPago: {
                required: false
            }
        }

    });

	return IngresoModel;
});
