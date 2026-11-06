function colorSlider(id){
    const slider = document.querySelector("#hue");
    const button = document.createElement("button");
    button.type = "button";
    button.textContent = "Apply";

    slider.insertAdjacentElement("afterend", button);

    slider.addEventListener("input", () => {
        document.documentElement.style.setProperty("--hue", slider.value);
    });

    button.addEventListener("click", async () => {
        const body = new URLSearchParams({ color: slider.value });
        const response = await fetch(`/color/${encodeURIComponent(id)}`, {
            method: "PUT",
            headers: {"Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"},
            body
        });

        if(response.ok){
            window.location.assign("/settings");
        }
    })
}

function setColor(hue){
    document.documentElement.style.setProperty("--hue", hue);
}