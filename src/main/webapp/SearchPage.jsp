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
        
        label {
            color: #fff;
        }
        
        input[type="text"] {
            padding: 10px;
            font-size: 1.5rem;
            border-radius: 5px;
            border: none;
            margin-bottom: 20px;
        }
        
        input[type="submit"] {
            display: inline-block;
            padding: 10px 20px;
            background-color: #1a5276;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 1.5rem;
            margin: 10px;
            cursor: pointer;
        }
        
        input[type="submit"]:hover {
            background-color: #154360;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Auctooze</h1>
        <h1>${message}</h1>
        <form action="ItemDisplayServlet"> 
            <label for="searchTerm" style="color: #fff">Search:</label>
            <input type="text" name="searchTerm"/><br/>
            <input type="hidden" name="userName" value="${userName}">
            <input type="submit" value="Search"/> 	
        </form>
    </div>
</body>
</html>
