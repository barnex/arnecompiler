.section .data
	.lcomm 	_static_variable_23_true, 4
	.lcomm 	_static_variable_24_false, 4
	.lcomm 	_static_variable_0_a, 4
	.lcomm 	_static_variable_1_a, 4
	.lcomm 	_static_variable_2_s, 4
	.lcomm 	_static_variable_3_f_add, 4
	.lcomm 	_static_variable_6_f_mul, 4
	.lcomm 	_static_variable_9_f, 4
	.lcomm 	_static_variable_10_t, 4
	.lcomm 	_static_variable_11_i, 4
	.lcomm 	_static_variable_16_a, 4
	.lcomm 	_static_variable_17_b, 4
	.lcomm 	_static_variable_18_b, 4
	.lcomm 	_static_variable_19_i, 4
	.lcomm 	_static_variable_20_sum, 4
	.lcomm 	_static_variable_21_i, 4
	.lcomm 	_static_variable_22_lookatme, 4

.section .text
.globl _start
_start:

	movl	$1, %eax
	movl	%eax, _static_variable_23_true 	 ##initialize true
	movl	$0, %eax
	movl	%eax, _static_variable_24_false 	 ##initialize false
	movl	$0, _static_variable_0_a 	 ##autoinitialize a as 0
	movl	$0, %eax
	pushl	%eax
	movl	_static_variable_0_a, %eax 	 #fetch static a
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ0TRUE
.EQ0FALSE:
	movl	$0, %eax
	jmp	.EQ0END
.EQ0TRUE:
	movl	$1, %eax
.EQ0END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$200001, %eax
	pushl	%eax
	pushl	%eax
	call	_check_array_size
	addl	$4, %esp 	 ##pop argument back off
	incl	%eax
	imull	$4, %eax
	pushl	%eax
	call	malloc
	addl	$4, %esp 	 ##pop argument back off
	popl	%ebx
	movl	%ebx, (%eax)
	movl	%eax, _static_variable_1_a 	 ##initialize a
	movl	$1234567, %eax
	pushl	%eax
	movl	_static_variable_1_a, %eax 	 #fetch static a
	pushl	%eax
	movl	$0, %eax
	cmpl	$0, %eax
	jl	_error_array_bounds
	popl	%ebx
	cmpl	(%ebx), %eax
	jge	_error_array_bounds
	incl	%eax
	imull	$4, %eax
	addl	%ebx, %eax
	popl	%ebx
	movl	%ebx, (%eax)
	movl	_static_variable_1_a, %eax 	 #fetch static a
	pushl	%eax
	movl	$0, %eax
	cmpl	$0, %eax
	jl	_error_array_bounds
	popl	%ebx
	cmpl	(%ebx), %eax
	jge	_error_array_bounds
	incl	%eax
	imull	$4, %eax
	addl	%ebx, %eax
	movl	(%eax), %eax
	pushl	%eax
	movl	$1234567, %eax
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ1TRUE
.EQ1FALSE:
	movl	$0, %eax
	jmp	.EQ1END
.EQ1TRUE:
	movl	$1, %eax
.EQ1END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$123, %eax
	pushl	%eax
	movl	_static_variable_1_a, %eax 	 #fetch static a
	pushl	%eax
	movl	$200000, %eax
	cmpl	$0, %eax
	jl	_error_array_bounds
	popl	%ebx
	cmpl	(%ebx), %eax
	jge	_error_array_bounds
	incl	%eax
	imull	$4, %eax
	addl	%ebx, %eax
	popl	%ebx
	movl	%ebx, (%eax)
	movl	_static_variable_1_a, %eax 	 #fetch static a
	pushl	%eax
	movl	$200000, %eax
	cmpl	$0, %eax
	jl	_error_array_bounds
	popl	%ebx
	cmpl	(%ebx), %eax
	jge	_error_array_bounds
	incl	%eax
	imull	$4, %eax
	addl	%ebx, %eax
	movl	(%eax), %eax
	pushl	%eax
	movl	$123, %eax
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ2TRUE
.EQ2FALSE:
	movl	$0, %eax
	jmp	.EQ2END
.EQ2TRUE:
	movl	$1, %eax
.EQ2END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	pushl	$76
	call	malloc
	addl	$4, %esp 	 ##pop argument back off
	movl	%eax, _static_variable_2_s 	 ##initialize s
	movl	$123, %eax
	pushl	%eax
	movl	_static_variable_2_s, %eax 	 #fetch static s
	leal	18(%eax), %eax
	popl	%ebx
	movl	%ebx, (%eax)
	movl	$1234, %eax
	pushl	%eax
	movl	_static_variable_2_s, %eax 	 #fetch static s
	leal	0(%eax), %eax
	popl	%ebx
	movl	%ebx, (%eax)
	movl	_static_variable_2_s, %eax 	 #fetch static s
	leal	18(%eax), %eax
	movl	(%eax), %eax
	pushl	%eax
	movl	$123, %eax
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ3TRUE
.EQ3FALSE:
	movl	$0, %eax
	jmp	.EQ3END
.EQ3TRUE:
	movl	$1, %eax
.EQ3END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	_static_variable_2_s, %eax 	 #fetch static s
	leal	0(%eax), %eax
	movl	(%eax), %eax
	pushl	%eax
	movl	$1234, %eax
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ4TRUE
.EQ4FALSE:
	movl	$0, %eax
	jmp	.EQ4END
.EQ4TRUE:
	movl	$1, %eax
.EQ4END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$(_function_0_lambda), %eax
	movl	%eax, _static_variable_3_f_add 	 ##initialize f_add
	movl	$(_function_1_lambda), %eax
	movl	%eax, _static_variable_6_f_mul 	 ##initialize f_mul
	movl	$0, _static_variable_9_f 	 ##autoinitialize f as 0
	movl	_static_variable_3_f_add, %eax 	 #fetch static f_add
	pushl	%eax
	leal	_static_variable_9_f, %eax
	popl	%ebx
	movl	%ebx, (%eax)
	movl	$3, %eax
	pushl	%eax
	movl	$2, %eax
	pushl	%eax
	movl	_static_variable_9_f, %eax 	 #fetch static f
	call	*%eax
	addl	$8, %esp 	 #pop 2 arguments back off the stack
	pushl	%eax
	movl	$5, %eax
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ5TRUE
.EQ5FALSE:
	movl	$0, %eax
	jmp	.EQ5END
.EQ5TRUE:
	movl	$1, %eax
.EQ5END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	_static_variable_6_f_mul, %eax 	 #fetch static f_mul
	pushl	%eax
	leal	_static_variable_9_f, %eax
	popl	%ebx
	movl	%ebx, (%eax)
	movl	$3, %eax
	pushl	%eax
	movl	$2, %eax
	pushl	%eax
	movl	_static_variable_9_f, %eax 	 #fetch static f
	call	*%eax
	addl	$8, %esp 	 #pop 2 arguments back off the stack
	pushl	%eax
	movl	$6, %eax
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ6TRUE
.EQ6FALSE:
	movl	$0, %eax
	jmp	.EQ6END
.EQ6TRUE:
	movl	$1, %eax
.EQ6END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
.IF0CONDITION:
	movl	$1, %eax
	cmpl	$0, %eax
	je	.IF0END
	movl	$1, %eax
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
.IF0END:
.IF1CONDITION:
	movl	$0, %eax
	cmpl	$0, %eax
	je	.IF1END
	movl	$0, %eax
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
.IF1END:
.IF2CONDITION:
	movl	$1, %eax
	cmpl	$0, %eax
	je	.IF2ELSE
	movl	$1, %eax
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	jmp	.IF2END
.IF2ELSE:
	movl	$0, %eax
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
.IF2END:
.IF3CONDITION:
	movl	$0, %eax
	cmpl	$0, %eax
	je	.IF3ELSE
	movl	$0, %eax
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	jmp	.IF3END
.IF3ELSE:
	movl	$1, %eax
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
.IF3END:
	movl	$0, %eax
	movl	%eax, _static_variable_10_t 	 ##initialize t
.IF4CONDITION:
	movl	$2, %eax
	cmpl	$0, %eax
	je	.IF4END
	movl	$1, %eax
	pushl	%eax
	leal	_static_variable_10_t, %eax
	popl	%ebx
	movl	%ebx, (%eax)
.IF4END:
	movl	_static_variable_10_t, %eax 	 #fetch static t
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$13, %eax
	pushl	%eax
	movl	$13, %eax
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ7TRUE
.EQ7FALSE:
	movl	$0, %eax
	jmp	.EQ7END
.EQ7TRUE:
	movl	$1, %eax
.EQ7END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$-13, %eax
	pushl	%eax
	movl	$-13, %eax
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ8TRUE
.EQ8FALSE:
	movl	$0, %eax
	jmp	.EQ8END
.EQ8TRUE:
	movl	$1, %eax
.EQ8END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$14, %eax
	pushl	%eax
	movl	$13, %eax
	popl	%ebx
	cmpl	%ebx, %eax
	jne	.NEQ0TRUE
.NEQ0FALSE:
	movl	$0, %eax
	jmp	.NEQ0END
.NEQ0TRUE:
	movl	$1, %eax
.NEQ0END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$-13, %eax
	pushl	%eax
	movl	$13, %eax
	popl	%ebx
	cmpl	%ebx, %eax
	jne	.NEQ1TRUE
.NEQ1FALSE:
	movl	$0, %eax
	jmp	.NEQ1END
.NEQ1TRUE:
	movl	$1, %eax
.NEQ1END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$14, %eax
	pushl	%eax
	movl	$13, %eax
	popl	%ebx
	cmpl	%ebx, %eax
	jl	.LESS0TRUE
.LESS0FALSE:
	movl	$0, %eax
	jmp	.LESS0END
.LESS0TRUE:
	movl	$1, %eax
.LESS0END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$13, %eax
	pushl	%eax
	movl	$14, %eax
	popl	%ebx
	cmpl	%ebx, %eax
	jg	.GREATER0TRUE
.GREATER0FALSE:
	movl	$0, %eax
	jmp	.GREATER0END
.GREATER0TRUE:
	movl	$1, %eax
.GREATER0END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$13, %eax
	pushl	%eax
	movl	$-13, %eax
	popl	%ebx
	cmpl	%ebx, %eax
	jl	.LESS1TRUE
.LESS1FALSE:
	movl	$0, %eax
	jmp	.LESS1END
.LESS1TRUE:
	movl	$1, %eax
.LESS1END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$-13, %eax
	pushl	%eax
	movl	$13, %eax
	popl	%ebx
	cmpl	%ebx, %eax
	jg	.GREATER1TRUE
.GREATER1FALSE:
	movl	$0, %eax
	jmp	.GREATER1END
.GREATER1TRUE:
	movl	$1, %eax
.GREATER1END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$5, %eax
	pushl	%eax
	movl	$2, %eax
	pushl	%eax
	movl	$3, %eax
	popl	%ebx
	addl	%ebx, %eax
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ9TRUE
.EQ9FALSE:
	movl	$0, %eax
	jmp	.EQ9END
.EQ9TRUE:
	movl	$1, %eax
.EQ9END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$1, %eax
	pushl	%eax
	movl	$-2, %eax
	pushl	%eax
	movl	$3, %eax
	popl	%ebx
	addl	%ebx, %eax
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ10TRUE
.EQ10FALSE:
	movl	$0, %eax
	jmp	.EQ10END
.EQ10TRUE:
	movl	$1, %eax
.EQ10END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$-5, %eax
	pushl	%eax
	movl	$12, %eax
	pushl	%eax
	movl	$7, %eax
	popl	%ebx
	subl	%ebx, %eax
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ11TRUE
.EQ11FALSE:
	movl	$0, %eax
	jmp	.EQ11END
.EQ11TRUE:
	movl	$1, %eax
.EQ11END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$5, %eax
	pushl	%eax
	movl	$-12, %eax
	pushl	%eax
	movl	$-7, %eax
	popl	%ebx
	subl	%ebx, %eax
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ12TRUE
.EQ12FALSE:
	movl	$0, %eax
	jmp	.EQ12END
.EQ12TRUE:
	movl	$1, %eax
.EQ12END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$-20, %eax
	pushl	%eax
	movl	$5, %eax
	pushl	%eax
	movl	$-4, %eax
	popl	%ebx
	imull	%ebx, %eax
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ13TRUE
.EQ13FALSE:
	movl	$0, %eax
	jmp	.EQ13END
.EQ13TRUE:
	movl	$1, %eax
.EQ13END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$-7, %eax
	pushl	%eax
	movl	$-4, %eax
	pushl	%eax
	movl	$30, %eax
	popl	%ebx
	cdq
	idivl	%ebx
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ14TRUE
.EQ14FALSE:
	movl	$0, %eax
	jmp	.EQ14END
.EQ14TRUE:
	movl	$1, %eax
.EQ14END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$-7, %eax
	pushl	%eax
	movl	$4, %eax
	pushl	%eax
	movl	$-30, %eax
	popl	%ebx
	cdq
	idivl	%ebx
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ15TRUE
.EQ15FALSE:
	movl	$0, %eax
	jmp	.EQ15END
.EQ15TRUE:
	movl	$1, %eax
.EQ15END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$2, %eax
	pushl	%eax
	movl	$5, %eax
	pushl	%eax
	movl	$12, %eax
	popl	%ebx
	cdq
	idivl	%ebx
	movl	%edx, %eax
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ16TRUE
.EQ16FALSE:
	movl	$0, %eax
	jmp	.EQ16END
.EQ16TRUE:
	movl	$1, %eax
.EQ16END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$0, %eax
	movl	%eax, _static_variable_11_i 	 ##initialize i
	leal	_static_variable_11_i, %eax
	incl	(%eax)
	movl	$1, %eax
	pushl	%eax
	movl	_static_variable_11_i, %eax 	 #fetch static i
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ17TRUE
.EQ17FALSE:
	movl	$0, %eax
	jmp	.EQ17END
.EQ17TRUE:
	movl	$1, %eax
.EQ17END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	leal	_static_variable_11_i, %eax
	decl	(%eax)
	movl	$0, %eax
	pushl	%eax
	movl	_static_variable_11_i, %eax 	 #fetch static i
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ18TRUE
.EQ18FALSE:
	movl	$0, %eax
	jmp	.EQ18END
.EQ18TRUE:
	movl	$1, %eax
.EQ18END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$3, %eax
	pushl	%eax
	movl	$2, %eax
	pushl	%eax
	movl	$1, %eax
	pushl	%eax
	call	_function_0_ftest
	addl	$8, %esp 	 #pop 2 arguments back off the stack
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ19TRUE
.EQ19FALSE:
	movl	$0, %eax
	jmp	.EQ19END
.EQ19TRUE:
	movl	$1, %eax
.EQ19END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$0, _static_variable_16_a 	 ##autoinitialize a as 0
	movl	$0, %eax
	pushl	%eax
	leal	_static_variable_16_a, %eax
	popl	%ebx
	movl	%ebx, (%eax)
	movl	$0, %eax
	pushl	%eax
	movl	_static_variable_16_a, %eax 	 #fetch static a
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ20TRUE
.EQ20FALSE:
	movl	$0, %eax
	jmp	.EQ20END
.EQ20TRUE:
	movl	$1, %eax
.EQ20END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$0, _static_variable_17_b 	 ##autoinitialize b as 0
	movl	$0, %eax
	pushl	%eax
	leal	_static_variable_17_b, %eax
	popl	%ebx
	movl	%ebx, (%eax)
	movl	$0, %eax
	pushl	%eax
	movl	_static_variable_17_b, %eax 	 #fetch static b
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ21TRUE
.EQ21FALSE:
	movl	$0, %eax
	jmp	.EQ21END
.EQ21TRUE:
	movl	$1, %eax
.EQ21END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$0, %eax
	pushl	%eax
	movl	_static_variable_16_a, %eax 	 #fetch static a
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ22TRUE
.EQ22FALSE:
	movl	$0, %eax
	jmp	.EQ22END
.EQ22TRUE:
	movl	$1, %eax
.EQ22END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$0, _static_variable_18_b 	 ##autoinitialize b as 0
	movl	$1, %eax
	pushl	%eax
	leal	_static_variable_18_b, %eax
	popl	%ebx
	movl	%ebx, (%eax)
	movl	$1, %eax
	pushl	%eax
	movl	_static_variable_18_b, %eax 	 #fetch static b
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ23TRUE
.EQ23FALSE:
	movl	$0, %eax
	jmp	.EQ23END
.EQ23TRUE:
	movl	$1, %eax
.EQ23END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$0, %eax
	pushl	%eax
	movl	_static_variable_17_b, %eax 	 #fetch static b
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ24TRUE
.EQ24FALSE:
	movl	$0, %eax
	jmp	.EQ24END
.EQ24TRUE:
	movl	$1, %eax
.EQ24END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$0, %eax
	movl	%eax, _static_variable_19_i 	 ##initialize i
.WHILE0CONDITION:
	movl	$10, %eax
	pushl	%eax
	movl	_static_variable_19_i, %eax 	 #fetch static i
	popl	%ebx
	cmpl	%ebx, %eax
	jne	.NEQ2TRUE
.NEQ2FALSE:
	movl	$0, %eax
	jmp	.NEQ2END
.NEQ2TRUE:
	movl	$1, %eax
.NEQ2END:
	cmpl	$0, %eax
	je	.WHILE0EXIT
	movl	$1, %eax
	pushl	%eax
	movl	_static_variable_19_i, %eax 	 #fetch static i
	popl	%ebx
	addl	%ebx, %eax
	pushl	%eax
	leal	_static_variable_19_i, %eax
	popl	%ebx
	movl	%ebx, (%eax)
	jmp	.WHILE0CONDITION
.WHILE0EXIT:
	movl	$10, %eax
	pushl	%eax
	movl	_static_variable_19_i, %eax 	 #fetch static i
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ25TRUE
.EQ25FALSE:
	movl	$0, %eax
	jmp	.EQ25END
.EQ25TRUE:
	movl	$1, %eax
.EQ25END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$0, %eax
	movl	%eax, _static_variable_20_sum 	 ##initialize sum
	movl	$0, %eax
	movl	%eax, _static_variable_21_i 	 ##initialize i
.FOR0CONDITION:
	movl	$10, %eax
	pushl	%eax
	movl	_static_variable_21_i, %eax 	 #fetch static i
	popl	%ebx
	cmpl	%ebx, %eax
	jne	.NEQ3TRUE
.NEQ3FALSE:
	movl	$0, %eax
	jmp	.NEQ3END
.NEQ3TRUE:
	movl	$1, %eax
.NEQ3END:
	cmpl	$0, %eax
	je	.FOR0EXIT
	movl	_static_variable_21_i, %eax 	 #fetch static i
	pushl	%eax
	movl	_static_variable_20_sum, %eax 	 #fetch static sum
	popl	%ebx
	addl	%ebx, %eax
	pushl	%eax
	leal	_static_variable_20_sum, %eax
	popl	%ebx
	movl	%ebx, (%eax)
	leal	_static_variable_21_i, %eax
	incl	(%eax)
	jmp	.FOR0CONDITION
.FOR0EXIT:
	movl	$45, %eax
	pushl	%eax
	movl	_static_variable_20_sum, %eax 	 #fetch static sum
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ26TRUE
.EQ26FALSE:
	movl	$0, %eax
	jmp	.EQ26END
.EQ26TRUE:
	movl	$1, %eax
.EQ26END:
	pushl	%eax
	call	assert
	addl	$4, %esp 	 #pop 1 arguments back off the stack
	movl	$23, %eax
	movl	%eax, _static_variable_22_lookatme 	 ##initialize lookatme
	movl	$23, %eax
	pushl	%eax
	movl	_static_variable_22_lookatme, %eax 	 #fetch static lookatme
	popl	%ebx
	cmpl	%ebx, %eax
	je	.EQ27TRUE
.EQ27FALSE:
	movl	$0, %eax
	jmp	.EQ27END
.EQ27TRUE:
	movl	$1, %eax
.EQ27END:
	pushl	%eax
	call	assert
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
	cdq
	idivl	%ebx
	addl	$0, %esp 	 #free 0 local variables
	leave
	ret
.type _function_0_lambda @function
_function_0_lambda:
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
.type _function_1_lambda @function
_function_1_lambda:
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
.type _function_0_ftest @function
_function_0_ftest:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$8, %esp 	 #reserve 2 local variables
	movl	$0, -4(%ebp) 	 ##autoinitialize a as 0
	movl	$0, -8(%ebp) 	 ##autoinitialize b as 0
	movl	8(%ebp), %eax 	 #fetch argument x
	pushl	%eax
	leal	-4(%ebp), %eax
	popl	%ebx
	movl	%ebx, (%eax)
	movl	12(%ebp), %eax 	 #fetch argument y
	pushl	%eax
	leal	-8(%ebp), %eax
	popl	%ebx
	movl	%ebx, (%eax)
	movl	-8(%ebp), %eax 	 #fetch local b
	pushl	%eax
	movl	-4(%ebp), %eax 	 #fetch local a
	popl	%ebx
	addl	%ebx, %eax
	addl	$8, %esp 	 #free 2 local variables
	leave
	ret
