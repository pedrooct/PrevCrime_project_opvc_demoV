<?php
 
$response = array();
 
require_once __DIR__ . '/db_lig.php';
 

$db = new DB_CONNECT();
 
$result = mysql_query("SELECT * FROM locais") or die(mysql_error());
 

if (mysql_num_rows($result) > 0) {
   
    $response["locais"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        
        $locais = array();
        $locais["IdLocal"] = $row["IdLocal"];
        $locais["Longitude"] = $row["Longitude"];
        $locais["Altitude"] = $row["Altutide"];
		$locais["Latitude"] = $row["Latutide"];
		$locais["Descricao"] = $row["Descricao"];
 
        
        array_push($response["locais"], $product);
    }
    
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "Não há resultados.";
 
    // echo no users JSON
    echo json_encode($response);
}
?>
