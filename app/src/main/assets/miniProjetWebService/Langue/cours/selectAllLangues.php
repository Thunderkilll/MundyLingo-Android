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
 
$sql = "SELECT * FROM langue WHERE 1" ;
$result = $conn->query($sql);


$response = array(); 


if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
	$response []= array("id"=>$row["id"],"title"=>$row["title"],"imgUrl"=>$row["imgUrl"],"score"=>$row["score"]);
      
    }
} else {
    echo "0 results";
}
$conn->close();
//echo json_encode(array("question_data"=>$response)); 
 echo json_encode($response);


?>