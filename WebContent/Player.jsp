<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.dropbtn {
  background-color: #4CAF50;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown-content a:hover {background-color: #ddd;}

.dropdown:hover .dropdown-content {display: block;}

.dropdown:hover .dropbtn {background-color: #3e8e41;}
</style>
<title>Play video</title>
</head>
<body>

<div class="topnav">
  
  <div class="login-container">
    ${username}
  </div>
</div>

<div class="container" align="center">
<div class="video">
<iframe width="560" height="315" align="center" src="https://www.youtube.com/embed/${suburl}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
</div>
<div class="bar">
<div class="dropdown">
  <button class="dropbtn">Dropdown</button>
  <div class="dropdown-content">
    <a href="#">Poor</a>
    <a href="#">Fair</a>
    <a href="#">Good</a>
    <a href="#">Excellent</a>
  </div>
</div>
<div>
<button>
Comment</button>
<div>
<a href="/favorite">Add favorite</a>
</div>
</div>
</div>

</body>
</html>