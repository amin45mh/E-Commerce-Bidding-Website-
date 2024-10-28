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

        h2 {
            font-size: 2rem;
            margin: 20px 0;
            color: #f1c40f;
        }

        p {
            font-size: 1.5rem;
            margin: 10px 0;
            color: #fff;
        }
        
        input[type="submit"] {
            display: inline-block;
            padding: 10px 20px;
            background-color: #1a5276;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 1.5rem;
            margin: 20px 10px 0;
            cursor: pointer;
        }
        
        input[type="submit"]:hover {
            background-color: #154360;
        }
        
        .fa-user {
            margin-right: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Auctooze</h1>
        <h2>${name}</h2> 
        <p>Description: ${desc}</p>
        <p>Shipping: $${ship}</p>
        <p>Price: $${price}</p>
        <form action="PaymentConfServlet">
            <input type="submit" value="Buy Now"/> 
            <input type="hidden" name="userName" value="${userName}">
        </form>
    </div>
</body>
</html>
