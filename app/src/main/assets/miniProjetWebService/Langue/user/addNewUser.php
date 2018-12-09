<?php


require('connectionDB.php');

  $username = $_GET["username"];
  $urlImage = $_GET["image"];
  $idFG = $_GET["idFG"];
 $sql = "INSERT INTO `user`( `username`, `image`, `scoreF`
 ,`scoreAng`, `scoreGerm`, `scoreEsp`, `levelAng`, `levelFr`,
 `levelGer`, `levelEsp` ,`idFG`) VALUES ('". $username ."','".$urlImage."',

 0,0,0,0,1,1, 1,1
 ,'".$idFG."')" ;

 $result = $conn->query($sql);

$conn->close();
 
?>