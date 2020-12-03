
function setAno() {
	now = new Date;
	var campo = "";
	for(var i = 1900;i <= now.getFullYear();  i++) {
		if(i != now.getFullYear()) {
			campo += `
				<option value='${i}'>${i}</option>`;
		} else {
			campo += `
				<option value='${i}' selected>${i}</option>`;
		}
	}
	$("#lancamento").html(campo);
}