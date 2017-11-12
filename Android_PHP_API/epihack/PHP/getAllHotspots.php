<?php
	include_once('connection.php');
	
	$userID = $_REQUEST['userID'];

	$query = "SELECT 
	
	subscribed_area_id,
	hotspot_category_name, 
	hotspot_name, 
	hotspot_address,
	hotspot_city,
	hotspot_state,
	hotspot_lat,
	hotspot_lng

	FROM 
	
	hotspot_details

	JOIN hotspot_category_details ON (hotspot_category_details.hotspot_category_id = hotspot_details.hotspot_type)
	
	JOIN subscribed_area_details ON (subscribed_area_details.subscribed_hp_id = hotspot_details.hotspot_id)
	
	WHERE 
	
	hotspot_active = 1 AND subscribed_user_id = $userID";
	
	$result = mysqli_query($connection, $query);

	while($rows = mysqli_fetch_array($result)){
		$ress[] = $rows;
	}

print json_encode(utf8ize($ress), JSON_UNESCAPED_SLASHES);

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