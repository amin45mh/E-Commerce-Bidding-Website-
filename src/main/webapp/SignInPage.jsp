<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
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
        
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        
        input[type="text"], input[type="password"] {
            padding: 10px;
            font-size: 1.5rem;
            margin-bottom: 20px;
            border-radius: 5px;
            border: none;
        }
        
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #1a5276;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 1.5rem;
            margin-top: 20px;
            cursor: pointer;
        }
        
        input[type="submit"]:hover {
            background-color: #154360;
        }
        
        .fa-user {
            margin-right: 10px;
        }
        
        label {
            color: #fff;
            font-size: 1.5rem;
            margin-bottom: 10px;
        }
        
        p {
            color: #fff;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Sign In</h1>
        <h1>${message}</h1>
        <form action="SignInServlet">
            <label for="username">Username:</label>
            <input type="text" id="username" name="userName"/>
            <label for="password">Password:</label>
            <input type="password" id="password" name="passWord"/>
            <input type="submit" value="Sign In"/>
        </form>
        <form action="SignUpPage.jsp">
            <p>Don't have an account?</p>
            <input type="submit" value="Sign Up"/>
        </form>
    </div>
</body>
</html>
