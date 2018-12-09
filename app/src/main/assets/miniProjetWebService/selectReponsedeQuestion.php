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

$question = $_GET['question'] ;
$level = $_GET['level'] ;
$idLangue = $_GET['idLangue'];



$sql = "SELECT * FROM reponse WHERE level='".$level."'AND question='".$question."' AND idLangue='".$idLangue."' AND repTest= 0";

$result = $conn->query($sql);
//donner l'id de question pour le quelle on donne une reponse 

$response = array(); 

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
       // echo "Question: " . $contenu. " - reponse: " . $row["text"]. " " . "<br>";
	   	$response []=array("id"=>$row["id"],"level"=>$row["level"],"text"=>$row["text"],"image"=>$row["image"],"vocal"=>$row["vocal"],"question"=>$row["question"],"repTest"=>$row["repTest"],"idLangue"=>$row["idLangue"],"bonus"=>$row["bonus"]);
    }
} else {
    echo "0 results";
}
$conn->close();
echo json_encode($response);
?>