	.file	"problem.c"
	.section	.rodata
.LC0:
	.string	"%d"
	.text
.globl main
	.type	main, @function
main:
	leal	4(%esp), %ecx
	andl	$-16, %esp
	pushl	-4(%ecx)
	pushl	%ebp
	movl	%esp, %ebp
	pushl	%ecx
	subl	$36, %esp
	movl	$1, -16(%ebp)
	movl	$2, -20(%ebp)
	movl	$2, -8(%ebp)
	jmp	.L2
.L4:
	movl	-8(%ebp), %eax
	movl	%eax, (%esp)
	call	isprime
	testl	%eax, %eax
	je	.L3
	movl	-8(%ebp), %eax
	movl	%eax, -12(%ebp)
	addl	$1, -16(%ebp)
.L3:
	addl	$1, -8(%ebp)
.L2:
	cmpl	$10002, -16(%ebp)
	jne	.L4
	movl	-12(%ebp), %eax
	movl	%eax, 4(%esp)
	movl	$.LC0, (%esp)
	call	printf
	addl	$36, %esp
	popl	%ecx
	popl	%ebp
	leal	-4(%ecx), %esp
	ret
	.size	main, .-main
.globl isprime
	.type	isprime, @function
isprime:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$20, %esp
	movl	$1, -8(%ebp)
	movl	$2, -4(%ebp)
	jmp	.L7
.L9:
	movl	8(%ebp), %edx
	movl	%edx, %eax
	sarl	$31, %edx
	idivl	-4(%ebp)
	movl	%edx, %eax
	testl	%eax, %eax
	jne	.L8
	movl	$0, -8(%ebp)
	movl	8(%ebp), %eax
	movl	%eax, -4(%ebp)
.L8:
	addl	$1, -4(%ebp)
.L7:
	movl	-4(%ebp), %eax
	cmpl	8(%ebp), %eax
	jl	.L9
	movl	-8(%ebp), %eax
	leave
	ret
	.size	isprime, .-isprime
	.ident	"GCC: (Ubuntu 4.3.3-5ubuntu4) 4.3.3"
	.section	.note.GNU-stack,"",@progbits
