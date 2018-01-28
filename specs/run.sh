#!/usr/bin/env bash

declare -a specs=(
    "BinarySearch.java"
    "BinaryTree.java"
    "BubbleSort.java"
    "Factorial.java"
    "LinearSearch.java"
    "LinkedList.java"
    "QuickSort.java"
    "BubbleSort.java"
    "TreeVisitor.java"
    "MultiLineCommentUnexpectedEOF.java"
    "UnmatchedMultiLineComment.java"
    "InvalidCharacter.java"
)

for i in "${specs[@]}"
do
   java Asgn1 < "../specs/$i"
   # or do whatever with individual element of the array
done