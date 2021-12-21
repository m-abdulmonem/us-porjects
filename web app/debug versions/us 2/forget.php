<?php
require_once "app/init.php";

use \US\model\Users;

$basic  = new \Nexmo\Client\Credentials\Basic("33122fe3", "QNUvQi8r9IDUfs73");
$client = new \Nexmo\Client($basic);
$response = array();;
if ($_SERVER["REQUEST_METHOD"] == "POST"){
	 if (isset($$_POST['user'])){
        $auth = $_POST["user"];
        $forget = vaildateForget($auth);
        $loginColumn = registerColumn($auth);

        if (!empty($auth)) {
        	$user = Users::where($loginColumn,$auth);
        	if (!empty($user)) {
        		if ($loginColumn == 'email'){
        			mail($auth, "Reset Password", "Plase Click rest To Reset Password <a href='https://overloud-sponges.000webhostapp.com/public_html/rest.php?code=" . $user->email_verify_code . "'>Reset</a>");
        			$response['message'] = "Message Sent";
        			$response['rest'] = true;
        		} elseif ($loginColumn == "phone") {
        			try {
                        $message = $client->message()->send([
                            'to'   => $auth,
                            'from' => 'US',
                            'text' => 'To rest password Code ' . $user->phone_verify_code . ' US Team'
                        ]);
                         $response = $message->getResponseData();
                        if($response['messages'][0]['status'] == 0) {
                            $response['message'] = "The message was sent successfully\n";
                        } else {
                            $response['message'] = "The message failed with status: " . $response['messages'][0]['status'] . "\n";
                        }
                        $response['message'] = "Message Sent";
                        $response['rest'] = true;
                    } catch (Exception $e) {
                            $response['message'] = "The message was not sent. Error: " . $e->getMessage() . "\n";
                    }
        		} else
        			$response['message'] = "Sorry Plase Enter vaild E-Mail or Phone";
        	} else
        		$response['message'] = "Sorry!, This Account Is Not Exsits";
        } else
        	$response['message'] = "Sorry! Please enterE-Mail or Phone";
    } else
    	$response['message'] = "Sorry! Please enterE-Mail or Phone";
} else
	$response['message'] = "Sorry! Please enterE-Mail or Phone";


	echo json_encode($response);