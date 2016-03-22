define([
	'backbone',
    'models/evento/EventoModel'
], function(Backbone, EventoModel){

    var EventoCollection = Backbone.Collection.extend({
        model: EventoModel,
        url: 'evento'
    });

	return EventoCollection;
});