async function addInstruments(){
    const newInstrument = prompt("Please enter the name of the instrument you would like to add:")
    if (newInstrument) {
        const response = await fetch('/instruments', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ instrument: newInstrument })
        });
        document.getElementById("instrument").innerHTML += `<option value="${newInstrument}">${newInstrument}</option>`;
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