// SRC
// https://www.mathsisfun.com/algebra/trig-solving-sss-triangles.html

let al = 13;
let bl = 13;
let cl = 13;
let xl;
let xl_max;
let xl_min;
let minabcL;
let maxabcL;
let orginal_op1 = 1;
let orginal_op2 = 1;
let orginal_op3 = 1;

let op1;
let op2;
let opl;

let angle_EA = 0;
let angle_AB = 0;
let angle_BC = 0;
let angle_rotation = 0;

const input_al = document.getElementById("input_al");
const input_bl = document.getElementById("input_bl");
const input_cl = document.getElementById("input_cl");
const input_xl = document.getElementById("input_xl");
const input_op1 = document.getElementById("input_op1");
const input_op2 = document.getElementById("input_op2");
const input_op3 = document.getElementById("input_op3");


input_al.onchange = (event) => {
    al = parseFloat(event.target.value);
    calc();
};
input_bl.onchange = (event) => {
    bl = parseFloat(event.target.value);
    calc();
};
input_cl.onchange = (event) => {
    cl = parseFloat(event.target.value);
    calc();
};

input_xl.onchange = (event) => {
    xl = parseFloat(event.target.value);
    calc();
};
input_op1.onchange = (event) => {
    orginal_op1 = parseFloat(event.target.value);
    calc();
};
input_op2.onchange = (event) => {
    orginal_op2 = parseFloat(event.target.value);
    calc();
};
input_op3.onchange = (event) => {
    orginal_op3 = parseFloat(event.target.value);
    calc();
};

document.addEventListener("DOMContentLoaded", () => {
    setLoadValues();
    calc();
})

/**
 * Hauptberechnung der Winkel
 * @returns nothing
 */
function calc() {
    calcOPL();
    threeDtotwoD();
    // calcXminmax();
    calcExtremArmLenght();
    calcXminmax();
    setData();
    if (opl > maxabcL || opl < minabcL) {
        console.log("OP out of range")
        return;
    }


    angle_EA = ((Math.atan( op2 / op1 ) * (180/Math.PI)) +
                (Math.acos( ( Math.pow(xl, 2) + Math.pow(opl, 2) - Math.pow(cl, 2) ) / ( 2 * xl * opl ) ) * (180/Math.PI))  +
                (Math.acos( ( Math.pow(al, 2) + Math.pow(xl, 2) - Math.pow(bl, 2)    ) / ( 2 * al * xl ) ) * (180/Math.PI)));
    angle_AB = (Math.acos( ( Math.pow(bl, 2) + Math.pow(al, 2) - Math.pow(xl, 2) ) / ( 2 * bl * al ) ) * (180/Math.PI));
    angle_BC = ((Math.acos( ( Math.pow(xl, 2) + Math.pow(bl, 2) - Math.pow(al, 2) ) / ( 2 * xl * bl ) ) * (180/Math.PI)) + 
               (Math.acos( ( Math.pow(cl, 2) + Math.pow(xl, 2) - Math.pow(opl, 2) ) / ( 2 * cl * xl ) ) * (180/Math.PI)));

    // console.log([al, bl, cl, xl, xl_min, xl_max, op1, op2, opl])
    // console.log([xl_min, xl_max])
    angle_rotation = (Math.acos( (orginal_op1 * 1 + orginal_op2 * 0 + 0 * 0 ) / ((Math.sqrt(Math.pow(orginal_op1, 2) + Math.pow(orginal_op2, 2) + Math.pow(0, 2))) * (Math.sqrt(Math.pow(1, 2) + Math.pow(0, 2) + Math.pow(0, 2)))) ) * (180/Math.PI));
    if (angle_rotation == NaN || (orginal_op1 == 0 && orginal_op2 == 0)) {
        angle_rotation = 0;
    }

    setData();
}

/**
 * Berechnet, die minimale Armlänge / den minimalen Abstand, den der Endpunkt zum Ursprung haben kann.
 */
function calcExtremArmLenght() {

    // TODO: Not working at the time being, idk. which case i have to calculate what

    ///// V 1
    // // Finding MIN ABC Arm length
    // const dAlBl = Math.abs(al-bl)
    // // Guckt, ob das Längen Delta zwischen al und bl größer ist als dass der delta Betrag zwischen    |  | al - bl | - cl  |
    // if (dAlBl > Math.abs(dAlBl-cl)) {
    //     minabcL = dAlBl-cl;
    // }
    // else {
    //     minabcL = dAlBl;
    // }
    
    ///// V 2
    // Es könen drei Fälle eintreten:
    // 1. al+bl ist größer als cl
    // 2. al+bl ist kleiner als cl
    // 3. al+bl ist cl
    // if ((al+bl) > cl) {
    //     // Drei Fälle:
    //     // 1. al > bl
    //     // 2. bl > al
    //     // 3. al == bl
    //     if (al > bl) {
    //         minabcL = (al-(bl+cl))
    //     } else if (bl > al) {
    //         minabcL = al-bl-cl
    //     } else if (al == bl) {
    //         // 1. Wenn al+bl > cl
    //         // 2. Wenn al+bl < cl
    //         if ((al+bl) > cl) {
    //             minabcL = 0;
    //         } else if ((al+bl) < cl) {
    //             minabcL = (al+bl)-cl
    //         }
    //     }
    // } else if ((al+bl) < cl) {
    //     minabcL = (al+bl)-cl
    // } else if ((al+bl) == cl) {
    //     minabcL = 0;
    // }


    //// V3
    const dAlBl = al-bl
    if (dAlBl == 0) {
        minabcL = (al+bl)-cl
    } else if (dAlBl > 0) {
        if (Math.abs(dAlBl) > Math.abs(dAlBl-cl)) {
            minabcL = Math.abs(dAlBl-cl)
        } else {
            minabcL = Math.abs(dAlBl)
        }
    } else if (dAlBl < 0) {
    } // -->> WRONG

    
    
    // Calculating MAX ABC Arm length
    maxabcL = (al+bl+cl);

}

/**
 * Berechnet die direkte Gesamtstrecke zwischen Ursprung und dem gegebenen Punkt OP. aka Vektorbetrag
 */
function calcOPL() {
    opl = Math.sqrt(Math.pow(orginal_op1, 2) + Math.pow(orginal_op2, 2) + Math.pow(orginal_op3, 2));
}

/**
 * Berechnet die Minimale und Maximale Länge von xl
 */
function calcXminmax() {

    // TODO: Calculation should be all right, but x max/min not working

    // Kann maximal die al+bl sein
    // Bedingung: Muss P erreichen können.
    // Punkt P wurde hier schon überprüft, ob er in der möglichen erreichbaren Distanz liegt. -> nothing to worry about
    // Ich kann also vom Punkt P aus radial mit cl umhergehen um die max und min von xl zu bekommen (theoretisch)

    // xl_max = (cl+opl)
    // xl_min = (cl-opl)
    xl_max = (opl+cl)
    xl_min = (opl-cl)
    // input_xl.setAttribute("max", xl_max);
    // input_xl.setAttribute("min", xl_min);

    // const avgminmax = xl_min + ((xl_max-xl_min) / 2)
    // if (xl == undefined || xl < xl_min || xl > xl_max) {
    //     xl = avgminmax;
    //     input_xl.value = avgminmax;
    // }
}



/**
 * Wandelt die gegebenen 3D Koordinaten in 2D Koordinaten um, mitdenen dann die Winkel berechnet werden.
 */
function threeDtotwoD() {
    const OriginatlOPThreeHigth = Math.abs(orginal_op3); // unnessesary -> op3 should only be positive in later production
    const OriginalOPShadowLenght = Math.sqrt(Math.pow(opl, 2) - Math.pow(OriginatlOPThreeHigth, 2));
    op1 = OriginalOPShadowLenght;
    op2 = OriginatlOPThreeHigth;
}

/**
 * Fügt alle berechneten Daten in die Website ein.
 */
function setData() {
    document.getElementById("angle_EA").innerHTML = `EA: ${angle_EA} °`;
    document.getElementById("angle_AB").innerHTML = `AB: ${angle_AB} °`;
    document.getElementById("angle_BC").innerHTML = `BC: ${angle_BC} °`;
    document.getElementById("angle_rotation").innerHTML = `Rotation Angle: ${angle_rotation} °`;
    document.getElementById("3dlfromOrigin").innerHTML = `ODL: ${opl}`; // Total lenght from Origin to Destination never changes regardless if it is in 3d or 2d. Think about it
    document.getElementById("minabcL").innerHTML = `Minimal Distand from Origin to Endpoint: ${minabcL}`;
    document.getElementById("maxabcL").innerHTML = `Maximal Distand from Origin to Endpoint: ${maxabcL}`;
    document.getElementById("xlminmax").innerHTML = `Min: ${xl_min} - Max: ${xl_max}`;
}

/**
 * Lädt die in JS definierten default Daten
 */
function setLoadValues() {
    input_al.value = al;
    input_bl.value = bl;
    input_cl.value = cl;
    // input_xl.value = xl;
    input_op1.value = orginal_op1;
    input_op2.value = orginal_op2;
    input_op3.value = orginal_op3;
}