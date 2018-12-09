<?php


require('connectionDB.php');

 $username = $_GET["username"];
 $idFG = $_GET["idFB"];
 
$sql =   "SELECT * FROM `user` where `username` ='".$username."' and idFG='".$idFG."' ";
 
$result = $conn->query($sql);


$response=array(); 


if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {   
	//  `levelAng`, `levelFr`, `levelGer`, `levelEsp`
	$response[]=array("id"=>$row["id"],"username"=>$row["username"],"image"=>$row["image"] 
	 
	,"scoreAng"=>$row["scoreAng"] 
	,"levelAng"=>$row["levelAng"]
	
	);
         
    }
}  
$conn->close();
echo json_encode($response);
?>