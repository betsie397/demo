function colorSlider(){
    const slider = document.querySelector("#hue");

    slider.addEventListener("input", () => {
        document.documentElement.style.setProperty("--hue", slider.value);
    });
}