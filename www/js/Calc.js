var formulario=document.getElementById('formulario');
formulario.addEventListener('submit', function(e){
e.preventDefault();
var expr = document.getElementById('expression');
var dec = document.getElementById('nDecimal');

console.log(dec.value)
var solicitud ;
console.log(((solicitud)));
fetch('https://calculator-354020.rj.r.appspot.com/calculate',{
    method:'POST',
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({expression: expr.value, nDecimal: dec.value})
})
.then(res => res.text())
.then( data => {
    
    console.log(((data)))
   
    var obj=(JSON.parse(data))
 
    document.getElementById('expression').value=obj.result

})
})


