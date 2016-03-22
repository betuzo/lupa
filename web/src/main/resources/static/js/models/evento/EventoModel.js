define([
    'backbone'
], function(Backbone){

    var EventoModel = Backbone.Model.extend({

        urlRoot: 'evento',

        defaults: {
            nombre: '',
            descripcion: '',
            fechaEvento: (new Date()).getTime(),
            fechaEventoDes: '',
        },

        initialize: function() {
        },

        validation: {
            nombre: {
                required: true
            },
            descripcion: {
                required: true
            },
            fechaEvento: {
                required: true
            }
        }

    });

	return EventoModel;
});
