function showForm(){
    var form = document.getElementById("form");
    form.style.visibility = "visible";
}

function hideForm(){
    var form = document.getElementById("form")
    form.style.visibility = "hidden";
}

function editEntry(data){
    var form = document.getElementById("form");
    form.style.visibility = "visible";
    //form.elements["date"].value = data["Date"];
    //form.elements["duration"].value = data["Duration"];
    //form.elements["instrument"].value = data["Instrument"];
    //form.elements["scales"].checked = data["Scales"] === "✔";
    //form.elements["exercises"].checked = data["Exercises/Studies"] === "✔";
    //form.elements["pieces"].checked = data["Pieces"] === "✔";
    //form.elements["description"].value = data["Description"];
}

