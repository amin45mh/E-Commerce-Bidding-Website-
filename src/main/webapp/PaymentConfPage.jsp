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
        }
        
        h1 {
            font-size: 3rem;
            margin: 0 0 40px;
            color: #f1c40f;
        }
        
        h2, p {
            color: white;
            font-size: 2rem;
            margin: 20px 0;
        }
        
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #1a5276;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 1.5rem;
            margin: 40px 10px 0 0;
            cursor: pointer;
        }
        
        .btn:last-child {
            margin-right: 0;
        }
        
        .btn:hover {
            background-color: #154360;
        }
        
        input[type="radio"] {
            margin-right: 10px;
        }
        
        label[for="s1"] {
            margin-right: 40px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Auctooze</h1>
        <h2>${name}</h2>
        <p>Description: ${desc}</p>
        <p>Shipping: $${ship}</p>
        <form action="PaymentServlet">
            <p>Expedited Shipping</p>
            <input type="radio" id="s1" name="exp" value="exp">
            <label for="s1">$${exp}</label>
            <br>
            <p>Price: $${price}</p>
            <p>Winner: ${bidder}</p>
            <input type="hidden" name="userName" value="${userName}">
            <input type="hidden" name="itemID" value="${itemID}">
            <button type="submit" class="btn"><i class="fas fa-money-bill-wave"></i>Pay Now</button>
        </form>
    </div>
</body>
</html>
