<?php
 

$response = array();
 
if (isset($_POST['Latitude']) && isset($_POST['Altitude']) && isset($_POST['Longitude'])) {
 
    $alt = $_POST['Altitude'];
    $long = $_POST['Longitude'];
    $des = $_POST['Descricao'];
	$lat = $_POST['Latitude'];
	$id = $_POST['id'];
	$idcpetd = $_POST['utilizador_perfil_id'];
 
    // include db connect class
    require_once __DIR__ . '/lig.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql inserting a new row
	$cpted = mysql_query("INSERT INTO cpetd(id, utilizador_perfil_id) values('$id','$idceptd')");
    $result = mysql_query("INSERT INTO locais(Altitude, Longitude, Latitude,Descricao) VALUES('$alt', '$long', '$lat','des')");
	
 
    
    if ($result) {111
        
        $response["success"] = 1;
        $response["message"] = "Rua inserida com sucesso.";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
        
        $response["success"] = 0;
        $response["message"] = "Oops! rua não inserida.";
 
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Tem de inserir os campos todos";
 
    // echoing JSON response
    echo json_encode($response);
}
?>
