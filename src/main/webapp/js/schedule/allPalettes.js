$(document).ready(function() {

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
        var id  =activeTile.id;
        var split = id.split("_");
        console.log(split[0]+ " and "+ split[1]+ " and " + hex);
        var palID= split[0];
        var colorNo = split[1];

        $.ajax({
            url: 'editColorAJAX',
            data: "paletteid=" + palID+"&index="+colorNo+"&inputpaletteColor="+hex ,
            type: 'post',
            async: true,
            success: function (response) {
                if (response==1) {
                    console.log("changed");
                }
                else {
                    console.log("unchanged");
                }
            }})

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


    normalizeHeight();
    $("#dialog").dialog({
        modal: true,
        bgiframe: true,
        autoOpen: false,
        width: 500,
        height: 400,
    });
    $(".delButton").click(function(e) {
        e.preventDefault();

        var parentrow = this.parentElement.parentElement.parentElement;
        var text = "";

        document.getElementById("dialog").innerHTML=parentrow.children[0].innerHTML+"<br>"+parentrow.children[1].innerHTML+"<br>"+parentrow.children[2].innerHTML+"<br>"+parentrow.children[3].innerHTML;
        var targetUrl = $(this).attr("href");
        console.log(targetUrl)
        $('#dialog').dialog('option', 'title', 'Delete this Palette???')
        $("#dialog").dialog({
            hide: {
                effect: "explode",
                duration: 300
            },
            show: {
                effect: "explode",
                duration: 300
            },
            buttons : {
                "Delete For Real" : function() {
                    console.log("try");
                    var cardid ="#"+ targetUrl+"_card";
                    console.log(cardid)
                    $.ajax({
                        url: 'deletePalette',
                        data: "paletteID=" + targetUrl+"&AJAX=true" ,
                        type: 'post',
                        async: true,
                        success: function (response) {
                            if (response==1){
                                $(cardid).slideUp();}
                            else {
                                $(cardid).addClass("ui-state-error");
                            }
                        }})
                     $(this).dialog("close");

                    //$(cardid).slideUp();
                },
                "Let It Stay" : function() {
                    $(this).dialog("close");
                }
            }
        });
        $("#dialog").dialog("open"); });
})
function normalizeHeight() {
    var cards = jQuery("span.card");
    var big = 0;
    cards.each(function (index, el) {
        if (jQuery(el).height() > big)
            big = jQuery(el).height(); //find the largest height
    });
    cards.each(function (index, el) {
        jQuery(el).css("height", big + "px"); //assign largest height to all the divs
    });}

