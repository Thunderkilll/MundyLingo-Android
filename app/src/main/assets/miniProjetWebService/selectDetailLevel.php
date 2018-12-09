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

$id = $_GET["NumLVL"];
$langue = $_GET["langue"];
$sql = "SELECT * FROM level WHERE NumLVL ='".$id."' AND langue='".$langue."'" ;
$result = $conn->query($sql);


$response = array(); 


if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
	$response []= array("id"=>$row["id"],"NumLVL"=>$row["NumLVL"],"diffeculte"=>$row["diffeculte"],"langue"=>$row["langue"],"dateDeb"=>$row["dateDeb"],"dateFin"=>$row["dateFin"],"Prerequis"=>$row["Prerequis"],"nbrQuestion"=>$row["nbrQuestion"],"idCour"=>$row["idCour"]);
      
    }
} else {
    echo "0 results";
}
$conn->close();
//echo json_encode(array("question_data"=>$response)); fi androids
echo json_encode($response);


?>