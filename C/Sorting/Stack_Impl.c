#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int *data;
    int size;
    int capacity;
} Stack;

Stack *createStack() {
    Stack *stack = (Stack *)malloc(sizeof(Stack));
    stack->capacity = 4;
    stack->data = (int *)malloc(sizeof(int) * stack->capacity);
    stack->size = 0;
    return stack;
}

bool is_empty(Stack *stack) { return stack->size == 0; }

void push_to_stack(Stack *stack, int val) {
    if (stack->size == stack->capacity) {
        stack->capacity *= 2;
        stack->data =
            (int *)realloc(stack->data, sizeof(int) * stack->capacity);
    }

    stack->data[stack->size] = val;
    stack->size++;
}

// NOTE: Caller is responsible for underflow check,
// Calling on empty stack will result in undefined behaviour.
int peek(Stack *stack) { return stack->data[stack->size - 1]; }

// NOTE: Caller is responsible for underflow check,
// Calling on empty stack will result in undefined behaviour.
int pop_from_stack(Stack *stack) {
    int top = peek(stack);
    stack->size--;
    return top;
}

void cleanup(Stack *stack) {
    free(stack->data);
    free(stack);
}
