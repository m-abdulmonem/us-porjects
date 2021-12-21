<?php
namespace US\vendor\Http\Mail;


// Import PHPMailer classes into the global namespace
// These must be at the top of your script, not inside a function
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

    public static function send($to,$body,$subject= null,$name = null){

        $error = "";

        try {

            self::serverSettings();
            //Recipients
            $name = $name== null ? "US Memeber" : $name;
            self::mail()->setFrom($to, 'US Team');
            self::mail()->addAddress(static::$to,$name);   
            self::mail()->addReplyTo('info@us.com', 'Information');
            self::mail()->addCC('cc@us.com');
            self::mail()->addBCC('bcc@us.com');

             $subject = $subject == null ? "US Team" ? $subject;

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
            echo "Message could not be sent. Mailer Error: {self::mail()->ErrorInfo}";
        }
        return $error;
    }

    private static function isView($body){
        if (is_file($body))
            return file_get_contents($body);
        else
            return $body;
    }
    private static function serverSettings(){
          //Server settings
        self::mail()->SMTPDebug = 2;                                       // Enable verbose debug output
        self::mail()->isSMTP();                                            // Set mailer to use SMTP
        self::mail()->Host       = 'smtp.gmail.com';  // Specify main and backup SMTP servers
        self::mail()->SMTPAuth   = true;                                   // Enable SMTP authentication
        self::mail()->Username   = 'abuabdojoker22@gmail.com';                     // SMTP username
        self::mail()->Password   = 'Arose413@321';                               // SMTP password
        self::mail()->SMTPSecure = 'tls';                                  // Enable TLS encryption, `ssl` also accepted
        self::mail()->Port       = 587;    
    }

    private static function mail(){
        return new PHPMailer(true);
    }

}