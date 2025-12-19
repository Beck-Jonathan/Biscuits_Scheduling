$(document).ready(function () {

    /* ===============================
       Core element references
       =============================== */

    const colorTiles = document.querySelectorAll('.color-tile');
    const palettePreview = document.getElementById('palettePreview');
    const randomizeBtn = document.getElementById('randomizeBtn');

    const picker = document.getElementById('colorPicker');
    const slCanvas = document.getElementById('slCanvas');
    const hueSlider = document.getElementById('hue');
    const ctx = slCanvas.getContext('2d');

    let activeTile = null;
    let hue = 0;

    /* ===============================
       Initialize tiles from hidden inputs
       =============================== */

    colorTiles.forEach(tile => {
        const input = document.getElementById(tile.dataset.input);

        if (!input.value) {
            input.value = '#555555';
        }
        tile.style.background = input.value;
    });

    /* ===============================
       Canvas setup
       =============================== */

    slCanvas.width = 220;
    slCanvas.height = 150;

    function drawSL() {
        ctx.clearRect(0, 0, slCanvas.width, slCanvas.height);

        // Saturation
        const satGrad = ctx.createLinearGradient(0, 0, slCanvas.width, 0);
        satGrad.addColorStop(0, `hsl(${hue}, 0%, 50%)`);
        satGrad.addColorStop(1, `hsl(${hue}, 100%, 50%)`);
        ctx.fillStyle = satGrad;
        ctx.fillRect(0, 0, slCanvas.width, slCanvas.height);

        // Lightness
        const lightGrad = ctx.createLinearGradient(0, 0, 0, slCanvas.height);
        lightGrad.addColorStop(0, '#fff');
        lightGrad.addColorStop(0.5, 'transparent');
        lightGrad.addColorStop(1, '#000');
        ctx.fillStyle = lightGrad;
        ctx.fillRect(0, 0, slCanvas.width, slCanvas.height);
    }

    function setColorFromCanvas(x, y) {
        if (!activeTile) return;

        const s = Math.round((x / slCanvas.width) * 100);
        const l = Math.round(100 - (y / slCanvas.height) * 100);

        const rgb = hslToRgb(hue / 360, s / 100, l / 100);
        const hex = rgbToHex(...rgb);

        activeTile.style.background = hex;
        document.getElementById(activeTile.dataset.input).value = hex;

        updatePalettePreview();
    }

    slCanvas.addEventListener('mousedown', e => {
        const r = slCanvas.getBoundingClientRect();
        setColorFromCanvas(e.clientX - r.left, e.clientY - r.top);
    });

    hueSlider.addEventListener('input', e => {
        hue = Number(e.target.value);
        drawSL();
    });

    /* ===============================
       Tile click → open picker
       =============================== */

    colorTiles.forEach(tile => {
        tile.addEventListener('click', e => {
            activeTile = tile;

            picker.style.display = 'block';
            picker.style.left = `${e.pageX + 8}px`;
            picker.style.top = `${e.pageY + 8}px`;

            drawSL();
        });
    });

    document.addEventListener('click', e => {
        if (!picker.contains(e.target) && !e.target.classList.contains('color-tile')) {
            picker.style.display = 'none';
        }
    });

    /* ===============================
       Palette preview
       =============================== */

    function updatePalettePreview() {
        const colors = Array.from(colorTiles).map(tile => {
            const input = document.getElementById(tile.dataset.input);
            return input.value || '#000000';
        });

        palettePreview.style.background =
            `linear-gradient(to right, ${colors.join(',')})`;
    }

    /* ===============================
       Randomize palette
       =============================== */

    function randomColor() {
        return '#' + Math.floor(Math.random() * 16777215)
            .toString(16)
            .padStart(6, '0');
    }

    randomizeBtn.addEventListener('click', () => {
        colorTiles.forEach(tile => {
            const input = document.getElementById(tile.dataset.input);
            const c = randomColor();

            input.value = c;
            tile.style.background = c;
        });

        updatePalettePreview();
    });

    /* ===============================
       Helpers
       =============================== */

    function hslToRgb(h, s, l) {
        let r, g, b;

        if (s === 0) {
            r = g = b = l;
        } else {
            const hue2rgb = (p, q, t) => {
                if (t < 0) t += 1;
                if (t > 1) t -= 1;
                if (t < 1 / 6) return p + (q - p) * 6 * t;
                if (t < 1 / 2) return q;
                if (t < 2 / 3) return p + (q - p) * (2 / 3 - t) * 6;
                return p;
            };

            const q = l < 0.5 ? l * (1 + s) : l + s - l * s;
            const p = 2 * l - q;

            r = hue2rgb(p, q, h + 1 / 3);
            g = hue2rgb(p, q, h);
            b = hue2rgb(p, q, h - 1 / 3);
        }

        return [
            Math.round(r * 255),
            Math.round(g * 255),
            Math.round(b * 255)
        ];
    }

    function rgbToHex(r, g, b) {
        return "#" + [r, g, b]
            .map(x => x.toString(16).padStart(2, '0'))
            .join('');
    }

    /* ===============================
       Init
       =============================== */

    updatePalettePreview();

    /* ===============================
       LineNo validation (unchanged logic)
       =============================== */

    const submitbutton = document.getElementById("submitButton");
    submitbutton.disabled = true;

    let LineNo_error = 0;

    const LineNo_input = document.getElementById("inputpaletteLineNo");

    LineNo_input.addEventListener('blur', function () {
        LineNo_input.value = LineNo_input.value.trim();

        if (LineNo_input.value !== "" && $.isNumeric(LineNo_input.value)) {
            $(LineNo_input).addClass("ui-state-highlight")
                .removeClass("ui-state-error");
            LineNo_error = 0;
        } else {
            $(LineNo_input).removeClass("ui-state-highlight")
                .addClass("ui-state-error");
            LineNo_error = 1;
        }

        submitbutton.disabled = LineNo_error !== 0;
    });

});
