var servidor = "";

function btnSalvar(Formulario) {
	var formularioCad = document.getElementById(Formulario);
	var service = "";
	if(formularioCad.querySelector("#titulo").value == "") {
		alert("Titulo do Filme não pode estar em branco !");
	} else if(formularioCad.querySelector("#diretor").value == "") {
		alert("Diretor não pode estar em branco !");
	} else if(formularioCad.querySelector("#duracao").value == "" || formularioCad.querySelector("#duracao").value == "0") {
		alert("Favor informar a duração do filme !");
	} else if(formularioCad.querySelector("#estudio").value == "") {
		alert("Favor informar o Estudio responsável pelo filme !");
	} else if(formularioCad.querySelector("#id").value == "") {
		service = "inserirFilme";
	} else {
		service = "alterarFilme";
	}
	
	const formData = new FormData(formularioCad);
	const data = [...formData.entries()];
  	const asString = data
      .map(x => `${encodeURIComponent(x[0])}=${encodeURIComponent(x[1])}`)
      .join('&');
	
	console.log(formData);
	if(service != "") {
		fetch(servidor + "Controller?service=" + service + "&" + asString).then(function(response) {
		response.json().then(function(dados) {
				alert(dados.mensagem);
				if(dados.condicao) {
					$("#" + Formulario).trigger("reset");
					$("#manForm").modal("hide");
					AtualizarDados();
				}
		    });
		});
	}
}

function AtualizarDados() {
	var tabela = "";
	fetch(servidor + "Controller?service=listarFilme").then(function(response) {
		response.json().then(function(dados) {
			dados.forEach((filme) => {
					tabela += `
						<tr>
							<td>${filme.titulo}</td>
							<td>${filme.diretor}</td>
							<td>${filme.estudio}</td>
							<td>${filme.lancamento}</td>
				            <td>${filme.duracao}</td>
				            <td>
				            <button type="button" class="btn btn-primary btn-edit btn-xs btn-flat" onclick="editaFilme(${filme.id})">Editar</button>
				            <button type="button" class="btn btn-danger btn-xs btn-flat" onclick="excluiFilme(${filme.id})">Excluir</button>
				            </td> 
						</tr>
		        	`;
				});
			$("#tabelaFilme").html(tabela);
	    });
	});
	
}

function editaFilme(id) {
	fetch(servidor + "Controller?service=consultarFilme&id=" + id).then(function(response) {
	response.json().then(function(dados) {
			$("#Formfilme").trigger("reset");
			var formularioCad = document.getElementById("Formfilme");
			formularioCad.querySelector("#id").value = dados.id;
			formularioCad.querySelector("#titulo").value = dados.titulo;
			formularioCad.querySelector("#estudio").value = dados.estudio;
			formularioCad.querySelector("#diretor").value = dados.diretor;
			formularioCad.querySelector("#lancamento").value = dados.lancamento;
			formularioCad.querySelector("#duracao").value = dados.duracao;
			$("#manForm").modal("show");
	    });
	});
}

function excluiFilme(id) {
	fetch(servidor + "Controller?service=deletarFilme&id=" + id).then(function(response) {
	response.json().then(function(dados) {
			alert(dados.mensagem);
			AtualizarDados();
	    });
	});
}

AtualizarDados();