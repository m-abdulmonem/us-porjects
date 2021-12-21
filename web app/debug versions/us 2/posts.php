<?php
require_once 'init.php';

use \US\model\Posts;
use \US\model\Users;

$post = array();
$post['posts']= Posts::get();
// $posts = Posts::get();
// foreach ($posts as $loop) {
// 	$postLoop = array();
// 	$postLoop['image'] = $loop['image'];
// 	$postLoop['content'] = $loop['content'];
// 	$postLoop['react'] = $loop['react'];
// 	$postLoop['location'] = $loop['location'];
// 	$postLoop['link'] = $loop['link'];
// 	$postLoop['user_id'] = $loop['user_id'];
// 	$postLoop['created_at'] = $loop['created_at'];
// 	$postLoop['comments'] = $loop['comments'];
// 	$postLoop['saved'] = $loop['saved'];
// 	$user = Users::getByID($loop['user_id']);
// 	$postLoop['user']= $user;
// 	$postArray['posts'] = $postLoop;
// 	array_push($post, $postArray); 
// }

echo json_encode($post);