define([
    'backbone'
], function(Backbone){

    var EventoModel = Backbone.Model.extend({

        urlRoot: 'evento',

        defaults: {
            eventoNombre: '',
            eventoDescripcion: '',
            fechaEvento: (new Date()).getTime(),
            fechaEventoDes: '',
        },

        initialize: function() {
        },

        validation: {
            eventoNombre: {
                required: true
            },
            eventoDescripcion: {
                required: true
            },
            fechaEvento: {
                required: true
            }
        }

    });

	return EventoModel;
});
