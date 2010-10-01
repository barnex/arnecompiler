#include <stdio.h>

int main(){
  int i;
  int answer;
  int count = 1;
  int n = 2;

  for(i=2; count != 10002; i++){
     if(isprime(i)){
       answer = i;
	count++;
     }
  }
  printf("%d", answer);

}

int isprime(int n){
  int i;
  int prime = 1;

  for(i=2; i < n; i++){
    if(n%i == 0){
      prime = 0;
      i=n;
    }
  }
  return prime;
}

// 
// variable(count, 1)
// variable(n, 2)
// 
// function(isprime, block(variable(n)),  block(
//   variable(i)
//   variable(prime)
//   prime(1)
//   for(i(2), <(i, n), i(+(i, 1)),  block(
//     if(==(%(n, i), 0), block(
//       prime(0)
//       i(n)
//     ))
//   ))
//   prime
// ))
// 
// variable(answer)
// variable(i)
// for(i(2), !=(count, 10002), i(+(i, 1)),  block(
//     if(isprime(i), block(
//       answer(i)
//       count(+(count, 1))
//     ))
// ))
// 
// print_int(answer)
// 
// cfunction(print_int, block(variable(x)))