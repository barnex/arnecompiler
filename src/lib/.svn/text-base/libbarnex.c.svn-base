/** 
 * libbarnex.c
 *
 * A small library meant as an addition to libc. Barnex programs are linked
 * against libbarnex and libc. Typically, the small libbarnex can be statically
 * linked, while libc is dynamically linked.
 *
 */
#include <stdlib.h>
#include <stdio.h>

void _error_array_bounds(){
   printf("Array index out of bounds");
   exit(-4);
}

/**
 * Called at runtime
 */
void _check_array_bounds(int* array, int index){
  if(index < 0 || index >= *array){
    printf("Array index out of bounds. index: %d, array size: %d\n", index, *array);
    exit(-4);
  }
  
}

/**
 * Called at runtime, at array creation.
 */
int _check_array_size(int size){
  if(size < 0){
    printf("Invalid array size: %d\n", size);
    exit(-3);
  }
  else
    return size;
}

int assert_count = 0;

void print_int(int number){
  printf("%d\n", number);
}

int read_int(){
  int number;
  scanf("%d", &number);
  return number;
}

int system_stdin(){
  return (int) stdin;
}

int getmem(int address){
  int* address_as_pointer = (int*) address;
  return *address_as_pointer;
}

void setmem(int address, int value){
  int* address_as_pointer = (int*) address;
  *address_as_pointer = value;
}

void assert(int should_be_true){
  if(should_be_true){
    printf("passed unit test: %d\n", assert_count);
    assert_count++;
  }
  else{
    printf("assertion failed: %d\n", assert_count);
    exit(-2);
  }
}