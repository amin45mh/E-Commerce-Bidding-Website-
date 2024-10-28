<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome To Auctooze</title>
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
        
        .btn {
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
        
        .btn:last-child {
            margin-right: 0;
        }
        
        .btn:hover {
            background-color: #154360;
        }
        
        .fa-user {
            margin-right: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome To Auctooze</h1>
        <form action="SignInPage.jsp">
            <button type="submit" class="btn"><i class="fas fa-user"></i>Sign In</button>
        </form>
        <form action="SignUpPage.jsp">
            <button type="submit" class="btn"><i class="fas fa-user"></i>Sign Up</button>
        </form>
    </div>
</body>
</html>
