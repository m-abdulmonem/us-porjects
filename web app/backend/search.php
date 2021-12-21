<?php
require_once 'init.php';

use \US\model\Users;
$response = array();
if ($_SERVER["REQUEST_METHOD"] == "POST") {
	if (isset($_POST['type'])) {
		if ($_POST['type'] == "users" && isset($_POST['search_key'])) {
			$response['users'] = Users::userSearch("username",$_POST["search_key"]);		
		}
	}else{
		$response['error'] = "invaild request";
	}

} else{
	$response['error'] = "invaild request";
}
echo json_encode($response);
