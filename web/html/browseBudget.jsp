<!Doctype html>
<html lang="en">

<head>
    <jsp:include page="includes/htmlHead.include.jsp"/>
    <title>Browsing Budget</title>

    <link href="/BudgetTime/css/budget.css" rel="stylesheet" type="text/css">

</head>

<body class="body">

<!-- Header above navigation bar -->
<div class="jumbotron text-center" style="margin-bottom:0; background-image: url(../img/header-background.jpg); background-size: cover; height: 200px">
</div>


<nav class="navbar navbar-dark navbar-inverse">
    <jsp:include page="includes/navigation.include.jsp"/>
</nav>


<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h1>The Output</h1>

            <div id="entriesContainer" class="entriesContainer"></div>

        </div>
    </div>
</div>


<script type="application/javascript">

    let entries = [];

    entries.push({entry_id: 0, category: "Work",      description: "Working Hours",             hours_budgeted: 40, hours_logged: 0});
    entries.push({entry_id: 1, category: "Work",      description: "Commuting to/from work",    hours_budgeted: 5,  hours_logged: 0});
    entries.push({entry_id: 2, category: "Fun",       description: "Cinema",                    hours_budgeted: 3,  hours_logged: 0});
    entries.push({entry_id: 3, category: "Self Care", description: "Sleep",                     hours_budgeted: 56, hours_logged: 0});
    entries.push({entry_id: 4, category: "Self Care", description: "Washing / morning routine", hours_budgeted: 5,  hours_logged: 0});


    function addEntry(entry_id, category, description, hours_budgeted, hours_logged) {
        entries.push({entry_id: entry_id, category: category, description: description, hours_budgeted: hours_budgeted, hours_logged: hours_logged});
    }

    function removeEntry(entry_id) {
        entries = entries.filter(value => {
            return value.entry_id !== entry_id;
        });
    }

    function drawEntriesList(containerElem, entries) {
        entries.forEach(entry => {
            const newElem = entryToUlElem(entry);
            containerElem.appendChild(newElem);
        })
    }

    function entryToUlElem(entry) {
        const newElem = document.createElement("ul");
        // newElem.addClass("budget-category-row");
        newElem.innerHTML = "" +
            "<li class='budget-category-name'>" + entry.category + "</li>" +
            "<li class='budget-category-description'>" + entry.description + "</li>" +
            "<li class='budget-category-hours_budgeted'>" + entry.hours_budgeted + "</li>" +
            "";

        return newElem;
    }
    function entryToUlElem(entry) {
        const newElem = document.createElement("ul");
        // newElem.addClass("budget-category-row");
        newElem.innerHTML = "" +
            "<li class='budget-category-name'>" + entry.category + "</li>" +
            "<li class='budget-category-description'>" + entry.description + "</li>" +
            "<li class='budget-category-hours_budgeted'>" + entry.hours_budgeted + "</li>" +
            "";

        return newElem;
    }


    drawEntriesList(document.getElementById("entriesContainer"), entries);

</script>

<jsp:include page="includes/footer.include.jsp"/>

</body>
</html>
