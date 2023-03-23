<?php
$zone = array(
    "Trento" => "Nord",
    "Milano" => "Nord",
    "Torino" => "Nord",
    "Firenze" => "Centro",
    "Bologna" => "Centro",
    "Roma" => "Centro",
    "Napoli" => "Sud",
    "Bari" => "Sud",
    "Messina" => "Sud"
);
$zone_temps =  array(
    "Nord-Max" => null,
    "Nord-Min" => null,
    "Centro-Max" => null,
    "Centro-Min" => null,
    "Sud-Max" => null,
    "Sud-Min" => null
);
$stazioni_temps = array();
$datadump = fopen('data-dump.log', 'r');
$data_str = fread($datadump, 8192);
echo $data_str === '' ? 'Non ci sono dati da analizzare :(' : 'Gathered Data: <br>';
$data = explode(';', $data_str);

foreach ($data as $index => $value) {
    if ($index % 4 === 0 && $index < sizeof($data) - 1) {
        $stazione = $data[$index];
        $maxTemp = $data[++$index];
        $minTemp = $data[++$index];
        $timestamp = $data[++$index];
        $zona = $zone[trim($stazione)];
        $stazione_max = trim($stazione . "-Max");
        $stazione_min = trim($stazione . "-Min");
        $stazioni_temps = $stazioni_temps + array(
            $stazione_max => null,
            $stazione_min => null
        );
        if ($maxTemp > $zone_temps[$zona . "-Max"]  || $zone_temps[$zona . "-Max"] == null) {
            $zone_temps[$zona . "-Max"] = $maxTemp;
        }
        if ($minTemp < $zone_temps[$zona . "-Min"] || $zone_temps[$zona . "-Min"] == null) {
            $zone_temps[$zona . "-Min"] = $minTemp;
        }
        if ($maxTemp > $stazioni_temps[$stazione_max] || $stazioni_temps[$stazione_max] == null) {
            $stazioni_temps[$stazione_max] =  $maxTemp;
        }
        if ($minTemp < $stazioni_temps[$stazione_min] || $stazioni_temps[$stazione_min] == null) {
            $stazioni_temps[$stazione_min] = $minTemp;
        }
    }
}
echo
'<h3>Nord</h3>
<p>Max:' . $zone_temps['Nord-Max'] . '</p>  
<p>Min:' . $zone_temps['Nord-Min'] . '</p> 
<h3>Centro</h3>
<p>Max:' . $zone_temps['Centro-Max'] . '</p>  
<p>Min:' . $zone_temps['Centro-Min'] . '</p> 
<h3>Sud</h3>
<p>Max:' . $zone_temps['Sud-Max'] . '</p>  
<p>Min:' . $zone_temps['Sud-Min'] . '</p>  
      ';
    foreach ($stazioni_temps as $key => $value) {
        echo "<p>".$key.':'.$value."</p>";
    }
