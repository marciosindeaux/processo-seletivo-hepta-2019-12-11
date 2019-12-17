var inicio = new Vue({
	el:"#inicio",
    data: {
        listaProdutos: [],
		idItemPut: null
    },
    created: function(){
        let vm =  this;
        vm.buscaProdutos();
    },
    methods:{
        buscaProdutos: function(){
			const vm = this;
			axios.get("http://localhost:8080/mercado/rs/produtos")
			.then(response => {vm.listaProdutos = response.data;
			}).catch(function (error) {
				vm.mostraAlertaErro("Erro interno", "Não foi listar natureza de serviços");
			}).finally(function() {});
		},
		setIdItem(prop){
        	this.idItemPut = prop;
		}
    }
});