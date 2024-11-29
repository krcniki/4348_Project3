#B-Tree Index File Manager
    ###This project implements a B-tree-based index file manager in Java. The program provides functionality for creating and managing index files, inserting and searching key-value pairs, and loading or extracting data from files.

##Features
    - Create and Open Index Files: Create new index files or open existing ones.
    - Insert Key-Value Pairs: Insert and store key-value pairs in a B-tree.
    - Search Keys: Efficiently search for keys in the B-tree.
    Load and Extract Data: Load data from files and extract it to external files.
    - Exit: The program includes a 2-second exit delay with a message.

##Usage
    ###After running the program, you will be prompted to enter commands. Here are the available options:

        create: Create a new index file.
        open: Open an existing index file.
        insert: Insert a key-value pair into the B-tree.
        search: Search for a key in the B-tree.
        load: Load key-value pairs from a file.
        print: Print all key-value pairs in the tree.
        extract: Extract all key-value pairs to a file.
        quit: Exit the program (with a 2-second delay).

##Additional Output test Files
    - index.txt: sample file creation 
    - index_extracted.txt: sample file extracted from index.txt
    - load_into.txt: sample file in which index.txt is loaded into

##Development Log
###A development log is maintained throughout the project to track progress named 'devlog.md'. This file documents challenges faced, and outlines solutions. The log details important updates, bug fixes, and feature implementations.

##Running the Program
    ##java version used 
        java version "1.8.0_391"
        Java(TM) SE Runtime Environment (build 1.8.0_391-b13)
        Java HotSpot(TM) Client VM (build 25.391-b13, mixed mode)

    ##Commands
        - enter src directory (i.e. "cd src")
        - Compile command: javac -d ../bin -source 1.8 -target 1.8 *.java
        - exit src directory ("cd ..")
        - Run Main command: java -cp bin Main
        