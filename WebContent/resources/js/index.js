var inicio = new Vue({
	el:"#inicio",
    data: {
        listaProdutos: [],
        listaProdutosHeader: [
			{sortable: false, key: "nome", label:"Nome"},
			{sortable: false, key: "fabricante.nome", label:"Fabricante"},
			{sortable: false, key: "volume", label:"Volume"},
			{sortable: false, key: "unidade", label:"Unidade"},
			{sortable: false, key: "estoque", label:"Estoque"}
		]
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
			}).finally(function() {
			});
		},
		excluirItem:function (idProduto) {
        	alert(idProduto);
			axios.delete("/mercado/rs/produtos/"+idProduto)
				.then(response => {
					window.location.reload()
				}).catch( function (error) {
					alert("Produto não excluido");
				}).finally(function () {});
        }

    }
});