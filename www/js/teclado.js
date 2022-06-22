function init(){
    var result = document.getElementById('expression');
    var uno = document.getElementById('1');
    var dos = document.getElementById('2');
    var tres = document.getElementById('3');
    var cuatro = document.getElementById('4');
    var cinco = document.getElementById('5');
    var seis = document.getElementById('6');
    var siete = document.getElementById('7');
    var ocho = document.getElementById('8');
    var nueve = document.getElementById('9');
    var cero = document.getElementById('0');
    var mas = document.getElementById('+');
    var menos = document.getElementById('-');
    var mult= document.getElementById('*');
    var div = document.getElementById('/');
    var potencia = document.getElementById('^');
    var raiz = document.getElementById('sqrt(');
    var abrirP = document.getElementById('(');
    var cerrarP = document.getElementById(')');
    var borrar = document.getElementById('c');
    var igual = document.getElementById('=');
    var punto = document.getElementById('.');
    
    uno.onclick = function(e){
        result.value = result.value+ uno.value;
    }
    dos.onclick = function(e){
        result.value = result.value+ dos.value;
    } 
    tres.onclick = function(e){
        result.value = result.value+ tres.value;
    }
     cuatro.onclick = function(e){
        result.value = result.value+ cuatro.value;
    } 
    cinco.onclick = function(e){
        result.value = result.value+ cinco.value;
    } 
    seis.onclick = function(e){
        result.value = result.value+ seis.value;
    } 
    siete.onclick = function(e){
        result.value = result.value+ siete.value;
    } 
    ocho.onclick = function(e){
        result.value = result.value+ ocho.value;
    } 
    nueve.onclick = function(e){
        result.value = result.value+ nueve.value;
    } 
    cero.onclick = function(e){
        result.value = result.value+ cero.value;
    } 
    mas.onclick = function(e){
        result.value = result.value+ mas.value;
    }
     menos.onclick = function(e){
        result.value = result.value+ menos.value;
    }
    mult.onclick = function(e){
        result.value = result.value+ mult.value;
    }
    div.onclick = function(e){
        result.value = result.value+ div.value;
    }
    potencia.onclick = function(e){
        result.value = result.value+ potencia.value;
    }
    raiz.onclick = function(e){
        result.value = result.value+ raiz.value;
    }
    abrirP.onclick = function(e){
        result.value = result.value+ abrirP.value;
    }
    cerrarP.onclick = function(e){
        result.value = result.value+ cerrarP.value;
    }
    punto.onclick = function(e){
        result.value = result.value+ punto.value;
    }
    borrar.onclick = function(e){
        result.value = "";
    }

}

   
