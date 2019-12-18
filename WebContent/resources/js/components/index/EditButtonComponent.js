Vue.component('edit-btn',{
    props:['produto'],
    data(){
        return{
            target: null,
            source:null
        }
    },
    methods:{
        setModalId(){
            $('.' + this.produto.id).modal('toggle')
        }
    },
    mounted(){
        this.source = this.produto.id;
        this.target = "#"+this.source;
    },
    template:`
    <a>
        <a  data-toggle="modal" data-target="#edit" v-on:click="setModalId">
           <span >
                <svg id="i-compose" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" width="32" height="32" fill="none" stroke="currentcolor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2">
                    <path d="M27 15 L27 30 2 30 2 5 17 5 M30 6 L26 2 9 19 7 25 13 23 Z M22 6 L26 10 Z M9 19 L13 23 Z" />
                </svg>
            </span>
        </a>
        
        <div class="modal fade" v-bind:class="this.source" id="edit" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Edição de Produto</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form-input
                            v-bind:key="this.source"
                            v-bind:cadastro="false"
                            v-bind:produto="this.produto">    
                        </form-input>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                        <button type="button" class="btn btn-primary" Alterar item </button>
                    </div>
                </div>
            </div>
        </div>
    </a>
    `,
});
