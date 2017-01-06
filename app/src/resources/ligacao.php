<?php
$ligacao = mysqli_connect('localhost','root',null); 
if(!$ligacao){  
	echo '<p>Erro, Falha na Ligação.</p>'; 
	exit;  
}
mysql_select_db("opvc_db");
$sql=mysql_query("select * from tiposderegisto");
while($row=mysql_fetch_assoc($sql))
	$output[]=$row;
print(json_encode($output));
mysql_close();

//mysqli_select_db($ligacao, 'opvc_db'); 

?>
