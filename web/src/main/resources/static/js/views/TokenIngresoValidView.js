define([
    'jquery',
    'dateformat',
    'core/BaseView',
    'models/IngresoTokenModel',
    'views/private/util/ModalGenericView',
    'text!templates/tplTokenIngresoValid.html'
], function($, dateformat, BaseView, IngresoTokenModel, ModalGenericView, tplTokenIngresoValid){

    var TokenIngresoValidView = BaseView.extend({
        template: _.template(tplTokenIngresoValid),

        events: {
            'click #btn-ok'         : 'confirmIngreso'
        },

        initialize: function(opts) {;
            this.model = new IngresoTokenModel();
            this.listenTo(this.model, 'sync', this.syncIngresoToken);
            this.listenTo(this.model, 'error', this.errorIngresoToken);

            this.model.set({id: opts.token, token: opts.token});
            this.model.fetch();
        },

        render: function() {
            return this;
        },

        confirmIngreso: function(){
            that = this;
            this.model.set({id: this.model.get('token')});
            this.model.destroy({
                contentType: 'application/json',
                wait:true,
                success: function(model, response) {
                    that.destroyView();
                    new ModalGenericView({message: 'Ingreso validado correctamente'});
                },
                error: function(model, error) {
                    new ModalGenericView({message: error.responseJSON.message});
                }
            });
        },

        syncIngresoToken: function() {
            this.model.set({fechaRegistroDes: (new Date(this.model.get('fechaRegistro'))).format("mm/dd/yyyy HH:MM")});
            this.$el.html(this.template(this.model.toJSON()));
        },

        errorIngresoToken: function(model, response) {
            new ModalGenericView({
                message: response.responseJSON.message
            });
        }
    });

    return TokenIngresoValidView;

});