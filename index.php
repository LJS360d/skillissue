<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Document</title>
</head>

<body>
    <form action="index.php" method="post">
        <input class="input-box" type="text" name="string" placeholder="Insert something here"><br>
        <input type="submit" name="submitstr">
    </form>
    <p>
        <?php
        $string = $_POST['string'] ?? '';
        $vowels = [
            "a" => 0,
            "e" => 0,
            "i" => 0,
            "o" => 0,
            "u" => 0,

        ];
        if(isset($_POST['submitstr'])){
            foreach (str_split($string, 1) as $letter) {
                $letter = strtolower($letter);
                if(in_array($letter,['a','e','i','o','u']))
                $vowels[$letter]+=1;
            }
    
            foreach ($vowels as $key => $value) {
                echo strtoupper($key)."\t:".$value."<br>";
            }
        }

        ?>
    </p>

</body>

</html>