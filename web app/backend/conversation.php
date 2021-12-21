<?php

require_once 'init.php';

use \US\model\Conversation;

$response = array();
if ($_SERVER["REQUEST_METHOD"] == "GET"){

    if (isset($_GET['id'])){

    	$response['conversation'] = Conversation::all($_GET['id']);

    }
}else
	$response['error'] = "invaild Reques";



echo json_encode($response);
