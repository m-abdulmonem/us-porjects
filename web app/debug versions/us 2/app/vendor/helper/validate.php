<?php


if (! function_exists("validateEmptyLogin")){
    /**'
     * @param $auth
     * @param $pass
     * @return mixed
     */
    function validateEmptyLogin($auth, $pass){

        $errors = array();
        if (empty($auth) && empty($pass)) {
            $errors[] = "filed required";
        } elseif (empty($auth)){
            $errors[] = "enter a Username Or Email";
        } elseif(empty($pass)){
            $errors[] = "enter a password";
        }

        return !empty($errors) ? [ 'error' => true,'error_message' => $errors]: [ 'error' => false];
    }
}

if (! function_exists("validateEmptyRegister")){
    /**'
     * @param $username
     * @param $pass
     * @param $name
     * @param $user
     * @return mixed
     */
    function validateEmptyRegister($username, $pass,$name,$user){

        $errors = array();
        if (empty($username) && empty($pass) && empty($name) && empty($user)) {
            $errors[] = "filed required";
        } elseif (empty($auth)){
            $errors[] = "enter a Username Or Email";
        } elseif(empty($pass)){
            $errors[] = "enter a password";
        }elseif (empty($user))
            $errors[] = "mobile phone  or Email is Required";
        elseif (empty($name))
            $errors[] = "Full name is required";
        return !empty($errors) ? [ 'error' => true,'error_message' => $errors]: [ 'error' => false];
    }
}


if (! function_exists("loginColumn")){

    function loginColumn($data){

        if (is_numeric($data))
            $column = "phone";
        elseif (is_email($data))
            $column = "email";
        else{
            $column = "username";
        }
        return $column;
    }
}
if (! function_exists("registerColumn")){

    function registerColumn($data){

        if (is_numeric($data))
            $column = "phone";
        elseif (is_email($data))
            $column = "email";
        else
            $column = null;
        return $column;
    }
}
if (! function_exists("vaildateForget")) {
    function vaildateForget($user){
        if (empty($user))
            $error = "Plase enter you email or phone or username";
        require $error;
    }
}
if (! function_exists("is_email")) {

    function is_email($value)
    {
        return filter_var($value,FILTER_VALIDATE_EMAIL);
    }
}