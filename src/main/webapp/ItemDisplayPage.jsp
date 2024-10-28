<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.time.LocalDateTime" %>
    <%@ page import="java.time.Duration" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="beans.Item" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
}

h1 {
    font-size: 3rem;
    margin: 0 0 40px;
    color: #f1c40f;
}

table {
    border-collapse: collapse;
    width: 100%;
    max-width: 800px;
    margin: 0 auto;
    background-color: #ffffff;
    box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
    overflow: hidden;
}

th,
td {
    text-align: left;
    padding: 12px;
    border: 1px solid #ddd;
    font-size: 16px;
    color: #333;
}
th {
    background-color: #f1c40f;
    color: #fff;
}

td label {
    display: block;
    margin-bottom: 6px;
}

td:last-child {
    text-align: center;
}

input[type="radio"] {
    margin-right: 6px;
}

input[type="submit"] {
    padding: 24px;
    font-size: 2rem;
    font-weight: bold;
    background-color: #f1c40f;
    color: #fff;
    border: none;
    border-radius: 4px;
    font-size: 16px;
    cursor: pointer;
}

input[type="submit"]:hover {
    background-color: #e6b800;
}
</style>

</head>
<body bgcolor = "#f0f0f0">
<h1 align = "center"> Auctooze </h1>

<form action="BidPrepServlet">

<div align="center">
		<table border="1">
		        <tr>
		           <th>Item Name</th>
		           <th>Price</th>
		           <th>Auction Type</th>
		           <th>Remaining Time</th>
		           <th>Action</th>
		        </tr>    
			<%
			List<Item> items = (ArrayList<Item>) request.getAttribute("results");
			for (int i = 0; i < items.size(); i++) {
				String item = items.get(i).getName();
				String itemPrice = Double.toString(items.get(i).getHighestBid());
				String auctionType = items.get(i).getAuctionType();
				
				String endTime;
				LocalDateTime endDate = items.get(i).getAuctionEndTime();
				if (endDate != null) {
					Duration dur = Duration.between(LocalDateTime.now(), endDate);
					endTime = String.format("%d days:%02d hours:%02d mins:%02d secs",dur.toDaysPart(), dur.toHoursPart(), dur.toMinutesPart(), dur.toSecondsPart());
				}
				else endTime = "Now";
		%>
		
		<tr>
				<td>
					<div>
						<label for="s<%= i + 1 %>"><%= item %></label>
					</div>
				</td>
				<td>
					<div>
						<label><%= itemPrice %></label>
					</div>
				</td>
				<td>
					<div>
						<label><%= auctionType %></label>
					</div>
				</td>
				<td>
					<div>
						<label><%= endTime %></label>
					</div>
				</td>
				<td>
					<div>
						<p><input type="radio" id="s<%= i + 1 %>" name="checked" value="<%= item %>"></p>
					</div>
				</td>
			</tr>	
		<%
			}
		%>
		</table>
		<input type="hidden" name="userName" value="${userName}">
		<br></br><input type="submit" value="Bid">
	</div>
	
	</form>

</body>
</html>