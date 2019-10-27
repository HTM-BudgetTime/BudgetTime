<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="width = device-width, initial-scale = 1" name="viewport">

    <!-- Link to external CSS file -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../css/login_page.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</head>
<body class="body">

<form action="#" class="" method="post">
    <div class="container">

        <table class="table table-bordered table-hover" style="background-color: #eee;">
            <thead>
            <tr>
                <th scope="col">Category</th>
                <th scope="col">Type</th>
                <th scope="col">Hours</th>
            </tr>

            </thead>
            <tbody id="entriesRows">
            </tbody>
        </table>

    </div>

    <br><br>
    <button class="btn btn-default" id="newBlankRowHandler">New Row</button>
    <br/>
    <br/>
    <button class="btn btn-default" id="submitEntries">Submit Entries</button>
</form>

<script type="application/javascript">

    document.getElementById("newBlankRowHandler").addEventListener("click", function (e) {
        addRow("", "", 0);
        e.preventDefault();
    });

    document.getElementById("submitEntries").addEventListener("click", function (e) {
        submitEntries();
        e.preventDefault();
    });

    let entries = [];

    entries.push({category: "Work", description: "Working Hours", hours: 40});
    entries.push({category: "Work", description: "Commuting to/from work", hours: 5});
    entries.push({category: "Fun", description: "Cinema", hours: 3});
    entries.push({category: "Self Care", description: "Sleep", hours: 56});
    entries.push({category: "Self Care", description: "Washing / morning routine", hours: 5});


    let rowsBody = document.getElementById("entriesRows");
    entries.forEach(entry => {
        addRow(entry.category, entry.description, entry.hours);
    });


    function addRow(category, description, hours) {
        const newRow = rowsBody.insertRow(-1);
        newRow.innerHTML = '<td>\n' +
            '                    <select name="Categories" class="form-control">\n' +
            '                        <option value="Work">Work</option>\n' +
            '                        <option value="Education">Education</option>\n' +
            '                        <option value="Entertainment">Entertainment</option>\n' +
            '                        <option value="Sleep">Sleep</option>\n' +
            '                        <option value="Daily Errands">Daily Errands</option>\n' +
            '                    </select>\n' +
            '                </td>\n' +
            '                <td>\n' +
            '                    <input type="text" name="input1Type" id="input1Type" placeholder="Description" value="' + description + '"/>\n' +
            '                </td>\n' +
            '                <td>\n' +
            '                    <input type="number" name="input1" id="firstInput" value="' + hours + '">\n' +
            '                </td>';
    }


    function submitEntries() {

        fetch("doUpdateEntries", {
            credentials: "same-origin",
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(entries)
        })
            .then(value => {
                console.info("value");
            });

    }

</script>


</body>
</html>
