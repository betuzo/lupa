define([
	'backgrid',
	'views/private/util/ModalGenericView',
	'views/private/ingreso/IngresoDetailView',
	'views/private/ingreso/IngresoNewView',
	'text!templates/private/ingreso/tplIngresoAction.html'
], function(backgrid, ModalGenericView, IngresoDetailView, IngresoNewView, tplIngresoAction){

    var IngresoActionCell = Backgrid.Cell.extend({
        template: _.template(tplIngresoAction),
        events: {
          "click #eliminar-ingreso"      : "deleteRow",
          "click #detalle-ingreso"       : "detailRow",
          "click #editar-ingreso"        : "editRow"
        },

        deleteRow: function (e) {
            e.preventDefault();
            var collection = this.model.collection;
            this.model.destroy({
                contentType: 'application/json',
                wait:true,
                success: function(model, response) {
                    app.eventBus.trigger("deleteIngreso", model);
                    collection.remove(model);
                    new ModalGenericView({message: 'Ingreso eliminado'});
                },
                error: function(model, error) {
                    new ModalGenericView({message: error.responseJSON.message});
                }
            });
        },

        detailRow: function (e) {
            e.preventDefault();
            new IngresoDetailView(this.model);
        },

        editRow: function (e) {
            e.preventDefault();
            new IngresoNewView({modelo: this.model, tipo: 'edit'});
        },

        render: function () {
            this.model.set({classAction: this.classAction(this.model.get('enabled'))});
            this.model.set({classDetail: this.classDetail(this.model.get('enabled'))});
            this.$el.html(this.template(this.model.toJSON()));
            this.delegateEvents();
            this.listenTo(app.eventBus, 'editIngreso'+this.model.get('id'), this.editarSaveIngreso);
            return this;
        },

        editarSaveIngreso: function(model) {
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

	return IngresoActionCell;
});