/**
 * Validar formulário
 */

 function validator() {
	 let nome = frmContact.name.value
	 let fone = frmContact.fone.value

	 if (nome === "") {
		alert('Preencha o campo nome')
		frmContact.nome.focus()
		return false
	 } else if (fone === "") {
		alert('Preencha o campo Telefone')
		frmContact.fone.focus()
		return false
	 } else {
		 document.getElementById("frmContact").submit();
	 }
 }