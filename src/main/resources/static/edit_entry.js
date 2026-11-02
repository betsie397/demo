async function editEntry(row){
    var form = document.getElementById("form");
    form.style.visibility = "visible";

    var id = row["ID"];

    document.getElementById("date").value = row["Date"];
    document.getElementById("duration").value = row["Duration"];
    document.getElementById("instrument").value = row["Instrument"];
    document.getElementById("scales").checked = row["Scales"] === "true";
    document.getElementById("exercises").checked = row["Exercises/Studies"] === "true";
    document.getElementById("pieces").checked = row["Pieces"] === "true";
    document.getElementById("description").value = row["Description"] ?? "";
    
    document.querySelector("#form form").addEventListener("submit", async event => {
        event.preventDefault();

        const payload = {
        date: document.getElementById("date").value,
        duration: document.getElementById("duration").value,
        instrument: document.getElementById("instrument").value,
        scales: document.getElementById("scales").checked ? "true" : "",
        exercises: document.getElementById("exercises").checked ? "true" : "",
        pieces: document.getElementById("pieces").checked ? "true" : "",
        description: document.getElementById("description").value
    };
    
    const response = await fetch(`/edit/${encodeURIComponent(id)}`, {
        method: "PUT",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(payload)
    });

    window.location.reload();
    });
};