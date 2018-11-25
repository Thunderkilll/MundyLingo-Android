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
//donner l'ID de question et le level pour le quelle on cherche la reponse

//$question = $_GET['question']   . $question
//$level = $_GET['level']   . $level
$sql = "SELECT text,image,vocal,question,level FROM reponse WHERE question =".$_GET["question"]." and level =".$_GET["level"]."" ;
$result = $conn->query($sql);
//donner l'id de question pour le quelle on donne une reponse 

$sql2 = "SELECT contenu FROM question WHERE id =".$_GET["question"]."" ;
$result2 = $conn->query($sql2);
 while($row2 = $result2->fetch_assoc()) {
$contenu = $row2["contenu"];
//echo $contenu;
 }
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "Question: " . $contenu. " - reponse: " . $row["text"]. " " . "<br>";
    }
} else {
    echo "0 results";
}
$conn->close();
?>