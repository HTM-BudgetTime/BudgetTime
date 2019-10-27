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

<div class="container" style=" background-color: whitesmoke; padding: inherit; backface-visibility: hidden; padding-bottom: 30px;">
    <div class="col-md-12">
        <h1 style="text-align: center; font-family: sans-serif; font-size:5vw; padding-bottom: 10px; opacity: 1.0;"><b>Welcome to our Budget Timer</b></h1>
    </div>
    <div class="col-md-6">
        <p style="font-size: 20px; font-family: sans-serif; padding-top: 20px; padding-bottom: 5px; opacity: 1.0;">
            This website is used to represent a project from the 24 hour annual Hack the Midlands Hackathon. The Budget Timer is a web application used to
            organize your time throughout a weekly margin and calculate how much time your spending on various acivities in different categories. This app will
            keep you up to date with your weekly schedule as well as suggest any improvements and adjustments you can make to your routine to improve your
            wellbeing or achieve any goals you've set for yourself.</p>
    </div>

    <div class="col-md-1">
    </div>

    <div class="col-md-5">
        <div class="image-frame">
            <a href="#"><img alt="Image Time is Money" src="../img/home.jpg" style="height: 200px; width: 440px;"></img></a>
            <div class="image-caption" style="width: 440px;">
                <h2>Time is Money</h2>
            </div>
        </div>
        </br>
        <p style="font-size: 20px; font-family: sans-serif; padding-top: 0px; padding-bottom: 0px; opacity: 1.0;">
            If you've already created an account to our web application, <a href="login">please login here</a>.
        </p>
        <p style="font-size: 20px; font-family: sans-serif; padding-top: 0px; padding-bottom: 10px; opacity: 1.0;">
            If you don't have an account on our web application, please <a href="register">create one here</a>.
        </p>

    </div>
</div>

<br/>

<!-- Footer of the website -->
<footer class="py-4 bg-dark text-white-50" id="sticky-footer">
    <div class="container text-center">
        <small>Copyright &copy; BudgetTime</small>
        <!-- Social Media Icons in footer -->
    </div>
</footer>


<script type="text/javascript">
    $(document).ready(function () {

        //jQuery used for image text slides:
        $('.image-frame').hover(function () {
            $('.image-caption', this).slideToggle('slow');
        }, function () {
            $('.image-caption', this).slideToggle('slow');
        });

        //jQuery used to open links with class newtab in new windows:
        $('a.newtab').click(function () {
            window.open(this.href);
            return false;
        });
    });
</script>

</body>
</html>
