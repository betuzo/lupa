define([
    'backbone'
], function(Backbone){

    var PersonaModel = Backbone.Model.extend({

        urlRoot: 'persona',

        defaults: {
            nombre: '',
            paterno: '',
            materno: '',
            sexo: 'FEMENINO',
            fechaRegistro: (new Date()).getTime(),
            fechaRegistroDes: '',
            fotoPersona: 'novalid'
        },

        initialize: function() {
        },

        validation: {
            nombre: {
                required: true
            },
            paterno: {
                required: true
            },
            materno: {
                required: false
            },
            sexo: {
                required: true
            },
            fechaRegistro: {
                required: true
            },
            fotoPersona: {
                required: true
            }
        }

    });

	return PersonaModel;
});
