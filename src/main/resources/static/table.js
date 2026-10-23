

function showTable(data, id) {
    const table = document.getElementById("table");
    const headers = ["Date", "Duration", "Instrument", "Scales", "Exercises/Studies", "Pieces", "Description", "Date Entered", "Edit", "Delete"];
    var rowNo = 0;

    const headerRow = document.createElement("tr");
    headers.forEach(header => {
        const th = document.createElement("th")
        th.textContent = header;
        headerRow.appendChild(th);
    });
    table.appendChild(headerRow);

    for(const row of data){
        const tableRow = document.createElement("tr");
        rowNo ++;

        for(let j = 0; j < headers.length; j++){
            const header = headers[j];
            const value = row[header];
            const td = document.createElement("td");

            if (header === "Scales" || header === "Exercises/Studies" || header === "Pieces") {
                if (value === "true"){
                    td.textContent = "✔";
                } else{
                    td.textContent = "";
                }
            } else if (header === "Description") {
                td.textContent = value.replace(/"/g, "");
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
                    deleteEntry(data, row.ID);
                })
                td.appendChild(deleteButton)
            } else{
                td.textContent = value;
            }
            tableRow.appendChild(td)
        };

        table.appendChild(tableRow)
    };
    
}