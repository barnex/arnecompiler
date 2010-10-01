.section .data
	.lcomm 	_static_variable_0_sum, 4
	.lcomm 	_static_variable_1_i, 4

.section .text
.globl _start
_start:

	movl	$0, %eax
	movl	%eax, _static_variable_0_sum 	 ##initialize sum
	movl	$2, %eax
	movl	%eax, _static_variable_1_i 	 ##initialize i
.FOR0CONDITION:
	movl	$2000000, %eax
	pushl	%eax
	movl	_static_variable_1_i, %eax 	 #fetch static i
	popl	%ebx
	cmpl	%ebx, %eax
	jl	.LESS0TRUE
.LESS0FALSE:
	movl	$0, %eax
	jmp	.LESS0END
.LESS0TRUE:
	movl	$1, %eax
.LESS0END:
	cmpl	$0, %eax
	je	.FOR0EXIT
.IF0CONDITION:
	movl	_static_variable_1_i, %eax 	 #fetch static i
	pushl	%eax
	call	_function_0_isprime
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	cmpl	$0, %eax
	je	.IF0END
	movl	_static_variable_0_sum, %eax 	 #fetch static sum
	pushl	%eax
	call	print_int
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	_static_variable_1_i, %eax 	 #fetch static i
	pushl	%eax
	movl	_static_variable_0_sum, %eax 	 #fetch static sum
	popl	%ebx
	addl	%ebx, %eax
	movl	%eax, _static_variable_0_sum 	 #set static sum
.IF0END:
	incl	_static_variable_1_i
	jmp	.FOR0CONDITION
.FOR0EXIT:
	movl	_static_variable_0_sum, %eax 	 #fetch static sum
	pushl	%eax
	call	print_int
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	

	pushl	$0
	call	exit
	

.type _function_1_sub @function
_function_1_sub:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$0, %esp 	 #reserve 0 local variables
	movl	12(%ebp), %eax 	 #fetch argument b
	pushl	%eax
	movl	8(%ebp), %eax 	 #fetch argument a
	popl	%ebx
	subl	%ebx, %eax
	addl	$0, %esp 	 #free 0 local variables
	leave
	ret
.type _function_2_add @function
_function_2_add:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$0, %esp 	 #reserve 0 local variables
	movl	12(%ebp), %eax 	 #fetch argument b
	pushl	%eax
	movl	8(%ebp), %eax 	 #fetch argument a
	popl	%ebx
	addl	%ebx, %eax
	addl	$0, %esp 	 #free 0 local variables
	leave
	ret
.type _function_3_mul @function
_function_3_mul:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$0, %esp 	 #reserve 0 local variables
	movl	12(%ebp), %eax 	 #fetch argument b
	pushl	%eax
	movl	8(%ebp), %eax 	 #fetch argument a
	popl	%ebx
	imull	%ebx, %eax
	addl	$0, %esp 	 #free 0 local variables
	leave
	ret
.type _function_4_div @function
_function_4_div:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$0, %esp 	 #reserve 0 local variables
	movl	12(%ebp), %eax 	 #fetch argument b
	pushl	%eax
	movl	8(%ebp), %eax 	 #fetch argument a
	popl	%ebx
	movl	$0, %edx
	idivl	%ebx
	addl	$0, %esp 	 #free 0 local variables
	leave
	ret
.type _function_0_isprime @function
_function_0_isprime:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$8, %esp 	 #reserve 2 local variables
	movl	$1, %eax
	movl	%eax, -8(%ebp) 	 ##initialize prime
	movl	$2, %eax
	movl	%eax, -4(%ebp) 	 #set local i
.FOR1CONDITION:
	movl	8(%ebp), %eax 	 #fetch argument n
	pushl	%eax
	movl	-4(%ebp), %eax 	 #fetch local i
	popl	%ebx
	cmpl	%ebx, %eax
	jl	.LESS1TRUE
.LESS1FALSE:
	movl	$0, %eax
	jmp	.LESS1END
.LESS1TRUE:
	movl	$1, %eax
.LESS1END:
	cmpl	$0, %eax
	je	.FOR1EXIT
.IF1CONDITION:
	movl	$0, %eax
	pushl	%eax
	movl	-4(%ebp), %eax 	 #fetch local i
	pushl	%eax
	movl	8(%ebp), %eax 	 #fetch argument n
	popl	%ebx
	movl	$0, %edx
	idivl	%ebx
	movl	%edx, %eax
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ0TRUE
.EQ0FALSE:
	movl	$0, %eax
	jmp	.EQ0END
.EQ0TRUE:
	movl	$1, %eax
.EQ0END:
	cmpl	$0, %eax
	je	.IF1END
	movl	$0, %eax
	movl	%eax, -8(%ebp) 	 #set local prime
	movl	8(%ebp), %eax 	 #fetch argument n
	movl	%eax, -4(%ebp) 	 #set local i
.IF1END:
	incl	-4(%ebp)
	jmp	.FOR1CONDITION
.FOR1EXIT:
	movl	-8(%ebp), %eax 	 #fetch local prime
	addl	$8, %esp 	 #free 2 local variables
	leave
	ret
