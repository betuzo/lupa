define([
	'backbone',
    'models/util/GrapModel'
], function(Backbone, GrapModel){

    var GrapCollection = Backbone.Collection.extend({
        model: GrapModel,
        url: function() {
            if (this.tipo == 'egreso') {
                return 'egreso/detail';
            } else {
                return 'ingreso/detail';
            }
        },
        setTipo: function(tipo){
            this.tipo = tipo;
        }
    });

	return GrapCollection;
});