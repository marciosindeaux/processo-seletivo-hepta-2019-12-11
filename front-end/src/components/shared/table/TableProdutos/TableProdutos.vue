<template>
    <div class="container table">
        <div v-if="this.excluido" class="alert alert-warning alert-dismissible fade show" role="alert">
            <strong>Item Excluido com sucesso</strong> 
            <button @click="setExclude" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <text-field text="Lista de Produtos"/>
        <table style="width: 100%;" class="table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>Nome</th>
                    <th>Fabricante</th>
                    <th>Volume</th>
                    <th>Unidade</th>
                    <th>Estoque</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody id="event-table">
                <table-item 
                    v-for="produto in produtos" 
                    :key="produto.id"
                    :item="produto"
                    @tbitem:excluir="excluir"                    
                    />
            </tbody>
        </table>
       
    </div>
</template>

<script>
import TableItem from'./TableItem.vue';
import TextField from '../../text-field/TextField.vue';
export default {
    data() {
        return {
            produtos:[],
            excluido:false
        }
    },
    components: {
        'table-item':TableItem,
        'text-field':TextField,
    },
    created(){
        this.buscarTodos();
    },
    methods:{
        buscarTodos(){
            this.$axios.get()
            .then((resp) => this.produtos = resp.data)
            .catch((err) => console.log(err));
        },
        excluir(){
            this.buscarTodos();
            this.excluido = true;
            this.buscarTodos();
        },
        setExclude(){
            this.excluido = false
            this.buscarTodos();
        }
    }

}
</script>