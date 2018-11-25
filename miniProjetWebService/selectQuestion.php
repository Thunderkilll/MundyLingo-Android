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
//donner le level pour le quelle on cherche la question

//$level = $_GET['level']   . $level
$sql = "SELECT contenu,level FROM question WHERE level =".$_GET["level"]."" ;
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "Question: " . $row["contenu"]. " - level: " . $row["level"]. " " . "<br>";
    }
} else {
    echo "0 results";
}
$conn->close();
?>