<template>
    <div class="container">
        <div v-if="this.executouAcao" class="alert alert-warning alert-dismissible fade show" role="alert">
            <strong>Ação realizada com sucesso</strong> 
            <button @click="reverse" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <text-field v-if="!this.id" text="Cadastro de Novos Produtos"/>
        <text-field v-else text="Edição de Produtos"/>
        <form class="needs-validation" v-on:submit.prevent="resolve()">
            <div>
                <label :for="produto.nome">Digite o nome do produto</label>
                <input placeholder="Produto" type="text" class="form-control" :id="produto.nome" v-model="produto.nome" required>
            </div>
            <div>
                <label :for="produto.fabricante.nome">Digite o nome do fabricante</label>
                <input placeholder="Fabricante" type="text" class="form-control" :id="produto.fabricante.nome" v-model="produto.fabricante.nome" required>
            </div>
            <div>
                <label :for="produto.unidade">Digite a Unidade do produto</label>
                <input placeholder="Unidade" type="text" class="form-control" :id="produto.unidade" v-model="produto.unidade" required>
            </div>
            <div>
                <label :for="produto.volume">Digite o Volume</label>
                <input placeholder="Volume" type="text" class="form-control" :id="produto.volume" v-model="produto.volume" required>
            </div>
            <div>
                <label for="produto.estoque">Digite a quantidade em Estoque</label>
                <input type="number" class="form-control" :id="produto.estoque" v-model="produto.estoque" required>
            </div>
            <br>
            <button v-if="!this.id" type="submit" class="btn btn-dark" >Cadastrar</button>
            <button v-else type="submit" class="btn btn-dark" >Atualizar Dados</button>
        </form>
    </div>
</template>

<script>
import TextField from '../text-field/TextField.vue'

export default {
    data(){
        return{
            produto:null,
            id: this.$route.params.id,
            executouAcao:false
        }
    },
    components:{
        'text-field':TextField
    },
    created(){
        this.produto = this.produtoDefault()
        if(this.id) {
            this.$axios.get("/"+this.id)
                .then((resp) => this.produto = resp.data)
                .catch((err) => console.log(err))
        }
        console.log(this.produto)
    },
    methods:{
        produtoDefault(){
            return {
                nome:null,
                fabricante:{
                    nome:null
                },
                unidade:null,
                volume:null,
                estoque:null
            }
        },
        cadastrar(){
            this.$axios.post("", this.produto)
                .then()
                .catch((err) => console.log(err));
        },
        atualizar(){
            this.$axios.put("/"+this.produto.id, this.produto)
                .then()
                .catch((err) => console.log(err))
        },
        resolve(){
            if(!this.id){
                this.cadastrar();
            }else{
                this.atualizar();
            }
            this.produto = this.produtoDefault()
            this.executouAcao = true;
        },
        reverse(){
            this.executouAcao = false;
        }
    }
}
</script>

<style>
    form{
        text-align: left
    }
</style>