<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Auctooze</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <style>
        body {
            background-color: #0A192F;
            color: #f1c40f;
            font-family: sans-serif;
            margin: 0;
            padding: 0;
        }
        
        .container {
            max-width: 960px;
            margin: 0 auto;
            padding: 40px;
            text-align: center;
        }
        
        h1 {
            font-size: 3rem;
            margin: 0 0 40px;
            color: #f1c40f;
        }
        
        p {
            font-size: 1.5rem;
            margin-bottom: 20px;
            color: white;
        }
        
        form {
            margin-top: 40px;
        }
        
        h2 {
            font-size: 2rem;
            margin-bottom: 20px;
            color: #f1c40f;
            text-align: left;
        }
        
        input[type="text"] {
            padding: 10px;
            font-size: 1.5rem;
            border: none;
            border-radius: 5px;
            margin-bottom: 20px;
            width: 100%;
        }
        
        button[type="submit"] {
            display: inline-block;
            padding: 10px 20px;
            background-color: #1a5276;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 1.5rem;
            margin: 20px 0;
            cursor: pointer;
        }
        
        button[type="submit"]:hover {
            background-color: #154360;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Auctooze</h1>
        <p>First Name: ${fName}</p> 
        <p>Last Name: ${lName}</p> 
        <p>Street: ${street}</p> 
        <p>Number: ${number}</p>
        <p>City: ${prov}</p> 
        <p>Country: ${coun}</p> 
        <p>Postal Code: ${postal}</p> 
        <p>Total Cost: $${tCost}</p> 

        <form action="ReceiptServlet">
            <h2>Credit Card Info</h2>
            Card #: <input type="text" name="cardNum"/><br/>
            Card Holder: <input type="text" name="cardHold"/><br/>
            Exp. Date: <input type="text" name="date"/><br/>
            Security Code: <input type="text" name="secCode"/><br/>
            <input type="hidden" name="userName" value="${userName}">
            <input type="hidden" name="itemID" value="${itemID}">
            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>
