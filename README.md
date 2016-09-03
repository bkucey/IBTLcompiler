Welcome to the IBTLcompiler project.  This is a project I did for school in CS 480, translators.  It takes the Itty Bitty Teaching Language (IBTL) and translates it into valid Gforth 0.7.0 code that can be run with the Gforth interpreter.
See the IBTL grammar in the home directory as a reference for IBTL.
See the Home of Gforth for the Gforth manual and executables at http://www.complang.tuwien.ac.at/forth/gforth/.

This translator has three stages.  The first stage is the tokenizer that converts the IBTL code into a list of tokens (keywords and variable names)  in memory.  The second state is a parser that organizes the tokens into an unambiguous syntactic tree, organizing all the statements and expressions.  In the last step, the tree is printed out into Gforth code.

As a test driven development project, the test directory shows the use cases for the source code.

Use compile.bash to compile the project into a bin/ directory.  Use makeJar.bash to make an executable jar out of the .class files in bin/.

Usage$: java -jar IBTLcompiler.jar input-file
puts Gforth code to stdout.
