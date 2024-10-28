<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
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
            color: #f1c40f;
        }
        
        p {
            font-size: 1.5rem;
            margin: 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Error</h1>
        <p>${error}</p>
    </div>
</body>
</html>
