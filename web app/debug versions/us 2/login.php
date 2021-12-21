<?php

require_once "app/init.php";

use \US\model\Users;
$response = [];
if ($_SERVER["REQUEST_METHOD"] == "POST"){

    if (isset($_GET['user']) and isset($_GET['pass'])){
        $auth = $_GET["user"];
        $pass = $_GET["pass"];
        $response = array();
        $login = validateEmptyLogin($auth,$pass);
        $loginColumn = loginColumn($auth);



        if (!$login['error']) {
            $user = Users::where($loginColumn,$auth);
            var_dump($user);

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
            $response['user'] = $user;

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