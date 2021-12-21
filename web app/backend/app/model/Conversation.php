<?php
namespace US\model;

use US\vendor\Http\Model\Model;


class Conversation extends Model{

	protected static $table = "conversation";
    protected static $columns = [
       
    ];
    protected static $id = "id";


  	public static function all($id)
    {
    	return self::database()->query("SELECT users.*,conversation_from.* FROM conversation_from INNER JOIN users ON users.id = conversation_from.user_from WHERE conversation_from.user = ". $id)->fetchAll();
    }
	
}