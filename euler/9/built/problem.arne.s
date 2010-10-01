.section .data
	.lcomm 	_static_variable_0_a, 4
	.lcomm 	_static_variable_1_b, 4
	.lcomm 	_static_variable_2_c, 4

.section .text
.globl _start
_start:

	movl	$1, %eax
	movl	%eax, _static_variable_0_a 	 ##initialize a
.FOR0CONDITION:
	movl	$1000, %eax
	pushl	%eax
	movl	_static_variable_0_a, %eax 	 #fetch static a
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
	movl	$1, %eax
	movl	%eax, _static_variable_1_b 	 ##initialize b
.FOR1CONDITION:
	movl	_static_variable_0_a, %eax 	 #fetch static a
	pushl	%eax
	movl	$1000, %eax
	popl	%ebx
	subl	%ebx, %eax
	pushl	%eax
	movl	_static_variable_1_b, %eax 	 #fetch static b
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
	movl	_static_variable_1_b, %eax 	 #fetch static b
	pushl	%eax
	movl	_static_variable_0_a, %eax 	 #fetch static a
	popl	%ebx
	addl	%ebx, %eax
	pushl	%eax
	movl	$1000, %eax
	popl	%ebx
	subl	%ebx, %eax
	movl	%eax, _static_variable_2_c 	 ##initialize c
.IF0CONDITION:
	movl	_static_variable_2_c, %eax 	 #fetch static c
	pushl	%eax
	call	_function_0_square
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	pushl	%eax
	movl	_static_variable_1_b, %eax 	 #fetch static b
	pushl	%eax
	call	_function_0_square
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	pushl	%eax
	movl	_static_variable_0_a, %eax 	 #fetch static a
	pushl	%eax
	call	_function_0_square
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	popl	%ebx
	addl	%ebx, %eax
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
	je	.IF0END
	movl	_static_variable_0_a, %eax 	 #fetch static a
	pushl	%eax
	call	print_int
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	_static_variable_1_b, %eax 	 #fetch static b
	pushl	%eax
	call	print_int
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	_static_variable_2_c, %eax 	 #fetch static c
	pushl	%eax
	call	print_int
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	_static_variable_2_c, %eax 	 #fetch static c
	pushl	%eax
	movl	_static_variable_1_b, %eax 	 #fetch static b
	popl	%ebx
	imull	%ebx, %eax
	pushl	%eax
	movl	_static_variable_0_a, %eax 	 #fetch static a
	popl	%ebx
	imull	%ebx, %eax
	pushl	%eax
	call	print_int
	addl	$4, %esp 	 #pop 1 arguments back off the stack
.IF0END:
	incl	_static_variable_1_b
	jmp	.FOR1CONDITION
.FOR1EXIT:
	incl	_static_variable_0_a
	jmp	.FOR0CONDITION
.FOR0EXIT:
	

	pushl	$0
	call	exit
	

.type _function_2_sub @function
_function_2_sub:
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
.type _function_3_add @function
_function_3_add:
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
.type _function_4_mul @function
_function_4_mul:
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
.type _function_5_div @function
_function_5_div:
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
.type _function_0_square @function
_function_0_square:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$0, %esp 	 #reserve 0 local variables
	movl	8(%ebp), %eax 	 #fetch argument x
	pushl	%eax
	movl	8(%ebp), %eax 	 #fetch argument x
	popl	%ebx
	imull	%ebx, %eax
	addl	$0, %esp 	 #free 0 local variables
	leave
	ret
.type _function_1_sum @function
_function_1_sum:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$0, %esp 	 #reserve 0 local variables
	movl	16(%ebp), %eax 	 #fetch argument c
	pushl	%eax
	movl	12(%ebp), %eax 	 #fetch argument b
	pushl	%eax
	movl	8(%ebp), %eax 	 #fetch argument a
	popl	%ebx
	addl	%ebx, %eax
	popl	%ebx
	addl	%ebx, %eax
	addl	$0, %esp 	 #free 0 local variables
	leave
	ret
