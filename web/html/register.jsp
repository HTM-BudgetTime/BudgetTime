<!Doctype html>
<html lang="en">

<head>
    <jsp:include page="includes/htmlHead.include.jsp"/>
    <title> Welcome </title>
</head>

<body class="body">

<!-- Header above navigation bar -->
<div class="jumbotron text-center" style="margin-bottom:0; background-image: url(../img/header-background.jpg) ; background-size: cover;  height: 200px">
</div>


<nav class="navbar navbar-dark navbar-inverse">
    <jsp:include page="includes/navigation.include.jsp"/>
</nav>


<br/>

<!-- Login Username and Password Boxes -->
<div class="container">
    <form action="/BudgetTime/main/doRegister" method="POST">
        <label for="Username">Username</label>
        <input id="username" name="username" placeholder="Enter Username..." type="text">
        <label for="usrpassword">Password</label>
        <input id="usrpassword" name="Password" placeholder="Enter Password..." type="password">
        <input type="submit" value="Create an account"> <br>
        <input checked name="catVdog" type="radio" value="dog"> Dog </br>
        <input name="catVdog" type="radio" value="cat"> Cat
    </form>
</div>
<br/>

<!-- Footer of the website -->
<footer class="py-4 bg-dark text-white-50" id="sticky-footer">
    <div class="container text-center">
        <small>Copyright &copy; BudgetTime</small>
        <!-- Social Media Icons in footer -->
    </div>
</footer>


<script>
    // Get the modal
    var modal = document.getElementById('id01');

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>

</body>
</html>
