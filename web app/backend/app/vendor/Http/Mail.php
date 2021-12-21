<?php
namespace US\vendor\Http\Mail;

use PHPMailer\PHPMailer\Exception;
use PHPMailer\PHPMailer\PHPMailer;


class Mail
{

    protected static $to;
    protected static $name;

    public static function  to($to){
        static::$to= $to;
        return new static;
    }
    public static function send($to,$subject= null,$body,$name = null){
        $error = "";
        try {
            $mail = new PHPMailer(false);
            //Server settings
            $mail->SMTPDebug = 2;                                       // Enable verbose debug output
            $mail->isSMTP();                                            // Set mailer to use SMTP
            $mail->Host       = 'smtp.gmail.com';  // Specify main and backup SMTP servers
            $mail->SMTPAuth   = true;                                   // Enable SMTP authentication
            $mail->Username   = 'abuabdojoker22@gmail.com';                     // SMTP username
            $mail->Password   = 'Arose413@321';                               // SMTP password
            $mail->SMTPSecure = 'tls';                                  // Enable TLS encryption, `ssl` also accepted
            $mail->Port       = 587;

            //Recipients
            $name = $name== null ? "US Memeber" : $name;
            $mail->setFrom("no-replay@us.com", 'US Team');
            $mail->addAddress($to,$name);
            $mail->addReplyTo('info@us.com', 'Information');
            $mail->addCC('cc@us.com');
            $mail->addBCC('bcc@us.com');

             $subject = $subject == null ? "US Team" : $subject;

            // Content
            $mail->isHTML(true);                                  // Set email format to HTML
            $mail->Subject = $subject;
            $mail->Body    = self::isView($body);
            $mail->AltBody = $body;

             if ($mail->send())
                 $error = "Message Send";
             else
                 $error = "Message Could not send";
        } catch (Exception $e) {
            $error = "Message could not be sent. Mailer Error: {$mail->ErrorInfo}<br/>";
        }
        return $error;
    }

    private static function isView($body){
        if (is_file($body))
            return file_get_contents($body);
        else
            return $body;
    }
}