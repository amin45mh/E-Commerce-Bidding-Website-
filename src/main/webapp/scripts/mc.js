function validate() {
	var ok = true;
	return ok;
}

function handler(request) {
	if ((request.readyState == 4) && (request.status == 200)) {
		var target = document.getElementById("result");
		target.innerHTML = request.responseText;
		var rs = request.responseText;

		addParagraphs(target, rs);
	}
}

function doSimpleAjax(address) {
	var request = new XMLHttpRequest();
	var data = '';
	data += "theFirstName=" + document.getElementById("theFirstName").value + "&";
	data += "theLastName=" + document.getElementById("theLastName").value + "&";
	data += "theBid=" + document.getElementById("theBid").value + "&";
	data += "checked=" + document.getElementById("checked").value + "&";
	data += "calculate=true";
	request.open("GET", address + "?" + data, true);
	request.onreadystatechange = function() {
		handler(request);
	};

	request.send(null);
}

function addParagraphs(parent, p1) {

	var html_p1 = document.createElement("p");

	//create the  label
	var text_p1 = document.createTextNode("NEW TEXT The service result is : " + p1);

	//add the content to the <p> element
	html_p1.appendChild(text_p1);

	//add the <p> element to the <div>
	parent.appendChild(html_p1);

}