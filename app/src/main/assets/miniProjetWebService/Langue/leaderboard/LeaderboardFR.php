<?php


$servername = "localhost";
$username = "root";
$password = "";
$dbname = "mondy_lingo";
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$level = $_GET["levelFr"];
 
//$sql = "SELECT * FROM user where levelFr = ".$level;
 $sql = "SELECT * FROM `user` ORDER BY `scoreF` DESC  LIMIT 5"
$result = $conn->query($sql);


$response=array(); 


if ($result->num_rows > 0) {
     
    while($row = $result->fetch_assoc()) {   
	$response[]=array("id"=>$row["id"],"username"=>$row["username"],"image"=>$row["image"] ,"scoreF"=>$row["scoreF"] );
         
    }
}  else {
    echo "0 results";
} 
$conn->close();
echo json_encode($response);
?>