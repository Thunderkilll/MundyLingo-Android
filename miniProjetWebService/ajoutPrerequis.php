<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "mondy_lingo";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$sql = "INSERT INTO presrequis (objet, text, level)
VALUES ('".$_GET["objet"]."','".$_GET["text"]."','".$_GET["level"]."')";

if ($conn->query($sql) === TRUE) {
    echo "New presRequis created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>