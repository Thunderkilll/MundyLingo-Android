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
$sql = "SELECT contenue,duree,isValide,scoreTest,level FROM test WHERE level =".$_GET["level"]."";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "Result : ". "<br>" . $row["contenue"]. " - level: " . $row["level"]. " " . "<br>"."de duree = ".$row["duree"]. "<br>"."du score = ".$row["scoreTest"]. "<br>". "<br>";
    }
} else {
    echo "0 results";
}
$conn->close();
?>