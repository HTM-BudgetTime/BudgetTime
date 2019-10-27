<!Doctype html>
<html lang="en">

<head>
    <jsp:include page="includes/htmlHead.include.jsp"/>
    <title>Browsing Budget</title>

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

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
            <h1>Budget Summary / Overview</h1>

            <table class="table table-bordered table-hover table-sm">
                <thead>
                <tr>
                    <th>Category</th>
                    <th>Description</th>
                    <th>Budgeted</th>
                    <th>Spent</th>
                    <th>Remaining</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody id="entriesContainer" class="entriesContainer">

                </tbody>
            </table>



        </div>
    </div>


    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <button id="saveButton">Save</button>
        </div>
    </div>
</div>


<script type="application/javascript">

    let entries = [];

    fetch("../api/budgetContent", {
            credentials: "same-origin",
            method: "GET",
            headers: {
                'Content-Type': 'application/json'
            }
        }
    )
        .then(value => { return value.json(); })
    .then(value => {
        console.info("value", value);
        for (let i = 0; i < value.length; i++) {
            const entry = value[i];
            entries.push(entry);
        }

        drawEntriesTableRow(document.getElementById("entriesContainer"), entries);
    });


    // entries.push({entry_id: 0, category: "Work", description: "Working Hours", hours_budgeted: 40, hours_logged: 0});
    // entries.push({entry_id: 1, category: "Work", description: "Commuting to/from work", hours_budgeted: 5, hours_logged: 0});
    // entries.push({entry_id: 2, category: "Fun", description: "Cinema", hours_budgeted: 3, hours_logged: 0});
    // entries.push({entry_id: 3, category: "Self Care", description: "Sleep", hours_budgeted: 56, hours_logged: 0});
    // entries.push({entry_id: 4, category: "Self Care", description: "Washing / morning routine", hours_budgeted: 5, hours_logged: 0});


    function addEntry(entry_id, category, description, hours_budgeted, hours_logged) {
        entries.push({entry_id: entry_id, category: category, description: description, hours_budgeted: hours_budgeted, hours_logged: hours_logged});
    }

    function removeEntry(entry_id) {
        entries = entries.filter(value => {
            return value.entry_id !== entry_id;
        });
    }

    function drawEntriesTableRow(containerElem, entries) {
        containerElem.innerHTML = "";
        entries.forEach(entry => {
            const newElem = entryToTrElem(entry);
            newElem.setAttribute("id", "row_" + entry.entry_id);
            containerElem.appendChild(newElem);
        })
    }

    function entryToTrElem(entry) {
        const newElem = document.createElement("tr");

        entry.hours_logged = entry.hours_logged || 0;
        newElem.dataset.entry_id = entry.entry_id;
        newElem.dataset.category_name = entry.category;
        newElem.dataset.description = entry.description;
        newElem.dataset.hours_budgeted = entry.hours_budgeted;
        newElem.dataset.hours_logged = entry.hours_logged;
        newElem.dataset.hours_remaining = (entry.hours_budgeted - entry.hours_logged);

        newElem.classList.add("budget-category-row");
        newElem.classList.add("form-group");

        newElem.innerHTML = "" +
            "<td class='budget-category-name align-middle'>" +
            "    <input type='text' class='form-control' value='" + entry.category + "'>" +
            "</td>" +
            "<td class='budget-category-description align-middle'>" +
            "    <input type='text' class='form-control' value='" + entry.description + "'>" +
            "</td>" +
            "<td class='budget-category-hours_budgeted align-middle'>" +
            "    <input type='number' class='form-control' value='" + entry.hours_budgeted + "'>" +
            "</td>" +
            "<td class='budget-category-hours_logged align-middle'>" +
            "    <input type='number' class='form-control' value='" + entry.hours_logged + "'>" +
            "</td>" +
            "<td class='budget-category-hours_remaining align-middle'>" + (entry.hours_budgeted - entry.hours_logged) + "</td>" +
            "<td class='budget-category-actions align-middle'>" +
            '                    <a class="save" id="row_save_' + entry.entry_id + '" data-toggle="tooltip" title="Save"><i class="material-icons">&#xE03B;</i></a>\n' +
            '                    <a class="edit" id="row_edit_' + entry.entry_id + '" title="Edit"><i class="material-icons">&#xE254;</i></a>\n' +
            '                    <a class="delete" id="row_delete_' + entry.entry_id + '" title="Delete"><i class="material-icons">&#xE872;</i></a>' +
            "</td>" +
            "";

        return newElem;
    }


    $("#entriesContainer").on('click', 'tr a.save', function (event) {
        const currentTarget = event.currentTarget;
        console.info('currentTarget', currentTarget);
        const row = $(currentTarget).closest('tr');
        console.info('row', row)
    });


    $('#saveButton').on('click', function () {
        for(let i=0; i<entries.length; i++) {
            let entry = entries[i];
            console.info('entry', entry);
            fetch("doUpdateEntry", {
                credentials: "same-origin",
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(entry)
            })
                .then(value => {
                    console.info("value");
                });
        }
    });

</script>

<jsp:include page="includes/footer.include.jsp"/>

</body>
</html>
