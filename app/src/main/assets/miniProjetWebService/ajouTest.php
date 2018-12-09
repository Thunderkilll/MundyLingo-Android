
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

$sql = "INSERT INTO test (level, contenue, duree, isValide, scoreTest)
VALUES (".$_GET["level"].", '".$_GET["contenue"]."', ".$_GET["duree"]." ,".$_GET["isValide"].",".$_GET["scoreTest"].")";

if ($conn->query($sql) === TRUE) {
    echo "New test created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>
