<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "mondy_lingo";
$conn = new mysqli($servername, $username, $password,$dbname);


if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$sql = "select * from user where username like '".$_GET["username"]."' and password like'".$_GET["password"]."'";
$result = $conn->query($sql);
$row = $result->fetch_assoc() ;
$username = $row["username"];
$mail = $row["mail"];
$imgUrl = $row["image"];
if ($result->num_rows > 0) {
    echo "Login successuful". "<br>" ."welcome". "<br>";
	 echo "Username: " . $username."". "<br>". " Email: " . $mail. "  " . "<br>"."Image URL :" . $mail. "  ". "<br>" ;
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();




?>