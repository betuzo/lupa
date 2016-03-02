define([
	'backbone',
    'models/PersonaModel'
], function(Backbone, PersonaModel){

    var PersonaCollection = Backbone.Collection.extend({
        model: PersonaModel,
        url: function() {
            if (this.tipo == 'role') {
                return 'persona/role/' + this.role;
            } else {
                return 'persona';
            }
        },
        setTipo: function(tipo){
            this.tipo = tipo;
        },
        setRole: function(role){
            this.role = role;
        }
    });

	return PersonaCollection;
});