<?php
namespace US\model;
require_once APP_PATH . "/vendor/Http/Model.php";
use US\vendor\Http\Model\Model;

class Users extends Model
{

    protected static $table = "users";
    protected static $columns = [
        'name' => 'name',
        'email' =>'email',
        'username' =>"username",
        'email_verified_at' => "email_verified_at",
        'password' =>"password",
        'status' =>"status",
        'phone' =>"phone",
        'posts' => "posts",
        'followers' =>"followers",
        'following' => "following",
        'grand' => "grand",
        'profileCover' => "profileCover",
        'ip' => "ip",
        'macAddress' => "macAddress",
        'faceID' => "faceID",
        'loginAttempt' => "loginAttempt",
        'active' => "active",
        'location' => "location",
        'location_id' => "location_id",
        'role' => "role",
        'remember_token'=> "remember_token",
        'created_at' => "created_at",
        'updated_at' => "update_at",
        'email_verify_code' => "email_verify_code",
        'phone_verify_code' => "phone_verify_code",
    ];
    protected static $id = "id";


}