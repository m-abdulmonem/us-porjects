<?php
namespace US\vendor\Http\Model;


class Autoload
{

    public static function Autoload($className)
    {
        $className = str_replace("US","",$className);
        $className = str_replace("\\",DIRECTORY_SEPARATOR,$className);
        if (file_exists(APP_PATH .$className))
            require_once APP_PATH . $className ."php";

    }

}

spl_autoload_register(__NAMESPACE__. '\Autoload::Autoload');