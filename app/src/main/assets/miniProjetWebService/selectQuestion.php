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
$var = 0;
$type = $_GET["type"];
$level = $_GET["level"];
$langue = $_GET["langue"];
$sql = "SELECT * FROM question WHERE level ='".$level."' AND langue = '".$langue."' AND questionTest =".$var." AND type = ".$type ;
$result = $conn->query($sql);


$response = array(); 


if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
	$response []= array("id"=>$row["id"],"level"=>$row["level"],"contenu"=>$row["contenu"],"vocal"=>$row["vocal"],"image"=>$row["image"],"reponse"=>$row["reponse"],"langue"=>$row["langue"],"questionTest"=>$row["questionTest"]);
        //echo "Question: " . $row["contenu"]. " - level: " . $row["level"]. " " . "<br>";
    }
} else {
    echo "0 results";
}
$conn->close();
//echo json_encode(array("question_data"=>$response)); fi androids
echo json_encode($response);


?>