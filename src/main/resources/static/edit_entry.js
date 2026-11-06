async function editEntry(row, instruments){
    var form = document.querySelector("#form form");
    form.style.visibility = "visible";

    const instrument = instruments.find(item => Number(item.instrumentId) === Number(row.instrumentId));

    document.getElementById("instrument").value = instrument?.instrumentName ?? "";

    var id = row["id"];

    document.getElementById("date").value = row["date"];
    document.getElementById("duration").value = row["duration"];
    document.getElementById("scales").checked = row["scales"] === true;
    document.getElementById("exercises").checked = row["exercises"] === true;
    document.getElementById("pieces").checked = row["pieces"] === true;
    document.getElementById("description").value = row["description"] ?? "";
    
    form.onsubmit = async event => {
        event.preventDefault();
        form.style.visibility = "hidden";

        const payload = new URLSearchParams({
            date: document.getElementById("date").value,
            duration: document.getElementById("duration").value,
            instrument: document.getElementById("instrument").value,
            scales: document.getElementById("scales").checked ? true : false,
            exercises: document.getElementById("exercises").checked ? true : false,
            pieces: document.getElementById("pieces").checked ? true : false,
            description: document.getElementById("description").value
    });
    
    const response = await fetch(`/edit/${encodeURIComponent(id)}`, {
        method: "PUT",
        headers: {"Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"},
        body: payload
    });

    if (response.ok){
    window.location.assign("/home");}
};
};