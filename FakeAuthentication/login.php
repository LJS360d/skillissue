<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<body>
    <p>Login</p>
    <form method="post">
    <input required type="text" name="username" placeholder="Username" ><br>
    <input required type="text" name="password" placeholder="Password"><br>
    <button type="submit">Submit</button>
    </form>
    <?php
    $users = explode(';',fread(fopen('users.txt','r'),filesize("users.txt")));
    $username = $_POST['username'] ?? $_GET['username'] ?? '';
    $password = $_POST['password'] ?? $_GET['password'] ?? '';
    $usernameKey = array_search($username,$users);

    if(isset($username)){
        if($usernameKey !== false){
            //Even index -> Username | Odd index -> Password
            if($users[$usernameKey+1]===$password){
                echo "<script> location.replace('page.php'); </script>";
                exit();
            }else{
                echo "Wrong Password ";
            }
        }else{
            echo "Wrong Username";
        }
    };
    
    ?>


</body>
</html>