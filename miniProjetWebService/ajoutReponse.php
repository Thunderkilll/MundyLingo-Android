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

$sql = "INSERT INTO reponse (level, text, image, vocal, question)
VALUES (".$_GET["level"].", '".$_GET["text"]."','".$_GET["image"]."','".$_GET["vocal"]."',".$_GET["question"].")";

if ($conn->query($sql) === TRUE) {
    echo "New reponse created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>