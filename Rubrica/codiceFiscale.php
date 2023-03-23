<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        body{
            font-family: 'Comic Sans MS';
        }
    </style>
</head>

<body>
    <img src="https://external-preview.redd.it/2qxgN_b0DiuNVMHNk2iUrOCRXCzsbNosVaIjOTH2A40.jpg?auto=webp&s=216e1fc9a6e75e1f462992dc9beff85dbfb95418" 
    style="position: fixed; z-index:-1; display: block; right: 50vw;">
    <form method="post">
        <label for="nome">Inserisci Codice Fiscale</label> <br>
        <input type="text" name="cf" minlength="16" maxlength="16" style="width:30rem;">
        <button type="submit" name="submit">Submit</button>
    </form>
    <?php
    if (isset($_POST['submit'])) {
        $cf = strtoupper($_POST['cf']);
        echo isValidCF($cf) ? "<h2>Il codice è valido</h2>" : "<h2>Non è valido, il carattere di controllo dovrebbe essere ".calcControlCode($cf)."</h2>";
    }
    function isValidCF($cf)
    {
        $control_char = calcControlCode($cf);
        return (preg_match('/^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/', $cf)) && ($cf[15] == $control_char);
    }
    function calcControlCode($cf)
    {
        $evenMap = array(
            'A' => 0,
            'B' => 1,
            'C' => 2,
            'D' => 3,
            'E' => 4,
            'F' => 5,
            'G' => 6,
            'H' => 7,
            'I' => 8,
            'J' => 9,
            'K' => 10,
            'L' => 11,
            'M' => 12,
            'N' => 13,
            'O' => 14,
            'P' => 15,
            'Q' => 16,
            'R' => 17,
            'S' => 18,
            'T' => 19,
            'U' => 20,
            'V' => 21,
            'W' => 22,
            'X' => 23,
            'Y' => 24,
            'Z' => 25
        );
        $oddMap = array(
            'A' => 1,
            0 => 1,
            'B' => 0,
            1 => 0,
            'C' => 5,
            2 => 5,
            'D' => 7,
            3 => 7,
            'E' => 9,
            4 => 9,
            'F' => 13,
            5 => 13,
            'G' => 15,
            6 => 15,
            'H' => 17,
            7 => 17,
            'I' => 19,
            8 => 19,
            'J' => 21,
            9 => 21,
            'K' => 2,
            'L' => 4,
            'M' => 18,
            'N' => 20,
            'O' => 11,
            'P' => 3,
            'Q' => 6,
            'R' => 8,
            'S' => 12,
            'T' => 14,
            'U' => 16,
            'V' => 10,
            'W' => 22,
            'X' => 25,
            'Y' => 24,
            'Z' => 23
        );
        $even_sum = 0;
        $odd_sum = 0;
        //O(16) -> O(n)
        for ($i = 1; $i < 16; $i++) {
            $char = $cf[$i-1];
            if ($i % 2 == 0) {
                $even_sum += is_numeric($char) ? intval($char) : $evenMap[$char];
            } else {
                $odd_sum += $oddMap[$char];
            }
        }
        $control_char_val = ($odd_sum + $even_sum) % 26;
        $control_char = array_search($control_char_val, $evenMap);
        return $control_char;
    }
    ?>
</body>

</html>