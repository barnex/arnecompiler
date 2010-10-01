.section .data
	.lcomm 	_static_variable_0_count, 4
	.lcomm 	_static_variable_1_n, 4
	.lcomm 	_static_variable_5_answer, 4
	.lcomm 	_static_variable_6_i, 4

.section .text
.globl _start
_start:

	movl	$1, %eax
	movl	%eax, _static_variable_0_count 	 ##initialize count
	movl	$2, %eax
	movl	%eax, _static_variable_1_n 	 ##initialize n
	movl	$2, %eax
	movl	%eax, _static_variable_6_i 	 #set static i
.FOR1CONDITION:
	movl	$10002, %eax
	pushl	%eax
	movl	_static_variable_0_count, %eax 	 #fetch static count
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
	je	.FOR1EXIT
.IF1CONDITION:
	movl	_static_variable_6_i, %eax 	 #fetch static i
	pushl	%eax
	call	_function_0_isprime
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	cmpl	$0, %eax
	je	.IF1END
	movl	_static_variable_6_i, %eax 	 #fetch static i
	movl	%eax, _static_variable_5_answer 	 #set static answer
	incl	_static_variable_0_count
.IF1END:
	incl	_static_variable_6_i
	jmp	.FOR1CONDITION
.FOR1EXIT:
	movl	_static_variable_5_answer, %eax 	 #fetch static answer
	pushl	%eax
	call	print_int
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	

	pushl	$0
	call	exit
	

.type _function_0_isprime @function
_function_0_isprime:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$8, %esp 	 #reserve 2 local variables
	movl	$1, %eax
	movl	%eax, -8(%ebp) 	 ##initialize prime
	movl	$2, %eax
	movl	%eax, -4(%ebp) 	 #set local i
.FOR0CONDITION:
	movl	8(%ebp), %eax 	 #fetch argument n
	pushl	%eax
	movl	-4(%ebp), %eax 	 #fetch local i
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
	je	.IF0END
	movl	$0, %eax
	movl	%eax, -8(%ebp) 	 #set local prime
	movl	8(%ebp), %eax 	 #fetch argument n
	movl	%eax, -4(%ebp) 	 #set local i
.IF0END:
	incl	-4(%ebp)
	jmp	.FOR0CONDITION
.FOR0EXIT:
	movl	-8(%ebp), %eax 	 #fetch local prime
	addl	$8, %esp 	 #free 2 local variables
	leave
	ret
