<?php

require_once 'jwt_utils.php';

$is_jwt_valid = is_jwt_valid('eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEyMzQ1Njc4OTAiLCJuYW1lIjoiSm9obiBEb2UiLCJhZG1pbiI6dHJ1ZSwiZXhwIjoxNTgyNjE2MDA1fQ.umEYVDP_kZJGCI3tkU9dmq7CIumEU8Zvftc-klp-334');

echo nl2br("\n");

if($is_jwt_valid === TRUE) {
	echo 'JWT is valid';
} else {
	echo 'JWT is invalid';
}