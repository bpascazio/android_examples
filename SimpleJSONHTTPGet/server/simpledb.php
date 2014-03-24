<?php

$city = $_GET["city"];
$id = $_GET["id"];
function logHit()
{
//$query = "UPDATE `stats` SET `last_ap_update` =  NOW()  WHERE `member_id` = {$_SESSION['SESS_MEMBER_ID']}"; 
  $query = "INSERT into hits (dtime,info1,info2,info3,info4) Values(NOW(),'".$_GET["city"]."','".$_GET["id"]."','".$_SERVER['HTTP_USER_AGENT']."','success')";
  $queryresult = mysql_query($query);
}

function returnFailure()
{
  $query = "INSERT into hits (dtime,info1,info2,info3,info4) Values(NOW(),'".$_GET["city"]."','".$_GET["id"]."','".$_SERVER['HTTP_USER_AGENT']."','failed')";
  $queryresult = mysql_query($query);
  $array = array(
    'result' => 1
  );
  die(json_encode($array));
}



if ($city=="") returnFailure(); 

$username="belize";
$password="placencia";
$database="belize";
mysql_connect(localhost,$username,$password);

@mysql_select_db($database) or returnFailure();


$result = mysql_query("SELECT * FROM temps where city='".$city."'");

if($row = mysql_fetch_array($result))
{
  $array = array(
    'result' => 0,
    'city' => $row['city'],
    'temp' => $row['temp'],
    'info' => $row['info']
  );
  echo json_encode($array);
  logHit();
} else {
returnFailure();
}
//header("Content-Type: application/json");

mysql_close();
?>
