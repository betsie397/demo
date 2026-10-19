

function showTable(data) {
    const table = document.getElementById("table");
    const headers = ["Date", "Duration", "Instrument", "Scales", "Exercises/Studies", "Pieces", "Description", "Date Entered", "Edit", "Delete"];

    const headerRow = document.createElement("tr");
    headers.forEach(header => {
        const th = document.createElement("th")
        th.textContent = header;
        headerRow.appendChild(th);
    });
    table.appendChild(headerRow);

    for(let i = 0; i < data.length; i++){
        const tableRow = document.createElement("tr");

        headers.forEach(header => {
            const td = document.createElement("td");
            if (data[i][header] === "None") {
                td.textContent = "";
            } else if (header === "Scales" || header === "Exercises/Studies" || header === "Pieces") {
                td.textContent = "✔";
            } else if (header === "Edit") {
                const editButton = document.createElement("button");
                editButton.textContent = "Edit";
                editButton.addEventListener("click", () => {
                    editEntry();
                })
                td.appendChild(editButton);
            } else if (header === "Delete") {
                const deleteButton = document.createElement("button");
                deleteButton.textContent = "Delete";
                deleteButton.addEventListener("click", () => {
                    // Implement delete functionality here
                })
                td.appendChild(deleteButton)
            } else{
                td.textContent = data[i][header];
            }
            tableRow.appendChild(td)
        });

        table.appendChild(tableRow)
    };
    
}