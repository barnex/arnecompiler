.section .data
.lcomm 	_static_variable_54_answer, 4
.lcomm 	_static_variable_55_i, 4
.lcomm 	_static_variable_64_j, 4



.section .text
.globl _start
_start:

#initialize variable answer
movl	$1,	%eax
movl	%eax,	_static_variable_54_answer


#initialize variable i
movl	$0,	%eax
movl	%eax,	_static_variable_55_i


movl	$1,	%eax
movl	%eax,	_static_variable_55_i 	 #set static variable i
for_condition_9:
movl	$21,	%eax
pushl	%eax
movl	_static_variable_55_i,	%eax 	 #fetch static variable i
popl	%ebx
cmpl	%ebx,	%eax
jne	compare_13true
compare_13false:
movl	$0,	%eax
jmp	compare_13end
compare_13true:
movl	$1,	%eax
compare_13end:
cmpl	$0,	%eax
je	for_exit_9
if_23_condition:
movl	_static_variable_55_i,	%eax 	 #fetch static variable i
pushl	%eax
movl	_static_variable_54_answer,	%eax 	 #fetch static variable answer
popl	%ebx
movl	$0,	%edx
idivl	%ebx
movl	%edx,	%eax
cmpl	$0,	%eax
je	if_23_end
#initialize variable j
movl	$1,	%eax
movl	%eax,	_static_variable_64_j


while_condition_32:
movl	_static_variable_55_i,	%eax 	 #fetch static variable i
pushl	%eax
movl	_static_variable_64_j,	%eax 	 #fetch static variable j
pushl	%eax
movl	_static_variable_54_answer,	%eax 	 #fetch static variable answer
popl	%ebx
imull	%ebx,	%eax
popl	%ebx
movl	$0,	%edx
idivl	%ebx
movl	%edx,	%eax
cmpl	$0,	%eax
je	while_exit_32
movl	$1,	%eax
pushl	%eax
movl	_static_variable_64_j,	%eax 	 #fetch static variable j
popl	%ebx
addl	%ebx,	%eax
movl	%eax,	_static_variable_64_j 	 #set static variable j


jmp	while_condition_32
while_exit_32:
movl	_static_variable_64_j,	%eax 	 #fetch static variable j
pushl	%eax
movl	_static_variable_54_answer,	%eax 	 #fetch static variable answer
popl	%ebx
imull	%ebx,	%eax
movl	%eax,	_static_variable_54_answer 	 #set static variable answer


if_23_end:


movl	$1,	%eax
pushl	%eax
movl	_static_variable_55_i,	%eax 	 #fetch static variable i
popl	%ebx
addl	%ebx,	%eax
movl	%eax,	_static_variable_55_i 	 #set static variable i
jmp	for_condition_9
for_exit_9:
movl	_static_variable_54_answer,	%eax 	 #fetch static variable answer
pushl	%eax
call	print_int
addl	$4,	%esp 	 #pop 1 arguments back off the stack




pushl	$0
call	exit


