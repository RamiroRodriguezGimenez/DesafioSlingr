
package challenge.com.CalcController;

import challenge.com.Entities.Calculus;
import challenge.com.Services.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CalculatorController {
    
    /**
     *
     */
    @Autowired (required=false)
    public CalculatorService CalculatorService;
    
    @GetMapping(value="status")
    String checkStatus() {
        return("ok");
    }
    
    @GetMapping("/index")
    public String getIndex(){
        return("index.html");
    }
  
    
    @GetMapping ("/calculate/{expression}/ndecimal/{nDecimal}")
    public ResponseEntity<Calculus> calculate(@PathVariable("expression") String expression, @PathVariable (required=false) int nDecimal){
        try{
            
           Calculus calculus=CalculatorService.create(expression,nDecimal);
          
            return new ResponseEntity<>(calculus,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
      
    }
    
    @PostMapping ("/calculate")
    public ResponseEntity<Calculus> calculate(@RequestBody Calculus calculus){
        try{
            calculus=CalculatorService.create(calculus.getExpression(), calculus.getnDecimal());
            return new ResponseEntity<>(calculus,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
}

