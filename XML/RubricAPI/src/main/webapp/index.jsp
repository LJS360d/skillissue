<html>
  <style>
    form {
      display: flex;
      flex-direction: column;
      border: 1px solid black;
      width: 50%;
      padding: 0.5rem;
      gap: 0.5rem;
      border-radius: 1rem;
    }
    form input,
    form input:focus {
      padding: 1rem;
      outline: none;
      border: none;
    }

    button[type="submit"],
    button[type="submit"]:focus {
      padding: 1rem;
      outline: none;
      border: none;
      transition: .3s all;
    }
    button[type="submit"]:hover{
        background-color: bisque;
        border-radius: 1rem;
    }
  </style>
  <body>
    <h2>Insert a new Record</h2>
    <form action="api" method="post" autocomplete="off">
      <input type="text" name="name" id="name" placeholder="Name" required />
      <input
        type="text"
        name="surname"
        id="surname"
        placeholder="Surname"
        required
      />
      <input
        type="number"
        name="prefix"
        id="prefix"
        placeholder="Prefix"
        required
      />
      <input
        type="text"
        name="number"
        id="number"
        placeholder="Number"
        pattern="[0-9]{10}"
        maxlength="10"
        minlength="10"
        required
      />
      <button type="submit">Send</button>
    </form>
  </body>
</html>
