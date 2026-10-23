const fs = require('fs');

function deleteEntry(data, row){
    const id = row.replace(/\D/g, "").toInteger();

    for (let i = 0; i < data.length; i++){
        if (data[i].ID === id){
            data.splice(i, 1);
        }
    }
    console.log(data);
}