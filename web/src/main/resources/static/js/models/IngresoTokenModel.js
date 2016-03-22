define([
    'backbone'
], function(Backbone){

    var IngresoTokenModel = Backbone.Model.extend({

        urlRoot: 'ingresotoken',

        defaults: {
            token: '',
            fechaVigencia: (new Date()).getTime(),
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
            token: {
                required: true
            },
            fechaVigencia: {
                required: true
            },
            donadorId: {
                required: true,
                msg: 'Por favor especifique el donador'
            },
            recaudadorId: {
                required: false,
                msg: 'Por favor especifique un email correcto'
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

    return IngresoTokenModel;
});
