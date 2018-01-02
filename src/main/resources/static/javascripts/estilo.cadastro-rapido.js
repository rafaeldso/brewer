var Brewer = Brewer || {};

Brewer.EstiloCadastroRapido = (function(){
	
	function EstiloCadastroRapido(){
		this.modal = $('#modalCadastroRapidoEstilo');
		this.form = this.modal.find('form');
		this.btnSalvar = this.modal.find('.js-modal-cadastro-estilo-salvar-btn');
		this.inputNomeEstilo = $('#nomeEstilo');
		this.url = this.form.attr('action');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-estilo');
	}
	
	EstiloCadastroRapido.prototype.iniciar = function(){
		this.form.on('submit',function(event) {event.preventDefault() });
		this.modal.on('shown.bs.modal', OnModalShow.bind(this));
		this.modal.on('hide.bs.modal', OnModalClose.bind(this));
		this.btnSalvar.on('click', onClickSalvar.bind(this));
	}
	
	function OnModalShow(){
		this.inputNomeEstilo.focus();
	}
	function OnModalClose(){
		this.inputNomeEstilo.val('');
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onClickSalvar(){
		var nomeEstilo = this.inputNomeEstilo.val().trim();
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({nome: nomeEstilo}),
			error: onErroSalvandoEstilo.bind(this),
			success: onEstiloSalvo.bind(this)
		});
	}
	function onErroSalvandoEstilo(obj){
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>'+mensagemErro+'</span>');
		this.form.find('.form-group').addClass('has-error');
	}
	function onEstiloSalvo(estilo){
		var comboEstilo = $('#estilo');
		comboEstilo.append('<option value='+estilo.codigo+'>'+estilo.nome+'</option>');
		comboEstilo.val(estilo.codigo);
		this.modal.modal('hide');
	}
	
	return EstiloCadastroRapido;
	
}());

$(function() {
	var estiloCadastroRapido = new Brewer.EstiloCadastroRapido();
	estiloCadastroRapido.iniciar();
	console.log('O JS foi modificado');
	
});