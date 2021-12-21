<?php
namespace US\model;

use US\vendor\Http\Model\Model;


class Stories extends Model{

	protected static $table = "Stories";
    protected static $columns = [
       
    ];
    protected static $id = "id";


  	public static function all()
    {

    	return self::database()->query("SELECT users.*,Stories.* FROM Stories INNER JOIN users ON users.id = Stories.userID WHERE Stories.userID = 2")->fetchAll();
    }
	
}