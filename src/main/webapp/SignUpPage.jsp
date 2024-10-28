<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign Up</title>
<style>
body {
  background-color: #0A192F;
  font-family: sans-serif;
  margin: 0;
  padding: 0;
}

h1 {
  text-align: center;
  color: #f1c40f;
}

form {
  margin: 20px auto;
  max-width: 500px;
  padding: 20px;
  
}

form label {
  display: inline-block;
  width: 150px;
  text-align: right;
  margin-right: 10px;
  color: #fff;
}

form input {
  padding: 5px;
  margin-bottom: 20px;
  border-radius: 5px;
  border: none;
  width: 250px;
}

form button {
  display: inline-block;
  margin-left: 160px;
  margin-top: 10px;
  background-color: #1a5276;
  color: #fff;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
 
}
</style>
</head>
<body>
<h1>Sign Up</h1>
<h1>${message}</h1>
<form action="SignUpServlet">
  <label for="firstName">First Name:</label>
  <input type="text" name="firstName" id="firstName"><br>

  <label for="lastName">Last Name:</label>
  <input type="text" name="lastName" id="lastName"><br>

  <label for="address">Street Address:</label>
  <input type="text" name="address" id="address"><br>

  <label for="streetNumber">Street Number:</label>
  <input type="text" name="streetNumber" id="streetNumber"><br>

  <label for="postalCode">Postal Code:</label>
  <input type="text" name="postalCode" id="postalCode"><br>

  <label for="city">City:</label>
  <input type="text" name="city" id="city"><br>

  <label for="country">Country:</label>
  <input type="text" name="country" id="country"><br>

  <label for="userName">Username:</label>
  <input type="text" name="userName" id="userName"><br>

  <label for="passWord">Password:</label>
  <input type="text" name="passWord" id="passWord"><br>

  <button type="submit" name="Submit" value="Submit">Submit</button>
</form>
</body>
</html>


