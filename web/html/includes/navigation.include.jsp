<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-fluid">

    <!-- Logo -->
    <div class="navbar-header">
        <button class="navbar-toggle" data-target="#mainNavBar" data-toggle="collapse" type="button">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="index">BudgetTime</a>
    </div>

    <!-- Menu Items -->
    <div class="collapse navbar-collapse" id="mainNavBar" style="color: white;">
        <ul class="nav navbar-nav">
            <c:choose>
                <c:when test="${sessionScope.isLoggedIn}">
                    <li class="active"><a href="index">Home</a></li>
                    <li><a href="userChoice">User Choice</a></li>
                    <li><a href="userInput">User Input</a></li>
                    <li><a href="userOutput">User Output</a></li>
                    <li><a href="doLogout">Logout</a></li>
                </c:when>
                <c:otherwise>
                    <li class="active"><a href="index">Home</a></li>
                    <li><a href="login">Login Page</a></li>
                    <li><a href="register">Create an Account</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>
