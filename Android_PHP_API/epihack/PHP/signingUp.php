<?php

require_once("connection.php");

$firstName = $_POST['firstName'];
$lastName = $_POST['lastName'];
$userName = $_POST['userName'];
$password = $_POST['password'];
$email = $_POST['email'];

$address = $_POST['address'];
$city = $_POST['city'];
$state = $_POST['state'];

$contactNumber = $_POST['contactNumber'];
$mobileNumber = $_POST['mobileNumber'];

$token = $_POST['token'];
$latitude = $_POST['latitude'];
$longitude = $_POST['longitude'];

$query1 = "INSERT INTO 

user_details 

(user_first_name, 
user_last_name, 

user_name, 
user_password, 

user_email_address,

user_address,
user_city,
user_state,

user_lat,
user_lng,

user_contact_number,
user_mobile_number,

user_device_token) 

VALUES
 (
 '$firstName',
 '$lastName', 
 '$userName',
 '$password',
 
 '$email',

 '$address', '$city', '$state',

 '$latitude',
 '$longitude',
 
 '$contactNumber',
 '$mobileNumber',
 
 '$token')";
  

  mysqli_query($connection, $query1);
  mysqli_close($connection);

?>