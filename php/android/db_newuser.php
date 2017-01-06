<?php
 
$response = array();
 
if (isset($_POST['Nome']) && isset($_POST['NdeBI']) && isset($_POST['Morada']) && isset($_POST['email'])&& isset($_POST['Telemovel'])&& isset($_POST['genero'])&& isset($_POST['natadenascimento'])&& isset($_POST['utilizador_login_Npolicia'])&& ($_POST('utilizador_login_pass'))) {
 
    $nome = $_POST['Nome'];
    $nbi = $_POST['NdeBI'];
	$morada = $_POST['morada'];
	$email = $_POST['email'];
	$telemovel = $_POST['Telemovel'];
	$genero = $_POST['genero'];
	$data = $_POST['datadenascimento'];
	$Npolicia = $_POST['utilizador_login_Npolicia'];
	$pass = $_POST['utilizador_login_pass'];
 
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql inserting a new row
    $result = mysql_query("INSERT INTO utilizador_perfil(Nome, NdeBI, Morada, email, Telemovel, genero, datadenascimento, utilizador_login_Npolicia ) VALUES('$nome', '$nbi', '$morada','$email','$telemovel','$genero','$data','$Npolicia')(INSERT INTO utilizador_login(utilizador_login_pass) VALUES ('$pass')");
	
    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Conta cridada com sucesso.";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! algo correu mal, verifique os campos.";
 
        // echoing JSON response
        echo json_encode($response);
    }
	} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Preencha todos os campos";
 
    // echoing JSON response
    echo json_encode($response);
}
?>
