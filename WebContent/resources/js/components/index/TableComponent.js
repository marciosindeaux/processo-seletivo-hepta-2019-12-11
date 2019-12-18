Vue.component('general-table',{
    props:['lista'],
    template:`
    <div class="container table" >
        <br>
		<h4>Lista de Produtos</h4>
        <br>
		<table style="width: 100%;" class="table-striped">
			<thead class="thead-dark">
				<tr>
					<th>Nome</th>
					<th>Fabricante</th>
					<th>Volume</th>
					<th>Unidade</th>
					<th>Estoque</th>
					<th> </th>
				</tr>
			</thead>
            <tbody id="event-table" >
                <tab-item 
                    v-for="item in this.lista " 
                    v-bind:key="item.id"
                    v-bind:item="item">
                </tab-item> 
			</tbody>
		</table>
	</div>
    `,
});