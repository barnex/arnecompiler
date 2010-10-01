.section .data
	.lcomm 	_static_variable_25_eof, 4
	.lcomm 	_static_variable_0_array, 4
	.lcomm 	_static_variable_4_chr_a, 4
	.lcomm 	_static_variable_5_digit, 4
	.lcomm 	_static_variable_6_i, 4
	.lcomm 	_static_variable_7_answer, 4
	.lcomm 	_static_variable_8_i, 4
	.lcomm 	_static_variable_9_product, 4
	.lcomm 	_static_variable_10_j, 4

.section .text
.globl _start
_start:

	movl	$-1, %eax
	movl	%eax, _static_variable_25_eof 	 ##initialize eof
	movl	$1000, %eax
	pushl	%eax
	movl	$4, %eax
	popl	%ebx
	imull	%ebx, %eax
	pushl	%eax
	call	malloc
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	%eax, _static_variable_0_array 	 ##initialize array
	movl	$48, %eax
	movl	%eax, _static_variable_4_chr_a 	 ##initialize chr_a
	call	_function_2_read
	addl	$0, %esp 	 #pop 0 arguments back off the stack
	movl	%eax, _static_variable_5_digit 	 ##initialize digit
	movl	$0, %eax
	movl	%eax, _static_variable_6_i 	 ##initialize i
.WHILE0CONDITION:
	movl	_static_variable_4_chr_a, %eax 	 #fetch static chr_a
	pushl	%eax
	movl	_static_variable_25_eof, %eax 	 #fetch static eof
	pushl	%eax
	call	_function_3_sub
	addl	$8, %esp 	 #pop 2 arguments back off the stack
	pushl	%eax
	movl	_static_variable_5_digit, %eax 	 #fetch static digit
	popl	%ebx
	cmpl	%ebx, %eax
	jne	.NEQ0TRUE
.NEQ0FALSE:
	movl	$0, %eax
	jmp	.NEQ0END
.NEQ0TRUE:
	movl	$1, %eax
.NEQ0END:
	cmpl	$0, %eax
	je	.WHILE0EXIT
	movl	_static_variable_5_digit, %eax 	 #fetch static digit
	pushl	%eax
	movl	_static_variable_6_i, %eax 	 #fetch static i
	pushl	%eax
	call	_function_0_set_array
	addl	$8, %esp 	 #pop 2 arguments back off the stack
	incl	_static_variable_6_i
	movl	_static_variable_5_digit, %eax 	 #fetch static digit
	pushl	%eax
	call	print_int
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	call	_function_2_read
	addl	$0, %esp 	 #pop 0 arguments back off the stack
	movl	%eax, _static_variable_5_digit 	 #set static digit
	jmp	.WHILE0CONDITION
.WHILE0EXIT:
	movl	$0, %eax
	movl	%eax, _static_variable_7_answer 	 ##initialize answer
	movl	$0, %eax
	movl	%eax, _static_variable_8_i 	 ##initialize i
.FOR0CONDITION:
	movl	$995, %eax
	pushl	%eax
	movl	_static_variable_8_i, %eax 	 #fetch static i
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
	movl	%eax, _static_variable_9_product 	 ##initialize product
	movl	$0, %eax
	movl	%eax, _static_variable_10_j 	 ##initialize j
.FOR1CONDITION:
	movl	$5, %eax
	pushl	%eax
	movl	_static_variable_10_j, %eax 	 #fetch static j
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
	movl	_static_variable_10_j, %eax 	 #fetch static j
	pushl	%eax
	movl	_static_variable_8_i, %eax 	 #fetch static i
	popl	%ebx
	addl	%ebx, %eax
	pushl	%eax
	call	_function_1_get_array
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	pushl	%eax
	movl	_static_variable_9_product, %eax 	 #fetch static product
	popl	%ebx
	imull	%ebx, %eax
	movl	%eax, _static_variable_9_product 	 #set static product
	incl	_static_variable_10_j
	jmp	.FOR1CONDITION
.FOR1EXIT:
.IF0CONDITION:
	movl	_static_variable_7_answer, %eax 	 #fetch static answer
	pushl	%eax
	movl	_static_variable_9_product, %eax 	 #fetch static product
	popl	%ebx
	cmpl	%ebx, %eax
	jg	.GREATER0TRUE
.GREATER0FALSE:
	movl	$0, %eax
	jmp	.GREATER0END
.GREATER0TRUE:
	movl	$1, %eax
.GREATER0END:
	cmpl	$0, %eax
	je	.IF0END
	movl	_static_variable_9_product, %eax 	 #fetch static product
	movl	%eax, _static_variable_7_answer 	 #set static answer
.IF0END:
	incl	_static_variable_8_i
	jmp	.FOR0CONDITION
.FOR0EXIT:
	movl	_static_variable_7_answer, %eax 	 #fetch static answer
	pushl	%eax
	call	print_int
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	

	pushl	$0
	call	exit
	

.type _function_3_sub @function
_function_3_sub:
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
.type _function_4_add @function
_function_4_add:
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
.type _function_5_mul @function
_function_5_mul:
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
.type _function_6_div @function
_function_6_div:
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
.type _function_0_set_array @function
_function_0_set_array:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$0, %esp 	 #reserve 0 local variables
	movl	12(%ebp), %eax 	 #fetch argument value
	pushl	%eax
	movl	8(%ebp), %eax 	 #fetch argument index
	pushl	%eax
	movl	$4, %eax
	popl	%ebx
	imull	%ebx, %eax
	pushl	%eax
	movl	_static_variable_0_array, %eax 	 #fetch static array
	popl	%ebx
	addl	%ebx, %eax
	pushl	%eax
	call	setmem
	addl	$8, %esp 	 #pop 2 arguments back off the stack
	addl	$0, %esp 	 #free 0 local variables
	leave
	ret
.type _function_1_get_array @function
_function_1_get_array:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$0, %esp 	 #reserve 0 local variables
	movl	8(%ebp), %eax 	 #fetch argument index
	pushl	%eax
	movl	$4, %eax
	popl	%ebx
	imull	%ebx, %eax
	pushl	%eax
	movl	_static_variable_0_array, %eax 	 #fetch static array
	popl	%ebx
	addl	%ebx, %eax
	pushl	%eax
	call	getmem
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	addl	$0, %esp 	 #free 0 local variables
	leave
	ret
.type _function_2_read @function
_function_2_read:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$0, %esp 	 #reserve 0 local variables
	movl	_static_variable_4_chr_a, %eax 	 #fetch static chr_a
	pushl	%eax
	call	system_stdin
	addl	$0, %esp 	 #pop 0 arguments back off the stack
	pushl	%eax
	call	fgetc
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	pushl	%eax
	call	_function_3_sub
	addl	$8, %esp 	 #pop 2 arguments back off the stack
	addl	$0, %esp 	 #free 0 local variables
	leave
	ret
