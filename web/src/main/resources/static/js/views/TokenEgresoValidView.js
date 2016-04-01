define([
    'jquery',
    'dateformat',
    'core/BaseView',
    'models/EgresoTokenModel',
    'views/private/util/ModalGenericView',
    'text!templates/tplTokenEgresoValid.html'
], function($, dateformat, BaseView, EgresoTokenModel, ModalGenericView, tplTokenEgresoValid){

    var TokenEgresoValidView = BaseView.extend({
        template: _.template(tplTokenEgresoValid),

        events: {
            'click #btn-ok'         : 'confirmEgreso'
        },

        initialize: function(opts) {;
            this.model = new EgresoTokenModel();
            this.listenTo(this.model, 'sync', this.syncEgresoToken);
            this.listenTo(this.model, 'error', this.errorEgresoToken);

            this.model.set({id: opts.token, token: opts.token});
            this.model.fetch();
        },

        render: function() {
            return this;
        },

        confirmEgreso: function(){
            that = this;
            this.model.set({id: this.model.get('token')});
            this.model.destroy({
                contentType: 'application/json',
                wait:true,
                success: function(model, response) {
                    that.destroyView();
                    new ModalGenericView({message: 'Egreso validado correctamente'});
                },
                error: function(model, error) {
                    new ModalGenericView({message: error.responseJSON.message});
                }
            });
        },

        syncEgresoToken: function() {
            this.model.set({fechaRegistroDes: (new Date(this.model.get('fechaRegistro'))).format("mm/dd/yyyy HH:MM")});
            this.$el.html(this.template(this.model.toJSON()));
        },

        errorEgresoToken: function(model, response) {
            new ModalGenericView({
                message: response.responseJSON.message
            });
        }
    });

    return TokenEgresoValidView;

});