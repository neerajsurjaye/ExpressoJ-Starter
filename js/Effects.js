const cssHoverClasses = [
    "scrl-text-highlight-green",
    "scrl-text-highlight-red",
    "scrl-text-highlight-blue",
    "scrl-text-highlight-teal",
    "scrl-text-highlight-pink",
    "scrl-text-highlight-yellow",
];

/*Method generates scrolling text on a target element*/
function createScrollingTextBackground(targetElement, textArray, speed) {
    let effectsContainer = document.createElement("div");
    effectsContainer.classList.add("effect-cont");
    targetElement.appendChild(effectsContainer);

    for (let i = 0; i < 12; i++) {
        let rowElement = createRow();

        for (let i = 0; i < 40; i++) {
            let cellText =
                textArray[Math.floor(Math.random() * textArray.length)];

            let cellElem = createCell(cellText);
            rowElement.appendChild(cellElem);
        }

        console.log({ chileElemcount: rowElement.childElementCount });

        effectsContainer.appendChild(rowElement);
    }
}

function createRow() {
    let scrlAnimClasses = ["anim-scrl-row", "anim-scrl-row-reverse"];
    let animDurations = ["60s", "90s", "120s", "240s"];

    let rowElement = document.createElement("div");
    rowElement.classList.add("scrl-text-row");

    let scrollAnimClass = scrlAnimClasses[Math.floor(Math.random() * 2)];
    rowElement.classList.add(scrollAnimClass);
    rowElement.style.animationDuration =
        animDurations[Math.floor(Math.random() * animDurations.length)];
    console.log();

    return rowElement;
}

function createCell(textContent) {
    let cellElement = document.createElement("div");
    cellElement.classList.add("scrl-text-cell");

    let cellStyle =
        cssHoverClasses[Math.floor(Math.random() * cssHoverClasses.length)];
    cellElement.classList.add(cellStyle);
    cellElement.textContent = textContent;

    return cellElement;
}

export { createScrollingTextBackground };
