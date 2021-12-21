<?php

if (!defined("DS"))
    define("DS",DIRECTORY_SEPARATOR);

require_once "config.php";
require_once "vendor/database/DBConfig.php";
require_once "vendor/database/Database.php";
require_once "vendor/Http/Mail.php";
require_once "model/Users.php";
require_once "vendor/helper/request.php";
require_once "vendor/helper/validate.php";
require_once "vendor/helper/helper.php";
realpath( '../vendor/phpmailer/phpmailer/src/Exception.php');
realpath('../vendor/phpmailer/phpmailer/src/PHPMailer.php') ;
realpath('../vendor/phpmailer/phpmailer/src/SMTP.php');
realpath("../vendor/autoload.php");



$database = new \US\Vendor\Database\Database();

