define([
    'backbone'
], function(Backbone){

    var EgresoTokenModel = Backbone.Model.extend({

        urlRoot: 'egresotoken',

        defaults: {
            token: '',
            fechaVigencia: (new Date()).getTime(),
            recaudadorId: '',
            recaudadorNombre: '',
            monto: '',
            comentario: '',
            fichaPago: 'novalid',
            fechaRegistro: (new Date()).getTime(),
            fechaRegistroDes: '',
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
            recaudadorId: {
                required: false,
                msg: 'Por favor especifique un email correcto'
            },
            monto: {
                required: true,
                msg: 'Por favor especifique un monto correcto'
            },
            fichaPago: {
                required: false
            }
        }

    });

    return EgresoTokenModel;
});
