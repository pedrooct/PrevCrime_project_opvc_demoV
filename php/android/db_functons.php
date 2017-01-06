<?php
 
class db_functons {
 
    private $db;
 
    //put your code here
    // constructor
    require_once 'db_Connect.php';
    // connecting to database
    $this->db = new db_Connect();
    $this->db->connect();
	$db = mysql_select_db("opvc_prevcrime");
	$pass=$_POST["pass"];
	$npolicia=$_POST["npolicia"];
	if (!empty($_POST)) { 
	if (empty($_POST['npolicia']) || empty($_POST['pass'])) { 
	$response["success"] = 0;
	$response["message"] = "One or both of the fields are empty .";
	 die(json_encode($response));
      }
	 $query = " SELECT * FROM utilizadorlogin WHERE npolicia like '$npolicia'and pass like '$pass'";
	 $sql1=mysql_query($query);
	 $row = mysql_fetch_array($sql1);
	 if (!empty($row)) {
		 $response["success"] = 1; 
		 $response["message"] = "You have been sucessfully login"; 
		 die(json_encode($response));
	 }
	 else{ $response["success"] = 0; 
	 $response["message"] = "invalid username or password "; 
	 die(json_encode($response)); }
	 }
	else{
    $response["success"] = 0;
    $response["message"] = " One or both of the fields are empty "; 
    die(json_encode($response)); }
    mysql_close();
     //select * from utilizadorperfil inner join utilizadorlogin on utilizadorperfil.npolicia = utilizadorlogin.npolicia
  ?>


	
	