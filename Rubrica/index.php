<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <form method="post">
        <label>Inserisci Il nome o il numero della persona</label><br>
        <input type="text" name="formInput" id="numInpt">
        <button type="submit" name="submit">Submit</button>
    </form>
    <?php
    $map = [
        "Mario Rossi" => 123456789,
        "Dragos" => 3407583290,
        "Daniele" => 66666666,
    ];
    if (isset($_POST['submit'])) {

        $data = $_POST['formInput'];
        $returnData = returnAddress($map, $data);
        echo $returnData ? $returnData : "No address found";
    }
    function returnAddress($address_map, $data)
    {
        if (!is_numeric($data)) {
            return isset($address_map[$data]) ?
                $address_map[$data] : "No number found";
        } else {
            return array_search($data, $address_map);
        }
    }
    ?>
</body>

</html>