<?php

require_once 'init.php';

use \US\model\Stories;

$response = array();
$Stories = Stories::all();
$array = array();


foreach ($Stories as $story) {
	$array[] = $story['stories'] . ",";
}

var_dump($array);
// var_dump($array);


