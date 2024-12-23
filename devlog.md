# Development Log
## date: 11/25/24
        
    The workspace contains the following folders:

    - `src`: with core classes like `BTree`, `BTreeNode`, and `BTreeBlock` here.
    - `lib`: store any external dependencies 
    - `bin`: Java `.class` files will be generated here by default 

        ### Updates 
        - read the instructions 
        - set up workspace 
        - started pseudocode and planning 

        ### Next Steps
        - start coding logic and basic outline of files 
        - create/open file functionailty

## date: 11/26/24
    
    ### Updates
    - connected project to github
    - **`BTree.java`**: Contains the main implementation of the B-tree data structure. This file is responsible for managing node insertions, splits, and the overall structure of the tree.
    - **`BTreeNode.java`**: Defines a single node in the B-tree, which includes methods for inserting keys, values, and splitting the node when it reaches its capacity.
    - **`BTreeBlock.java`**: This file provides the functionality for managing data blocks in the B-tree. It includes methods to insert key-value pairs into a block and check if the block is full.
    - **`BTreeTest.java`**: A simple test class for verifying the functionality of the B-tree by running sample insertions and testing the tree structure.
    - pushed commit to github 

    ### Next Steps
    - insert issues 
    - initial testing
    - debug

## date: 11/27/24
       
    ### Updates 
    - create/open/insert/quit seem to work

    ### Next Steps
    - insert logic issues 
    - BTreeTest.java not outputting expected results 

## date: 11/28/24
       
    ### Updates 
    - created a Constants class
    - fixed and tested open/insert/search/load/print/extract/quit commands  
    - program crashes on index out of bounds issue for some inputs

    ### Next Steps
    - testing
    - index out of bounds issue

## date: 11/28/24
       
    ### Updates 
    - created a Constants class for BTree implemnentation
    - fixed and tested open/insert/search/load/print/extract/quit commands  
    - index out of bounds issue resolved
    -pushed code

    ### Next Steps
    - testing
    - minor bug fixes
    - push code after issues resolved
    - load command issue, values not being read

## date: 11/29/24
       
    ### Updates 
    - tested more cases
    - added comments 
    - added a 2 second delay for exiting program
    - fixed minor bug issues
    - fixed load command 
    - finalize README file
    
    ### Next Steps
    - final testing 
    - push final code
    - work on 