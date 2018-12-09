<?php


require('connectionDB.php');

$sql = "SELECT * FROM `user` ORDER BY `scoreAng` DESC  LIMIT 5";



$result = $conn->query($sql);


$response=array(); 


if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {   
	$response[]=array("id"=>$row["id"],"username"=>$row["username"],"image"=>$row["image"] ,"scoreAng"=>$row["scoreAng"] );
         
    }
}  
$conn->close();
echo json_encode($response);
?>