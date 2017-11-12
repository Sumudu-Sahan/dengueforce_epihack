<?php
	include_once('connection.php');

	$query = "SELECT 
	
	emergency_number_id, 
	emergency_number_name, 
	emergency_number_address,
	emergency_number_contact_number1,
	emergency_number_contact_number2,
	emergency_number_contact_number3

	FROM 
	
	emergency_number_details 
	
	WHERE 
	
	emergency_number_active = 1";

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