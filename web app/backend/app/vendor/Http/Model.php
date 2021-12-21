<?php



namespace US\vendor\Http\Model;





use US\Vendor\Database\Database;



class Model

{



    protected static $table;

    protected static $columns = [];

    protected static $id;







    public static function get(){

        return self::database()->select("*")->from(static::$table)->fetchAll();

    }


    public static function search($key,$value)
    {
        // return self::database()->select("*")->from(static::$table)->where($key . " LIKE  '%" . $value . "%'");
        return self::database()->select("*")->from(static::$table)->where("username LIKE  '%t%' ");
    } 

    public static function where($key,$value){

        return self::database()->select("*")->from(static::$table)->where($key . " = ?", $value)->fetch();

    }



    public static function getByID($id){

        return (object)  self::database()->select("*")->from(static::$table)->where(static::$id . " = ? ",$id)->fetch();

    }



    public static function create($params = []){

        return self::database()->data($params)->insert(static::$table)->getLastID();

    }



    public static function update($params = [],$id){

        self::database()->data($params)->where(static::$id . " = ?" ,$id)->update(static::$table);

    }

    





    public static function getColumn(){

        return (object) static::$columns;

    }





    /**

     * @return Database

     */

    public static function database(){

        return new Database();

    }





}