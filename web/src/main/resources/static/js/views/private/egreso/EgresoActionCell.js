define([
	'backgrid',
	'views/private/util/ModalGenericView',
	'views/private/egreso/EgresoDetailView',
	'views/private/egreso/EgresoNewView',
	'text!templates/private/egreso/tplEgresoAction.html'
], function(backgrid, ModalGenericView, EgresoDetailView, EgresoNewView, tplEgresoAction){

    var EgresoActionCell = Backgrid.Cell.extend({
        template: _.template(tplEgresoAction),
        events: {
          "click #eliminar-egreso"      : "deleteRow",
          "click #detalle-egreso"       : "detailRow",
          "click #editar-egreso"        : "editRow"
        },

        deleteRow: function (e) {
            e.preventDefault();
            var collection = this.model.collection;
            this.model.destroy({
                contentType: 'application/json',
                wait:true,
                success: function(model, response) {
                    app.eventBus.trigger("deleteEgreso", model);
                    collection.remove(model);
                    new ModalGenericView({message: 'Egreso eliminado'});
                },
                error: function(model, error) {
                    new ModalGenericView({message: error.responseJSON.message});
                }
            });
        },

        detailRow: function (e) {
            e.preventDefault();
            new EgresoDetailView(this.model);
        },

        editRow: function (e) {
            e.preventDefault();
            new EgresoNewView({modelo: this.model, tipo: 'edit'});
        },

        render: function () {
            this.model.set({classAction: this.classAction(this.model.get('enabled'))});
            this.model.set({classDetail: this.classDetail(this.model.get('enabled'))});
            this.$el.html(this.template(this.model.toJSON()));
            this.delegateEvents();
            this.listenTo(app.eventBus, 'editEgreso'+this.model.get('id'), this.editarSaveEgreso);
            return this;
        },

        editarSaveEgreso: function(model) {
            this.model = model;
        },

        classAction: function(enabled) {
            if (enabled==='REGISTRADA')
                return '';
            else
                return 'element-hidden';
        },

        classDetail: function(enabled) {
            if (enabled==='VALIDA')
                return '';
            else
                return 'element-hidden';
        },
    });

	return EgresoActionCell;
});