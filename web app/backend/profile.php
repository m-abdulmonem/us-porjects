<?php

require_once 'init.php';

use \US\model\Users;

$response = array();
if (isset($_GET['user_id'])) {
	(int)$id = $_GET['user_id'];
	$user = Users::getByID($id);
	$response['username'] = $user->username;
	$response['name'] = $user->name;
	$response['errorStatus'] = false;
} else{
	$response['error'] = "Request is not valid";
	$response['errorStatus'] = true;
}

echo json_encode($response);

?>
