

function showTable(data, instruments) {
    const table = document.getElementById("table");
    const headers = [["date", "Date"], ["duration", "Duration"], ["instrumentId", "Instrument"], ["scales", "Scales"], ["exercises", "Exercises"], ["pieces", "Pieces"], ["description", "Description"], ["dateEntered", "Date Entered"], ["edit", "Edit"], ["delete", "Delete"]];

    const headerRow = document.createElement("tr");
    headers.forEach(header => {
        const th = document.createElement("th")
        th.textContent = header[1];
        headerRow.appendChild(th);
    });
    table.appendChild(headerRow);
 
    for (const row of data){
        const tr = document.createElement("tr");
        for (const [key] of headers){
            if ([key] == "edit"){
                const editTd = document.createElement("td");
                const editButton = document.createElement("button");
                editButton.textContent = "Edit";
                editButton.addEventListener("click", event => {
                    event.preventDefault();
                    editEntry(row, instruments);
                })
                editTd.appendChild(editButton);
                tr.appendChild(editTd);

            } else if ([key] == "delete"){
                const deleteTd = document.createElement("td");
                const deleteButton = document.createElement("button");
                deleteButton.textContent = "Delete";
                deleteButton.addEventListener("click", event  => {
                    event.preventDefault();
                    event.stopPropagation();
                    deleteEntry(row.id);
                })
                deleteTd.appendChild(deleteButton);
                tr.appendChild(deleteTd);

            } else if ([key] == "instrumentId"){
                const instrumentTd = document.createElement("td");
                const instrument = instruments.find(item => Number(item.instrumentId) == Number(row.instrumentId));
                instrumentTd.textContent = instrument?.name ?? instrument?.instrumentName ?? "";
                tr.appendChild(instrumentTd);
 
            } else if ([key] == "scales" || [key] == "exercises" || [key] == "pieces"){
                const boolTd = document.createElement("td");
                if (row[key] == true) { boolTd.textContent = "✔"; }
                else {boolTd.textContent = ""; }
                tr.appendChild(boolTd);

            } else {
            const td = document.createElement("td");
            td.textContent = row[key] ?? "";
            tr.appendChild(td);
            }
        }

        table.appendChild(tr);
    }
    
}

function instrumentTable(user, instruments) {
    const table = document.getElementById("table");
    const headers = ["Instrument", "Delete"];

    const headerRow = document.createElement("tr");
    headers.forEach(header => {
        const th = document.createElement("th")
        th.textContent = header;
        headerRow.appendChild(th);
    });
    table.appendChild(headerRow);
 
    for (const row of user){
        const tr = document.createElement("tr");

        const instrumentTd = document.createElement("td");
        const instrument = instruments.find(item => Number(item.instrumentId) == Number(row.instrumentId));
        instrumentTd.textContent = instrument?.name ?? instrument?.instrumentName ?? "";
        tr.appendChild(instrumentTd);

        const deleteTd = document.createElement("td");
        const deleteButton = document.createElement("button");
        deleteButton.textContent = "Delete";
        deleteButton.addEventListener("click", event  => {
            deleteInstrument(row.instrumentId, row.userId);
        })
        deleteTd.appendChild(deleteButton);
        tr.appendChild(deleteTd);
        
        table.appendChild(tr);
}}