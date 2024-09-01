let al = 2;
let bl = 2;
let cl = 1;
let xl = 3;
let orginal_op1 = 2;
let orginal_op2 = 2;
let orginal_op3 = 2;
let orginal_opl = Math.sqrt(Math.pow(orginal_op1, 2) + Math.pow(orginal_op2, 2) + Math.pow(orginal_op3, 2));

let op1 = 2;
let op2 = 2;
let opl = Math.sqrt(Math.pow(op1, 2) + Math.pow(op2, 2));

let angle_EA = 0;
let angle_AB = 0;
let angle_BC = 0;
let angle_rotation = 0;

document.getElementById("input_al").onchange = (event) => {
    al = event.target.value;
    calc();
};
document.getElementById("input_bl").onchange = (event) => {
    bl = event.target.value;
    calc();
};
document.getElementById("input_cl").onchange = (event) => {
    cl = event.target.value;
    calc();
};

document.getElementById("input_xl").onchange = (event) => {
    xl = event.target.value;
    calc();
};
document.getElementById("input_op1").onchange = (event) => {
    orginal_op1 = event.target.value;
    calc();
};
document.getElementById("input_op2").onchange = (event) => {
    orginal_op2 = event.target.value;
    calc();
};
document.getElementById("input_op3").onchange = (event) => {
    orginal_op3 = event.target.value;
    calc();
};

document.addEventListener("DOMContentLoaded", () => {
    calc();
})


function calc() {
    threeDtotwoD()
    if (orginal_opl > (parseInt(al) + parseInt(al) + parseInt(al))) {
        console.log("OP out of range")
        return;
    }

    angle_EA = ((Math.atan( Math.abs(op2) / Math.sqrt( Math.pow(op1, 2) ) ) * (180/Math.PI)) +
    (Math.acos( ( Math.pow(xl, 2) + Math.pow(opl, 2) - Math.pow(cl, 2) ) / ( 2 * xl * opl ) ) * (180/Math.PI))  +
    (Math.acos( ( Math.pow(al, 2) + Math.pow(xl, 2) - Math.pow(bl, 2)    ) / ( 2 * al * xl  ) ) * (180/Math.PI)));
    // op3 brauche ich für die 2D Ansicht nicht
    // angle_EA = ((Math.atan( Math.abs(op3) / Math.sqrt( Math.pow(op1, 2) + Math.pow(op2, 2) ) ) * (180/Math.PI)) +
    //            (Math.acos( ( Math.pow(xl, 2) + Math.pow(opl, 2) - Math.pow(cl, 2) ) / ( 2 * xl * opl ) ) * (180/Math.PI))  +
    //            (Math.acos( ( Math.pow(al, 2) + Math.pow(xl, 2) - Math.pow(bl, 2)    ) / ( 2 * al * xl  ) ) * (180/Math.PI)));
    angle_AB = (Math.acos( ( Math.pow(bl, 2) + Math.pow(al, 2) - Math.pow(xl, 2) ) / ( 2 * bl * al  ) ) * (180/Math.PI));
    angle_BC = ((Math.acos( ( Math.pow(xl, 2) + Math.pow(bl, 2) - Math.pow(al, 2) ) / ( 2 * xl * bl  ) ) * (180/Math.PI)) + 
               (Math.acos( ( Math.pow(cl, 2) + Math.pow(xl, 2) - Math.pow(opl, 2) ) / ( 2 * cl * xl ) ) * (180/Math.PI)));


    angle_rotation = (Math.acos( (orginal_op1 * 1 + orginal_op2 * 0 + 0 * 0 ) / ((Math.sqrt(Math.pow(orginal_op1, 2) + Math.pow(orginal_op2, 2) + Math.pow(0, 2))) * (Math.sqrt(Math.pow(1, 2) + Math.pow(0, 2) + Math.pow(0, 2)))) ) * (180/Math.PI));
    if (angle_rotation == NaN || (orginal_op1 == 0 && orginal_op2 == 0)) {
        angle_rotation = 0;
    }

    setAngle();
}

function calcOPL() {
    opl = Math.sqrt(Math.pow(op1, 2) + Math.pow(op2, 2));
}
function calcOriginalOPL() {
    orginal_opl = Math.sqrt(Math.pow(orginal_op1, 2) + Math.pow(orginal_op2, 2) + Math.pow(orginal_op3, 2));
}

function threeDtotwoD() {
    calcOriginalOPL();
    const OriginatlOPThreeHigth = Math.abs(orginal_op3); // unnessesary -> op3 should only be positive in later production
    const OriginalOPShadowLenght = Math.sqrt(Math.pow(orginal_opl, 2) - Math.pow(OriginatlOPThreeHigth, 2));
    op1 = OriginalOPShadowLenght;
    op2 = OriginatlOPThreeHigth;
    calcOPL()
}

function setAngle() {
    document.getElementById("angle_EA").innerHTML = `EA: ${angle_EA} °`;
    document.getElementById("angle_AB").innerHTML = `AB: ${angle_AB} °`;
    document.getElementById("angle_BC").innerHTML = `BC: ${angle_BC} °`;
    document.getElementById("angle_rotation").innerHTML = `Rotation Angle: ${angle_rotation} °`;
}