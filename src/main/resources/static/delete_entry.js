
async function deleteEntry(id){
    const response = await fetch(`/delete/${encodeURIComponent(id)}`, {
        method: "DELETE"
    });

    if (response.ok){
        window.location.assign("/home");
    }
}

async function deleteInstrument(instrumentId, userId){
    const response = await fetch(`/delete/${encodeURIComponent(instrumentId)}/${encodeURIComponent(userId)}`, {
        method: "DELETE"
    });

    if (response.ok){
        window.location.assign("/settings");
    }
}