<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Auctooze</title>

<script type="text/javascript" src="scripts/mc.js">
	;
</script>

<script type="text/javascript" src="scripts/timer.js">
; 
</script>
	<style>
	body {
	  background-color: #0A192F;
	  font-family: sans-serif;
	  margin: 0;
	  padding: 0;
	  display: flex;
	  flex-direction: column;
	  justify-content: center;
	  align-items: center;
	  min-height: 100vh;
	  color: white; /* added */
	}
	
	input[type="button"] {
	  background-color: #f1c40f;
	  color: white;
	  border: none;
	  padding: 5px 10px;
	  font-size: 1.2rem;
	  font-weight: bold;
	  border-radius: 4px;
	  cursor: pointer;
	}
	
  button {
    background-color: #f1c40f;
    color: white;
    border: none;
    padding: 5px 10px;
    font-size: 1rem;
    font-weight: bold;
    margin-top: 20px;
    border-radius: 4px;
    cursor: pointer;
  }

  p {
    color: white;
    font-size: 1.5rem;
    margin: 0 0 16px;
  }
	#timer {
    font-size: 24px;
    font-weight: bold;
    margin-top: 20px;
    text-align: center;
    color: #f1c40f;
	}
	h1 {
	    font-size: 3rem;
	    margin: 0 0 40px;
	    color: #f1c40f;
	}
	
	
	</style>

</head>
<body>
ITEM : ${fchecked}
	<form>
		<input id="message" type="text">
		<input type="hidden" name="proceed" value="True" />
		<input onclick="wsSendMessage();" value="Bid" type="button">
	</form>
	<form action="WelcomeServlet">
	<button  onclick="wsCloseConnection(); type="button" name="Disconnect" value="Disconnect">Disconnect</button>
	</form>
	<br>
	<textarea id="echoText" rows="5" cols="30"></textarea>
	<script type="text/javascript">
	    var added = '<%= session.getAttribute("fchecked") %>';
	    var ftime = '<%= session.getAttribute("firstTime") %>';
	    var proceed = '<%= session.getAttribute("proceed") %>';
		var webSocket = new WebSocket("ws://localhost:8080/Auctooze/ForwardBidServlet");
		var echoText = document.getElementById("echoText");
		echoText.value = "";
		var message = document.getElementById("message");
		webSocket.onopen = function(message){ wsOpen(message);};
		webSocket.onmessage = function(message){ wsGetMessage(message);};
		webSocket.onclose = function(message){ wsClose(message);};
		webSocket.onerror = function(message){ wsError(message);};
		
		function wsOpen(message){
			echoText.value += "Submit your bid in the box above! \n";
		}
		function wsSendMessage(userName){
			 // create a URLSearchParams object from the current URL
		    var searchParams = new URLSearchParams(window.location.search);
		    
		    // retrieve the value of the "userName" parameter
		    var userName = searchParams.get("userName");
		    var messageWithUserName = message.value.concat(added, ":", userName);
			webSocket.send(messageWithUserName);
			echoText.value += message.value + "\n";
			message.value = "";
		}
		function wsCloseConnection(){
			webSocket.close();
		}
		function wsGetMessage(message){
			//echoText.value += message.data + "\n";
			if (message.data === "reload") {
		        location.reload();
		    } else {
		        echoText.value += message.data + "\n";
		    }
		}
		function wsClose(message){
			echoText.value += "Disconnect... \n";
		}

		function wserror(message){
			echoText.value += "Error ... \n";
		}
	</script>
	<p>Description: ${desc}</p>
	<p>Shipping: ${ship}</p>
	<p>Current Bid: ${highBid}</p>
	<p>Bidder: ${bidder}</p>
	<div id="timer"></div>
	<script>
    function countdown() {
    var now = new Date().getTime();
    var date = '<%= session.getAttribute("time") %>';
    var deadline = new Date(date).getTime();
    var distance = deadline - now;
	var days = Math.floor(distance / (1000 * 60 * 60 * 24));
    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((distance % (1000 * 60)) / 1000);
    document.getElementById("timer").innerHTML = days + "d :" + hours + "h :" + minutes + "m :" + seconds + "s ";
    if (distance < 0) {
        clearInterval(interval);
        var userName = '<%= session.getAttribute("userName") %>';
        document.getElementById("timer").innerHTML = "Time's up!";
        var servletURL = "/Auctooze/PaymentConfServlet?userName="+userName;
        window.location.replace(servletURL);
    }
}
var interval = setInterval(countdown, 1000);
</script>

</body>
</html>