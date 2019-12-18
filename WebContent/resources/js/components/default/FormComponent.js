Vue.component('form-input',{
    props:['cadastro','produto'],
    data() {
        if(this.produto){
            return {
                nomeProduto: this.produto.nome,
                fabricante: this.produto.fabricante.nome,
                unidade: this.produto.unidade,
                volume: this.produto.volume,
                estoque: this.produto.estoque,
                idProduto: this.produto.id
            }
        }
        return{
            nomeProduto: null,
            fabricante: null,
            unidade:  null,
            volume: null,
            estoque: null,
            idProduto: null
        }
    },
    mounted(){

        if(this.produto){
                this.nomeProduto = this.produto.nome;
                this.fabricante = this.produto.fabricante.nome,
                this.unidade = this.produto.unidade,
                this.volume = this.produto.volume,
                this.estoque = this.produto.estoque,
                this.idProduto = this.produto.id
        }

    },
    methods:{
        defaultData(){
            this.nomeProduto = null ;
            this.fabricante = null ;
            this.unidade = null ;
            this.volume = null ;
            this.estoque = null ;
            this.idProduto = null;
        },

        onPostSubmit(){
            axios.post('http://localhost:8080/mercado/rs/produtos',{
                nome:this.nomeProduto,
                unidade: this.unidade,
                volume: this.volume,
                estoque: this.estoque,
                fabricante:{
                    nome:this.fabricante
                }
            })
                .then(function (response) {
                    $('#cadastrar').modal('toggle')
                })
                .catch(function (error) {
                });
        },

        atualizarPagina(){
            window.location.reload()
        },

        onPutSubmit(){
            axios.put('http://localhost:8080/mercado/rs/produtos/'+this.idProduto,{
                nome:this.nomeProduto,
                unidade: this.unidade,
                volume: this.volume,
                estoque: this.estoque,
                fabricante:{
                    nome:this.fabricante
                }})
                .then(function (response) {
                    $('#cadastrar').modal('toggle')
                })
                .catch(function (error) {
                });
            },

        resolve(prop){
            if(prop){
                this.onPostSubmit();
            }else{
                this.onPutSubmit();
            }
            this.defaultData();
        },

    },
    template:`
    <form class="needs-validation" @submit.prevent="resolve(cadastro)">
        
            <label for="nomeProduto">Digite o produto</label>
            <input type="text" class="form-control" v-bind:class="this.idProduto" id="nomeProduto"  v-model="nomeProduto" required>
        
        <div class="form-group">
            <label for="fabricante">Nome da Fabricante</label>
            <input type="text" class="form-control"   v-bind:class="this.idProduto" id="fabricante" placeholder="Nome da Fabricante" v-model="fabricante" required>
        </div>
        <div class="form-group">
            <label for="unidade">Unidade</label>
            <input type="text" class="form-control"  v-bind:class="this.idProduto" id="unidade" v-model="unidade" required >
        </div>
        <div class="form-group">
            <label for="volume">Volume</label>
            <input type="text" class="form-control"  v-bind:class="this.idProduto" id="volume" v-model="volume" required>
        </div>
        <div class="form-group">
            <label for="estoque">Estoque</label>
            <input type="number" class="form-control"   v-bind:class="this.idProduto" id="estoque" v-model.number="estoque" required >
        </div>
        <button v-if="cadastro" type="submit" class="btn btn-dark"  v-bind:class="this.idProduto" >Cadastrar</button>
        <button v-else type="submit" class="btn btn-dark"  v-bind:class="this.idProduto" >Atualizar Dados</button>
        
        <div class="modal fade" id="cadastrar" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 v-if="cadastro" class="modal-title">Item cadastrado com sucesso</h5>
                        <h5 v-else class="modal-title"> Item Alterado com sucesso</h5>
                        <button @click="atualizarPagina" type="button" class="close" data-dismiss="modal" aria-label="Close" >
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-footer">
                        <button  @click="atualizarPagina" type="button" class="btn btn-secondary" data-dismiss="modal" >Fechar</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
    `,

});

