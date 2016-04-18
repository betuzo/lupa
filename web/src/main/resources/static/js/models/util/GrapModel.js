define([
    'backbone'
], function(Backbone){

    var GrapModel = Backbone.Model.extend({

        urlRoot: 'grap',

        defaults: {
        },

        initialize: function() {
        }

    });

	return GrapModel;
});