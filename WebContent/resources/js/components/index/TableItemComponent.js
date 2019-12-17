Vue.component('tab-item', {
    props: ['item'],
    template: `
        <tr>
            <td>{{ item.nome }}</td>
            <td>{{ item.fabricante.nome }}</td>
            <td>{{ item.volume }}</td>
            <td>{{ item.unidade }}</td>
            <td>{{ item.estoque }}</td>
            <td>
                <span @click="">
                <svg id="i-compose" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" width="32" height="32" fill="none" stroke="currentcolor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2">
                    <path d="M27 15 L27 30 2 30 2 5 17 5 M30 6 L26 2 9 19 7 25 13 23 Z M22 6 L26 10 Z M9 19 L13 23 Z" />
                </svg>
            </span>
                <exclude-btn @exclude-item="excluirItem(item.id)"></exclude-btn>
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