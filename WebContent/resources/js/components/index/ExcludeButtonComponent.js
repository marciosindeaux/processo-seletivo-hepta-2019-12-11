Vue.component('exclude-btn',{
    template:`
    <a>
        <a  data-toggle="modal" data-target="#excluir">
           <span >
                <svg id="i-close" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" width="32" height="32" fill="none" stroke="currentcolor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2">
                    <path d="M2 30 L30 2 M30 30 L2 2" />
                </svg>
            </span>
        </a>
        
        <div class="modal fade" id="excluir" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Tem certeza que deseja excluir esse Item ?</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                        <button type="button" class="btn btn-primary" @click="excluirItem()">Excluir Item</button>
                    </div>
                </div>
            </div>
        </div>
    </a>
    `,
    methods: {
        excluirItem() {
            this.$emit('exclude-item');
            window.location.reload()
        }
    }
});
