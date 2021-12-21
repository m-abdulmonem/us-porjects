<?php



if (! function_exists("request")){
    function request($key = null){
        if ($key != null)
            return $_POST[$key];
        else
            return$_POST;
    }
}