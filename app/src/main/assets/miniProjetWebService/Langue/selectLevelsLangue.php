<?php


require('connectionDB.php');

 $id = $_GET["langue"];
$sql = "SELECT * FROM level WHERE langue ='".$id."'" ;
//$sql = "SELECT * FROM question WHERE 1" ;
$result = $conn->query($sql);


$response=array(); 


if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {   
	$response[]=array("id"=>$row["id"],"diffeculte"=>$row["diffeculte"],"NumLVL"=>$row["NumLVL"] ,"langue"=>$row["langue"] ,"nbrQuestion"=>$row["nbrQuestion"]);
         
    }
}  
$conn->close();
echo json_encode($response);
?>