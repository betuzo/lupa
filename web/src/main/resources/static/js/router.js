define([
	'jquery',
	'underscore',
	'backbone',
	'core/BaseRouter',
	'views/LoginView',
	'views/SignupView',
    'views/TokenValidateView',
    'views/TokenChangePassView',
    'views/TokenIngresoValidView',
    'views/TokenEgresoValidView',
	'views/private/perfil/PerfilAdminView',
	'views/private/ingreso/IngresoAdminView',
	'views/private/egreso/EgresoAdminView',
	'views/private/evento/EventoAdminView',
	'views/private/resumen/ResumenAdminView',
	'views/private/MainAdminNavView',
	'views/public/MainView',
    'views/public/MainNavView',
	'Session'
], function($, _, Backbone, BaseRouter, LoginView, SignupView,
            TokenValidateView, TokenChangePassView, TokenIngresoValidView,
            TokenEgresoValidView, PerfilAdminView, IngresoAdminView,
            EgresoAdminView, EventoAdminView, ResumenAdminView, MainAdminNavView,
            MainView, MainNavView, Session){
        var Router = BaseRouter.extend({

        routes: {
            '':                             'main',
            '/':                            'main',
            '/#':                           'main',
            'login':                        'login',
            'signup':                       'signup',
            'token/:token':                 'token',
            'change/:token':                'changeToken',
            'token/ingreso/:token':         'tokenIngreso',
            'token/egreso/:token':          'tokenEgreso',
            'admin':                        'admin',
            'admin/perfil':                 'adminPerfil',
            'admin/donacion':               'adminDonacion',
            'admin/gasto':                  'adminGasto',
            'admin/evento':                 'adminEvento',
            'admin/resumen':                'adminResumen'
        },

        requresAuth : ['#admin'],

        preventAccessWhenAuth : ['#login'],

        before : function(params, next) {
            //Checking if user is authenticated or not
            //then check the path if the path requires authentication
            var isAuth = Session.get('authenticated');
            var path = Backbone.history.location.hash;
            var needAuth = path.indexOf(this.requresAuth) > -1;
            var cancleAccess = _.contains(this.preventAccessWhenAuth, path);

            if(needAuth && !isAuth){
              //If user gets redirect to login because wanted to access
              // to a route that requires login, save the path in session
              // to redirect the user back to path after successful login
              Backbone.history.navigate('login', { trigger : true });
            }else if(isAuth && cancleAccess){
              //User is authenticated and tries to go to login, register ...
              // so redirect the user to home page
              Backbone.history.navigate('', { trigger : true });
            }else{
              //No problem, handle the route!!
              return next();
            }
        },

        after : function() {
            console.log('after');
        },

        changeView : function(view) {
            function setView(view){
                if(this.currentView){
                    this.currentView.close();
                }
                this.currentView = view;
                $('#container-body').html(view.render().$el);
            }

            setView(view);
        },

        initialize: function () {
        },

        main: function() {
            var view = new MainView();
            this.changeView(view);
            new MainNavView();
        },

        login: function() {
            var view = new LoginView();
            this.changeView(view);
        },

        signup: function() {
            var view = new SignupView();
            this.changeView(view);
        },

        token: function(token) {
            new MainNavView();
            var view = new TokenValidateView({token: token});
            this.changeView(view);
        },

        changeToken: function(token) {
            new MainNavView();
            var view = new TokenChangePassView({token: token});
            this.changeView(view);
        },

        tokenIngreso : function(token) {
            new MainNavView();
            var view = new TokenIngresoValidView({token: token});
            this.changeView(view);
        },

        tokenEgreso : function(token) {
            new MainNavView();
            var view = new TokenEgresoValidView({token: token});
            this.changeView(view);
        },

        admin: function() {
            var view = new PerfilAdminView();
            this.changeView(view);
            new MainAdminNavView();
        },

        adminPerfil: function() {
            var view = new PerfilAdminView();
            this.changeView(view);
        },

        adminDonacion: function() {
            var view = new IngresoAdminView();
            this.changeView(view);
        },

        adminGasto: function() {
            var view = new EgresoAdminView();
            this.changeView(view);
        },

        adminEvento: function() {
            var view = new EventoAdminView();
            this.changeView(view);
        },

        adminResumen: function(){
            var view = new ResumenAdminView();
            this.changeView(view);
        }
	});

	return Router;

});
