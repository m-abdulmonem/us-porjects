<?php
namespace US\model;

use US\vendor\Http\Model\Model;


class Posts extends Model{

	protected static $table = "posts";
    protected static $columns = [
       
    ];
    protected static $id = "id";


  	public static function all()
    {
    	return self::database()->query("SELECT posts.*, users.* from posts INNER JOIN users on users.id = posts.user_id")->fetchAll();
        // return self::database()->select("posts.*","users.*")->from("posts")->join("INNER JOIN users on users.id = posts.user_id")->fetchAll();
    }
	
}