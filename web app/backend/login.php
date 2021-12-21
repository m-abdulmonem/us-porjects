<?php

require_once "app/init.php";

use \US\model\Users;
$response = array();

if ($_SERVER["REQUEST_METHOD"] == "GET"){

    if (isset($_GET['user']) and isset($_GET['pass'])){
        $auth = $_GET["user"];
        $pass = $_GET["pass"];
        $response = array();
        $login = validateEmptyLogin($auth,$pass);
	    $loginColumn = loginColumn($auth);

        if (!$login['error']) {
            $user = Users::where($loginColumn,$auth);
            if ($user == null && $user->username != $auth && !hashCheck($pass,$user->password)){
                if ($user->username != $auth or $user->email != $auth or $user->phone != $auth)
                {
                    if ($loginColumn == "email") {
                        $response["error"] = "Email Is not Exists";    
                    } elseif ($loginColumn == "phone") {
                        $response["error"] = "Phone Is not Exists";
                    } else
                        $response["error"] = "Username Is not Exists";
                        
                    $response['errorStatus'] = true ;
                    $response['loginStatus'] = false;
                }
                else{
                    if (!hashCheck($pass,$user->password))
                    {
                        $response["error"] = "Password is not Matched";
                        $response['errorStatus'] = true;
                        $response['loginStatus'] = false;
                    }
                }
            } else
                $response['errorStatus'] = false ;
            $response['loginStatus'] = true;
            $response['id'] = $user['id'];
            $response['username'] = $user['username'];
            $response['name'] = $user['name'];
            $response['posts'] = $user['posts'];
            $response['followers'] = $user['followers'];
            $response['following'] = $user['following'];
            $response['date'] = $user['date'];
            $response['email'] = $user['email'];
            $response['phone'] = $user['phone'];
            $response['profileCover'] = $user['profileCover'];
            $response['grand'] = $user['grand'];
            $response['status'] = $user['status'];
            $response['ip'] = $user['ip'];
            $response['picture'] = $user['picture'];
            $response['location'] = $user['location'];

        } else{
            foreach ($login['error_message'] as $error){
                $response['error'] = $error;
            }
            $response['errorStatus'] = true ;
            $response['loginStatus'] = false;
        }

    }

} else{

}

echo json_encode($response);