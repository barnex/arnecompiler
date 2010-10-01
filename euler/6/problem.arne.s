.section .data
.lcomm 	_static_variable_55_sum, 4
.lcomm 	_static_variable_56_sumsquares, 4
.lcomm 	_static_variable_57_i, 4



.section .text
.globl _start
_start:

#initialize variable sum
movl	$0,	%eax
movl	%eax,	_static_variable_55_sum


#initialize variable sumsquares
movl	$0,	%eax
movl	%eax,	_static_variable_56_sumsquares


#initialize variable i
movl	$0,	%eax
movl	%eax,	_static_variable_57_i


movl	$0,	%eax
movl	%eax,	_static_variable_57_i 	 #set static variable i
for_condition_13:
movl	$101,	%eax
pushl	%eax
movl	_static_variable_57_i,	%eax 	 #fetch static variable i
popl	%ebx
cmpl	%ebx,	%eax
jne	compare_17true
compare_17false:
movl	$0,	%eax
jmp	compare_17end
compare_17true:
movl	$1,	%eax
compare_17end:
cmpl	$0,	%eax
je	for_exit_13
movl	_static_variable_57_i,	%eax 	 #fetch static variable i
pushl	%eax
movl	_static_variable_55_sum,	%eax 	 #fetch static variable sum
popl	%ebx
addl	%ebx,	%eax
movl	%eax,	_static_variable_55_sum 	 #set static variable sum
movl	_static_variable_57_i,	%eax 	 #fetch static variable i
pushl	%eax
call	_function_58_sq
addl	$4,	%esp 	 #pop 1 arguments back off the stack


pushl	%eax
movl	_static_variable_56_sumsquares,	%eax 	 #fetch static variable sumsquares
popl	%ebx
addl	%ebx,	%eax
movl	%eax,	_static_variable_56_sumsquares 	 #set static variable sumsquares
movl	$1,	%eax
pushl	%eax
movl	_static_variable_57_i,	%eax 	 #fetch static variable i
popl	%ebx
addl	%ebx,	%eax
movl	%eax,	_static_variable_57_i 	 #set static variable i
jmp	for_condition_13
for_exit_13:
movl	_static_variable_56_sumsquares,	%eax 	 #fetch static variable sumsquares
pushl	%eax
movl	_static_variable_55_sum,	%eax 	 #fetch static variable sum
pushl	%eax
call	_function_58_sq
addl	$4,	%esp 	 #pop 1 arguments back off the stack


popl	%ebx
subl	%ebx,	%eax
pushl	%eax
call	print_int
addl	$4,	%esp 	 #pop 1 arguments back off the stack




pushl	$0
call	exit




.type 	_function_58_sq,	@function
_function_58_sq:
pushl	%ebp
movl	%esp,	%ebp
subl	$0,	%esp 	 #reserve 0 local variables


movl	8(%ebp),	%eax 	 #fetch argument x
pushl	%eax
movl	8(%ebp),	%eax 	 #fetch argument x
popl	%ebx
imull	%ebx,	%eax


addl	$0,	%esp 	 #free 0 local variables
leave
ret
