<?php
require "connectionDB.php";
// Check connection


$sql = "INSERT INTO user ( username, password, mail ,image, scoreF, scoreAng , scoreGerm , scoreEsp)
VALUES ('".$_GET["username"]."', '".$_GET["password"]."','".$_GET["mail"]."','".$_GET["image"]."',0 , 0 , 0 , 0)";

if ($conn->query($sql) === TRUE) {
    echo "<br>". "New user created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>