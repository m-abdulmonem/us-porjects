<?php


require_once "app/init.php";

use \US\model\Users;

use \US\model\Posts;

$posts = array();

$posts['posts'] = Posts::all();

echo json_encode($posts);