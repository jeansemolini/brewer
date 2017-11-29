Brewer = Brewer || {};

Brewer.PesquisaRapidaCliente = (function(){
	
	function PesquisaRapidaCliente() {
		this.pesquisaRapidaClientesModal = $('#pesquisaRapidaClientes');
		this.nomeInput = $('#nomeClienteModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-clientes-btn');
		this.containerTabelaPesquisa = $('#containerTabelaPesquisaRapidaCliente');
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-rapida-cliente').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
		this.mensagemErro = $('.js-mensagem-erro');
		this.btnCloseModal = $('.btnCloseModal');
	}
	
	PesquisaRapidaCliente.prototype.iniciar = function(){
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		this.pesquisaRapidaClientesModal.on('shown.bs.modal', onModalShow.bind(this));
		this.pesquisaRapidaClientesModal.on('hide.bs.modal', onModalClose.bind(this));
		this.btnCloseModal.on('click', onHideMensagem.bind(this));
	}
	
	function onModalShow(){
		this.nomeInput.focus();
	}
	
	function onModalClose(){
		this.nomeInput.val('');
		this.containerTabelaPesquisa.html('');
		this.mensagemErro.addClass('hidden');		
	}
	
	function onPesquisaRapidaClicado(event){
		event.preventDefault(); //parar o comportamento default do botao, que seria submter o formulario
		
		$.ajax({
			url: this.pesquisaRapidaClientesModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				nome: this.nomeInput.val()
			},
			success: onPesquisaConcluida.bind(this),
			error: onErroPesquisa.bind(this)
		});
	}
	
	function onPesquisaConcluida(resultado){
		this.mensagemErro.addClass('hidden');
		
		var html = this.template(resultado);
		this.containerTabelaPesquisa.html(html);
		
		var tabelaClientePesquisaRapida = new Brewer.TabelaClientePesquisaRapida(this.pesquisaRapidaClientesModal);
		tabelaClientePesquisaRapida.iniciar();
	}
	
	function onErroPesquisa(){
		this.mensagemErro.show();
		this.mensagemErro.removeClass('hidden');
	} 
	
	function onHideMensagem(){
		this.mensagemErro.hide();
	}
	
	return PesquisaRapidaCliente;
	
})();

Brewer.TabelaClientePesquisaRapida = (function(){
	
	function TabelaClientePesquisaRapida(modal){
		this.modalCliente = modal;
		this.cliente = $('.js-cliente-pesquisa-rapida');
	}
	
	TabelaClientePesquisaRapida.prototype.iniciar = function(){
		this.cliente.on('click', onClienteSelecionado.bind(this));
	}
	
	function onClienteSelecionado(evento){
		this.modalCliente.modal('hide');
		
		var clienteSelecionado = $(evento.currentTarget);	
		$('#nomeCliente').val(clienteSelecionado.data('nome'));
		$('#codigoCliente').val(clienteSelecionado.data('codigo'));
	}
	
	return TabelaClientePesquisaRapida;
	
})();

$(function(){
	var pesquisaRapidaCliente = new Brewer.PesquisaRapidaCliente();
	pesquisaRapidaCliente.iniciar();
});