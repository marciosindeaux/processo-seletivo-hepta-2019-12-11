Vue.component('tab-item', {
    props: {
        item:Object
    },
    template: `
        <tr>
            <td>{{ item.nome }}</td>
            <td>{{ item.fabricante.nome }}</td>
            <td>{{ item.volume }}</td>
            <td>{{ item.unidade }}</td>
            <td>{{ item.estoque }}</td>
            <td>
                <edit-btn v-bind:produto="this.item" > </edit-btn>
                <exclude-btn @exclude-item="excluirItem(this.item.id)" v-bind:key="this.item.id"> </exclude-btn>
            </td>  
        </tr>
    `,
    methods:{
        excluirItem(idProduto) {
            axios.delete("/mercado/rs/produtos/"+idProduto)
                .then(response => {
                }).catch( function (error) {
            }).finally(function () {});
            window.location.reload()
        }
    }
});