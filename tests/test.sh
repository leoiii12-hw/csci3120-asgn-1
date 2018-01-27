#!/usr/bin/env bash

cd ../src

#    java Main < ../tests/BinarySearch.java
#    java Main < ../tests/BinaryTree.java
#    java Main < ../tests/BubbleSort.java
#    java Main < ../tests/Factorial.java
#    java Main < ../tests/LinearSearch.java
#    java Main < ../tests/LinkedList.java
#    java Main < ../tests/QuickSort.java
#    java Main < ../tests/TreeVisitor.java

# Errors Handling 1
    java Main < ../tests/specs/EOFBeforeTerminationOfMultiLineComment.java

# Errors Handling 2
    java Main < ../tests/specs/UnmatchedMultiLineComment.java

# Errors Handling 3
    java Main < ../tests/specs/InvalidCharacter.java