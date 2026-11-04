
async function addInstruments() {
    const newInstrument = prompt("Please enter the name of the instrument you would like to add:")?.trim();
    if (!newInstrument) return;

    const response = await fetch(`/instruments`, {
        method: 'POST',
        headers: { "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8" },
        body: new URLSearchParams({ instrumentName: newInstrument })
    });


    if (response.ok) {
        const select = document.getElementById("instrument");
        const option = new Option(newInstrument, newInstrument);
        select.add(option);
        select.value = newInstrument;
    }
}


function showInstruments(options){
    for (let i = 0; i < options.length; i++){
        const add = document.createElement("option");
        add.value = options[i];
        add.textContent = options[i];
        document.getElementById("instrument").appendChild(add);
    };
}