<template>
    <tr>
        <td>{{ item.nome }}</td>
        <td>{{ item.fabricante.nome }}</td>
        <td>{{ item.volume }}</td>
        <td>{{ item.unidade }}</td>
        <td>{{ item.estoque }}</td>
        <td class="waring">
            <router-link :to="{name:'Editar', params:{id:item.id}}">
                <button type="button" class="btn btn-warning">Editar</button> 
            </router-link>
        </td>
        <td class="danger">
            <button type="button" class="btn btn-danger" @click="exluirItem">Excluir</button>
        </td>
    </tr>
</template>

<script>
export default {
    props:{
        item:{
            type: Object,
            default: undefined,
            required: true
        }
    },
    methods:{
        exluirItem(){
            this.$axios.delete("/"+this.item.id)
                .then()
                .catch((err) => console.log(err));

            this.$emit("tbitem:excluir", true)
        }
    }
}
</script>
<style scoped>
</style>