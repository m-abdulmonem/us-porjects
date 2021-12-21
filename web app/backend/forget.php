<?php
require_once "app/init.php";

use \US\model\Users;

$basic  = new \Nexmo\Client\Credentials\Basic("33122fe3", "QNUvQi8r9IDUfs73");
$client = new \Nexmo\Client($basic);

$response = array();;
if ($_SERVER["REQUEST_METHOD"] == "POST"){
	 if (isset($$_POST['user'])){
        $auth = $_POST["user"];
        $loginColumn = registerColumn($auth);

        if (!empty($auth)) {
        	$user = Users::where($loginColumn,$auth);
        	if (!empty($user)) {
        		if ($loginColumn == 'email'){
        			$response['message'] = US\vendor\Http\Mail\Mail::send($auth, "Reset Password", "Please Click  Reset  TO set New Password<a href='https://overloud-sponges.000webhostapp.com/public_html/rest.php?code=" . $user->email_verify_code . "'>Reset Password</a>");
        			$response['message'] = "Message Sent";
        			$response['restType'] = "mail";
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
                            $response['restType'] = "phone";
                        } else {
                            $response['message'] = "The message failed with status: " . $response['messages'][0]['status'] . "\n";
                        }
                        $response['message'] = "Message Sent";
                        $response['rest'] = true;
                    } catch (Exception $e) {
                            $response['message'] = "The message was not sent. Error: " . $e->getMessage() . "\n";
                    }
        		} else
        			$response['message'] = "Sorry please Enter Valid E-Mail or Phone";
        	} else
        		$response['message'] = "Sorry!, This Account Is Not Exists";
        } else
        	$response['message'] = "Sorry! Please enterE-Mail or Phone";
    } else
    	$response['message'] = "Sorry! Please enterE-Mail or Phone";
} else
	$response['message'] = "Sorry! Please enterE-Mail or Phone";


	echo json_encode($response);