<?php


require('connectionDB.php');

 $username = $_GET["username"];
 
 
$sql = "SELECT * FROM `user` WHERE `username` LIKE '".$username."'";
//$sql = "SELECT * FROM question WHERE 1" ;
$result = $conn->query($sql);


$response=array(); 


if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {   
	//  `levelAng`, `levelFr`, `levelGer`, `levelEsp`
	$response[]=array("id"=>$row["id"],"username"=>$row["username"],"image"=>$row["image"] ,"scoreF"=>$row["scoreF"] 
	,"scoreAng"=>$row["scoreAng"] ,
	"scoreGerm"=>$row["scoreGerm"] ,
	"scoreEsp"=>$row["scoreEsp"],
	"levelAng"=>$row["levelAng"],
	"levelFr"=>$row["levelFr"],
	"levelGer"=>$row["levelGer"],
	"levelEsp"=>$row["levelEsp"]
	);
         
    }
}  
$conn->close();
echo json_encode($response);
?>