/**
 * Validar formulário
 */

 function valdator() {
	 let nome = frmContact.nome.value;
	 let fone = frmContact.fone.value;
	 
	 if (nome === "") {
		alert('Preencha o campo nome')
		frmContact.nome.focus()
		return false
	 } else if (fone === "") {
		alert('Preencha o campo Telefone')
		frmContact.fone.focus()
		return false
	 } else {
		 document.form["frmContact"].submit()
	 }
 }