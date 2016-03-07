define([
    'backbone'
], function(Backbone){

    var TipoVisibilidadModel = Backbone.Model.extend({

        urlRoot: 'tipovisibilidad',

        defaults: {
            clave: '',
            descripcion: ''
        },

        initialize: function() {
        },

        validation: {
            clave: {
                required: true
            },
            descripcion: {
                required: true
            }
        }

    });

	return TipoVisibilidadModel;
});
