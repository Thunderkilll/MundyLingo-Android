<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "mondy_lingo";
$conn = new mysqli($servername, $username, $password,$dbname);


if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
 $username = $_POST["username"];
 $password = $_POST["password"];
//$sql = "select * from user where username like '".$_GET["username"]."' and password like'".$_GET["password"]."'";
 $sql = "select * from user where username like '".$username."' and password like'".$password."';";
$result = $conn->query($sql);
$row = $result->fetch_assoc() ;
$username = $row["username"];
$mail = $row["mail"];
$imgUrl = $row["image"];
if ($result->num_rows > 0) {
    echo "Login successuful !!!            ";
	echo "                         ";
	 echo "Username: " . $username."". " Email: " . $mail. "  " ."Image URL :" . $mail. "  ";
} else {
    echo "Error: " . $sql . "                                 " . $conn->error;
}

$conn->close();




?>