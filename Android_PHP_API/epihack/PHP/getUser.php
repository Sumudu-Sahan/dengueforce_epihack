<?php

	$userName = $_REQUEST['userName'];
	$password = $_REQUEST['userPassword'];
	
	$latitude = $_REQUEST['latitude'];
	$longitude = $_REQUEST['longitude'];
	
	$token = $_REQUEST['token'];
	
	include_once('connection.php');

	$query = "SELECT 
	
	user_id,
	  user_role_id,
      user_first_name,
      user_last_name,
      user_email_address,
      user_gender,
      user_role_name

	FROM 
	
	user_details

	JOIN user_role_details ON (user_role_details.user_role_id = user_details.user_type) 
	
	WHERE 
	
	user_active = 1 AND user_name= '$userName' AND user_password = '$password'";

	$result = mysqli_query($connection, $query);
	
	if(mysqli_num_rows($result) > 0){
		$query1 = "UPDATE user_details SET user_device_token = '$token', user_lat= '$latitude', user_lng = '$longitude' WHERE user_active = 1 AND user_name= '$userName' AND user_password = '$password'";

	mysqli_query($connection, $query1);
	
	while($rows = mysqli_fetch_array($result)){
		$ress[] = $rows;
	}
	
	print json_encode(utf8ize($ress), JSON_UNESCAPED_SLASHES);
	}
	else{
		
	}

function utf8ize($d) {
    if (is_array($d)) {
        foreach ($d as $k => $v) {
            $d[$k] = utf8ize($v);
        }
    } else if (is_string ($d)) {
        return utf8_encode($d);
    }
    return $d;
}
        
?>