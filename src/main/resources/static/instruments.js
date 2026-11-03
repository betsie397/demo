import {useState} from "react";

function instrumentPicker(){
    const [instruments, setInstruments] = useState([]);

    async function addInstruments(){
        const newInstrument = prompt("Please enter the name of the instrument you would like to add:").trim();
        if (newInstrument) {
            const response = await fetch(`/instruments/${encodeURIComponent(newInstrument)}`, {
                method: 'POST',
            });
            setInstruments((current) => 
            current.includes(newInstrument) ? current : [...current, newInstrument])
        }
    }}


function showInstruments(options){
    for (let i = 0; i < options.length; i++){
        const add = document.createElement("option");
        add.value = options[i];
        add.textContent = options[i];
        document.getElementById("instrument").appendChild(add);
    };
}