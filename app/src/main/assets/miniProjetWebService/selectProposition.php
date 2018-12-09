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
$question = $_GET["question"];
//$numero = $_GET["numeroP"];
$level = $_GET["level"];
///a verifier android pour la langue au niveau de l'URL
$langue = $_GET["langue"];
/*$question = 1;
$numero = 1;
$level =1;*/

$sql = "SELECT * FROM propositions WHERE level ='".$level."'AND question ='".$question."' AND langue = '".$langue."'" ;
$result = $conn->query($sql);


$response = array(); 


if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
	$response[]=array("id"=>$row["id"],"level"=>$row["level"],"langue"=>$row["langue"],"question"=>$row["question"],"numeroP"=>$row["numeroP"],"contenu"=>$row["contenu"],"image"=>$row["image"],"voix"=>$row["voix"]);
    }
} else {
    echo "0 results";
}
$conn->close();
echo json_encode($response);
?>