package algorithmsWeek1;

import tester.Tester;

class Algorithm {
  
  long karatsubaMultiplication(long x, long y) {
    
    
    long xDigits = (long) Math.floor(Math.log10(x) + 1);
    long yDigits = (long) Math.floor(Math.log10(y) + 1);
    
    if (xDigits <= 1 && yDigits <= 1) {
      return x * y; 
    }
    
    else {
    
      long b = x % (long) Math.pow(10, xDigits/2);
      long a = (x - b) / (long) Math.pow(10, xDigits/2);
      long d = y % (long) Math.pow(10, yDigits/2);
      long c = (y - d) / (long) Math.pow(10, yDigits/2);
    
      long ac = new Algorithm().karatsubaMultiplication(a, c);
      long bd = new Algorithm().karatsubaMultiplication(b, d);
      long abcd = new Algorithm().karatsubaMultiplication(a+b, c+d);
      
      return (long) (Math.pow(10, xDigits) * ac) +
             (long) (Math.pow(10, xDigits/2) * (abcd - ac - bd)) +
                    bd; 
      
    } 
       
  }
    

}
  







class ExamplesProblems {
  
  Algorithm example = new Algorithm(); 

  
  void testKaratsubaMultiplication (Tester t) {
    t.checkExpect(example.karatsubaMultiplication(1, 2), 2L); 
    t.checkExpect(example.karatsubaMultiplication(12,34), 408L); 
    t.checkExpect(example.karatsubaMultiplication(1234, 5678), 7006652L); 
//    t.checkExpect(example.karatsubaMultiplication(12, 3456), 41472L); 
//    t.checkExpect(example.karatsubaMultiplication(123, 456), 56088L);
//    t.checkExpect(example.karatsubaMultiplication
//        (3141592653589793238L, 
//         2718281828459045235L), 0);
  }
}

