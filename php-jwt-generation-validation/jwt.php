<?php

require_once 'jwt_utils.php';

$headers = array('alg'=>'HS256','typ'=>'JWT');
$payload = array('sub'=>'1234567890','name'=>'John Doe', 'admin'=>true, 'exp'=>(time() + 60));

$jwt = generate_jwt($headers, $payload);

echo $jwt;