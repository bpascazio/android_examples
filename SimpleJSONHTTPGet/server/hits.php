<?php

header('Refresh: 5');

function returnFailure()
{
  die('death');
}

$username="belize";
$password="placencia";
$database="belize";

mysql_connect(localhost,$username,$password);

@mysql_select_db($database) or returnFailure();

$verb = $_GET["verbose"];

echo "<html><body>";

$result = mysql_query("SELECT * FROM hits order by dtime desc");
echo "<table>";
while($row = mysql_fetch_array($result))
{
  if($verb) {
    echo '<tr><td>'.$row[dtime].'</td><td>'.$row[info1].'</td><td>'.$row[info2].'</td><td>'.$row[info3].'</td><td>'.$row[info4].'</td></tr>';
//    echo "<br>";
  }
}
echo "</table>";
echo "</body></html>";

if (!$verb)echo 'belize weather hit count is '.mysql_num_rows($result);;


mysql_close();
?>
