<?php
namespace US\Mail;
require 'vendor/phpmailer/phpmailer/src/Exception.php';
require 'vendor/PHPMailer/PHPMailer/src/PHPMailer.php';
require 'vendor/phpmailer/phpmailer/src/SMTP.php';


use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;



class Mail
{

    protected static $to;
    protected static $name;

    public static function  to($to){
//        if (is_array($to)){
//            foreach ($to as $person){
//                static::$to = $person;
//            }
//        }
        static::$to= $to;
        return new static;
    }

    public static function send($body,$to,$subject= null,$replyTo = "abuabdojoker22@gmail.com"){

        $error = "";

        try {
            self::serverSettings();
            //Recipients
            self::mail()->setFrom('abuabdojoker22@gmail.com', 'US Team');
            self::mail()->addAddress($to,"US Member");     // Add a recipient

            self::mail()->addReplyTo($replyTo, 'Reply To');

            // Content
            self::mail()->isHTML(true);                                  // Set email format to HTML
            self::mail()->Subject = $subject;
            self::mail()->Body    = self::isView($body);
            self::mail()->AltBody = $body;

             if (self::mail()->send())
                 $error = "Message Send";
             else
                 $error = "Message Could not send";
        } catch (Exception $e) {
            $error =  ['error'=> self::mail()->ErrorInfo,'error2'=>$e];
        }
        return $error;
    }

    public static function verifyEmail($body,$subject= null,$code= null){


        self::mail()->addReplyTo('info@example.com', 'Information');

            self::mail()->addCC('cc@example.com');
            self::mail()->addBCC('bcc@example.com');

            // Attachments
            self::mail()->addAttachment('/var/tmp/file.tar.gz');         // Add attachments
            self::mail()->addAttachment('/tmp/image.jpg', 'new.jpg');    // Optional name

    }

    private static function isView($body){
        if (is_file($body))
            return file_get_contents($body);
        else
            return $body;
    }
    private static function serverSettings(){
        //Server settings
        self::mail()->CharSet = "UTF-8";
        self::mail()->SMTPDebug = 0;                                         // Enable verbose debug output
        self::mail()->isSMTP();                                              // Set mailer to use SMTP
        self::mail()->Host       = 'smtp.mailtrap.io';  // Specify main and backup SMTP servers
        self::mail()->SMTPAuth   = true;                                     // Enable SMTP authentication
        self::mail()->SMTPAutoTLS = false;
        self::mail()->Username   = '29209c070a0f2c';                         // SMTP username
        self::mail()->Password   = 'fd4c3e916cbcde';                               // SMTP password
        self::mail()->SMTPSecure = '';                                       // Enable TLS encryption, `ssl` also accepted
        self::mail()->Port       = 2525;// TCP port to connect to

    }


    private static function mail(){
        return new PHPMailer(true);
    }

}