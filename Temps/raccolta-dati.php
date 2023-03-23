<?php
	session_start();
	$stazione = $_GET["stazione"];
	$temp_max = $_GET["temp_max"];
	$temp_min = $_GET["temp_min"];
	$timestamp = time();
	$data = array("stazione" => $stazione, "temp_max" => $temp_max, "temp_min" => $temp_min, "timestamp" => $timestamp);
	array_push($_SESSION,$data);
	$datadump = fopen('data-dump.log','a+');
	fwrite($datadump,implode(';',$data).";\n");
	header("Location: form.php");
?>
