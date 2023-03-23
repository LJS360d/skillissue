<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Inserimento dati temperature</title>
</head>

<body>
	<h1>Inserimento dati temperature</h1>
	<form action="raccolta-dati.php" method="GET">
		<label for="stazione">Stazione:</label>
		<select name="stazione" id="stazione">
			<?php
			$nomi_stazioni = array("Trento", "Milano", "Torino", "Firenze", "Bologna", "Roma", "Napoli", "Bari", "Messina");
			foreach ($nomi_stazioni as $stazione) {
				echo "<option value=\"$stazione\">$stazione</option>";
			}
			?>
		</select>
		<br>
		<label for="temp_max">Temperatura massima:</label> <br>
		<input type="number" name="temp_max" id="temp_max" required> <br>
		<label for="temp_min">Temperatura minima:</label> <br>
		<input type="number" name="temp_min" id="temp_min" required> <br>
		<input type="submit" value="Submit">
	</form>
	<br>
	<form action="riepilogo.php">
		<button type="submit">Vai alla pagina di Riepilogo</button>
	</form>
</body>
</html>