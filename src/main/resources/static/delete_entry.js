
async function deleteEntry(data, id){
    const response = await fetch(`delete/${encodeURIComponent(id)}`, {
        method: "DELETE"
    });

    for (let i = 0; i < data.length; i++){
        if (data[i].ID == id){
            data.splice(i, 1);
        }
    }

    window.location.reload();

}