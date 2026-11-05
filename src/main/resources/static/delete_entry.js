
async function deleteEntry(id){
    const response = await fetch(`/delete/${encodeURIComponent(id)}`, {
        method: "DELETE"
    });

    if (response.ok){
        window.location.assign("/home");
    }
}