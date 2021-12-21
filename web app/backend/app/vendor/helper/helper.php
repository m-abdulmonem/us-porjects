<?php

if (! function_exists("hashMake")){
    function hashMake($password){
        return password_hash($password,PASSWORD_DEFAULT,['cost' => 12]);
    }
}
if (! function_exists("hashCheck")){
    function hashCheck ($pass,$oldPass){
        return password_verify($pass,$oldPass);
    }
}

if (! function_exists("now")){
    function now(){
        return date('Y-m-d H:i:s');
    }
}