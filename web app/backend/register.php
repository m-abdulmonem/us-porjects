<?php

use US\model\Users;

require_once "app/init.php";

$response = array();

if ($_SERVER["REQUEST_METHOD"] == "POST"){

    $username = $_POST["username"];

    $user = $_POST["user"]; //Auth Type phone or email

    $name = $_POST["name"]; //full name

    $pass = $_POST["pass"]; //password

    $ip = "" ;#$_POST["ip"];

    $mac_address = "";#$_POST["mac_address"];

    $location = "";#$_POST["location"];

    $location_id = 0211546156;# $_POST["location_id"];


    $email_verfiy_code = uniqid(md5(rand(0,1000)));

    $body = "Please Verify Your Account, Click in this link, <a href=https://overloud-sponges.000webhostapp.com/public_html/verifiy.php?code='. $email_verfiy_code .'>Click Here</a>";



    $column = registerColumn($user);

    $register = validateEmptyRegister($username, $pass, $name, $user);



    $isUserExists = Users::where("username",$username); //DB::table("users")->where("username", $username)->orWhere("email", $user)->orWhere("phone",$user)->first();



    if ($isUserExists != null) {

        if ($isUserExists['username'] == $username)

            $response['error'] = "username is exists";

        else {

            if ($isUserExists['phone'] == $user)

                $response['error'] = "Phone is exists ";

            if ($isUserExists['email'] == $user)

                $response['error'] = "Email is exists";
        }

        if ((($isUserExists['username'] == $username) or ($isUserExists['phone'] == $user) or ($isUserExists['email'] == $user)) && hashCheck($pass,$isUserExists['password'])){

            $response['user'] = $isUserExists;

            $response['loginStatus'] = true;

        }

        if ($column == null) {
            $column = "email";
        }

    } else{

        if ($register['error']){

            $insertUser = Users::create([

                'name' => $name,

                $column => $user,

                'username' => $username,

                'password' => hashMake($pass),

                'ip' => $ip,

                'macAddress' => $mac_address,

                'location' => $location,

                'location_id' => $location_id,

                'created_at' => now(),

                'email_verify_code' => $email_verfiy_code,

                'phone_verify_code' => uniqid(rand(0,5000)),

            ]);



            $response['insertSuccess'] = true;

            $response['message'] = "User inserted";

            $response['user'] = Users::getByID($insertUser);

            $response['userID'] = $insertUser;

            if ($insertUser) {



                if ($column == "email") {

                    $message = mail($user, "Verify Email Address From Mail ", $body);

                    // $response['message'] =  \US\vendor\Http\Mail\Mail::to($user)->send($body);
                    if ($message) {
                        $response['sendMessage'] = true;
                        $response['messageType'] = "email";
                    } else
                        $response['error'] = "Message Could Not Be Sent . Error";
                  

                }

                elseif($column == "phone"){

//                Nexmo::message()->send([

//                    'to'   => 201099647084,

//                    'from' => 'US Team',

//                    'text' => 'To verify Your account enter this code '

//                ]);

                    $response['sendMessage'] = true;

                    $response['messageType'] = "phone";

                }

            }

            else

                $response['insertSuccess'] = false;

        } else

            $response['error'] = $register['error_message'];



    }



} else

{

    $response['error'] = "invalid request";

}

echo json_encode($response);