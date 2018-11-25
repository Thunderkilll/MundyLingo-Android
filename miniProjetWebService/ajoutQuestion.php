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

$sql = "INSERT INTO question (level, contenu, vocal, reponse)
VALUES (".$_GET["level"].", '".$_GET["contenu"]."','".$_GET["vocal"]."',".$_GET["reponse"].")";

if ($conn->query($sql) === TRUE) {
    echo "New question created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>