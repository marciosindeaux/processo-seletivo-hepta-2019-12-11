Vue.component('form-input', {
    props:['cadastro','produto','nomeFabricante','unidadeText','volumeText','qtdEstoque'],
    methods:{
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
            })
            .catch(function (error) {
            });
        },
        atualizarPagina(){
            window.location.reload()
        },
        onPutSubmit(){
            
        },
        resolve(prop){
            if(prop){
                this.onPostSubmit();
            }else{
                this.onPutSubmit();
            }
            $('#cadastrar').modal('toggle')
        },

    },

    template:`
    <form class="needs-validation" @submit.prevent="resolve(cadastro)"">
        <div class="form-group">
            <label for="nomeProduto">Digite o produto</label>
            <div class="input-group">
                <input type="text" class="form-control" id="nomeProduto"  v-model="nomeProduto" required>
                <div class="invalid-tooltip">
                  Por favor, Digite o nome do produto.
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="fabricante">Nome da Fabricante</label>
            <div class="input-group">
                <input type="text" class="form-control" id="fabricante" placeholder="Nome da Fabricante" v-model="fabricante" required>
                <div class="invalid-tooltip">
                  Por favor, Digite o nome do fabricante
                </div>
            </div>  
        </div>
        <div class="form-group">
            <label for="unidade">Unidade</label>
            <div class="input-group">
                <input type="text" class="form-control" id="unidade" v-model="unidade" required >
                <div class="invalid-tooltip">
                  Por favor, Digite a unidade
                </div>
            </div> 
        </div>
        <div class="form-group">
            <label for="volume">Volume</label>
            <div class="input-group">
                <input type="text" class="form-control" id="volume" v-model="volume" required >
                <div class="invalid-tooltip">
                  Por favor, Digite o volume
                </div>
            </div> 
        </div>
        <div class="form-group">
            <label for="estoque">Estoque</label>
            <div class="input-group">
                <input type="number" class="form-control" id="estoque" v-model.number="estoque" required >
                <div class="invalid-tooltip">
                  Por favor, Digite o estoque
                </div>
            </div> 
            
        </div>
        <button v-if="cadastro" type="submit" class="btn btn-dark" >Cadastrar</button>
        <button v-else type="submit" class="btn btn-dark" >Atualizar Dados</button>
        
        <div class="modal fade" id="cadastrar" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 v-if="cadastro" class="modal-title">Item cadastrado com sucesso</h5>
                        <h5 v-else class="modal-title"> Item Alterado com sucesso</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="atualizarPagina">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal" @click="atualizarPagina">Fechar</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
    `,
    data(){
        return{
            nomeProduto: produto | null,
            fabricante: nomeFabricante |  null,
            unidade: unidadeText | null,
            volume: volumeText | null,
            estoque: qtdEstoque | null

        }

    }
});

