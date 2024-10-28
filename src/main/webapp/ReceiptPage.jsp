<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Auctooze</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        body {
            background-color: #0A192F;
            font-family: sans-serif;
            margin: 0;
            padding: 0;
        }
        
        .container {
            max-width: 960px;
            margin: 0 auto;
            padding: 40px;
            text-align: center;
            color: #f1c40f;
        }
        
        h1 {
            font-size: 3rem;
            margin: 0 0 40px;
        }
        
        p {
            font-size: 1.5rem;
            margin-bottom: 20px;
            color: white;
        }
        
        form {
            margin-top: 40px;
        }
        
        button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #1a5276;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 1.5rem;
            margin: 40px 10px 0 0;
            cursor: pointer;
        }
        
        button:hover {
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
        <p>Item ID: ${itemID}</p> 
        <p>Card Type Used: ${cardType}</p> 
        
        <form action="WelcomeServlet">
            <h2 align="left">Shipping Details</h2>
            <p>Your ${itemName} will be shipped in ${itemID} days!</p>
            <p>Thank you for shopping with us!</p>
            <button type="submit">Finish</button>
        </form>
    </div>
</body>
</html>
