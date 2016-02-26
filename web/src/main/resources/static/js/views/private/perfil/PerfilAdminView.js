define([
    'jquery',
    'backbone',
    'core/BaseView',
    'models/UserModel',
    'views/private/util/ModalGenericView',
    'text!templates/private/perfil/tplPerfilAdmin.html',
    'Session'
], function($, Backbone, BaseView, UserModel, ModalGenericView, tplPerfilAdmin, Session){

    var LigaAdminView = BaseView.extend({
        template: _.template(tplPerfilAdmin),

        events: {

        },

        initialize: function() {
            this.model = new UserModel();
            this.listenTo(this.model, 'sync', this.saveUserSuccess);
            this.listenTo(this.model, 'error', this.saveUserError);
            this.model.set({id: Session.get('username')});
            this.model.fetch();
        },

        render: function() {
            return this;
        },

        saveUserSuccess: function(model, response, options){
            this.$el.html(this.template(this.model.toJSON()));
        },

        saveUserError: function(model, response, options){
            new ModalGenericView({
                message: response.responseJSON.message
            });
        }
    });

    return LigaAdminView;

});