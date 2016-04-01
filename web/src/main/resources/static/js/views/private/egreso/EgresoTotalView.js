define([
    'jquery',
    'underscore',
    'core/BaseView',
    'text!templates/private/egreso/tplEgresoTotal.html'
], function($, _, BaseView, tplEgresoTotal){

    var EgresoTotalView = BaseView.extend({
        el: '#egreso-totales',
        template: _.template(tplEgresoTotal),

        events: {
        },

        initialize: function(modelo) {
            this.model =  modelo;
            this.render();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }
    });

    return EgresoTotalView;

});