Vue.component('navigation',{
    props:['first'],
    template:`
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="index.html" style="font-size: 20px;">Mercadinho</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#textoNavbar" aria-controls="textoNavbar" aria-expanded="false" aria-label="Alterna navegação">
		    <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="textoNavbar">
            <ul class="navbar-nav mr-auto" v-if="first">
                <li class="nav-item active">
                    <a class="nav-link" href="index.html">Listagem De Produtos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="novo-produto.html">Cadastro de novos Produtos</a>
                </li>
            </ul>
            <ul  class="navbar-nav mr-auto" v-else>
                <li class="nav-item ">
                    <a class="nav-link" href="index.html">Listagem De Produtos</a>
                </li>
                <li class="nav-item active" >
                    <a class="nav-link" href="novo-produto.html">Cadastro de novos Produtos</a>
                </li>
            <ul>
	    </div>
    </nav>
    `
})