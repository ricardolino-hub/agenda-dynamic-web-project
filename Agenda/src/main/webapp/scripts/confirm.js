/**
 * Confirmar exclusão
 */

 function confirmJs (idcon) {
	 let = response = confirm("Deseja excluir este contato ?");
	 if (response === true) {
		 window.location.href = "delete?idcon=" + idcon;
	 }
	 
 }