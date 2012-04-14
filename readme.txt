------------------------------------------------------------------
Managing Data on the World Wide Web - Assignment 2 (Java and XML)
------------------------------------------------------------------

+ Instruction on how to run the program:
-----------------------------------------
<program_name> <local path to dblp.xml file> <regular expression>

In case you're running this project from eclipse then modify the
program parameters in the Run Configuration to be

<local path to dblp.xml file> <regular expression>

+ Example:
-----------
Let's say I'm username alex and have the dblp.xml file on local path at
/home/alex/dblp.xml

And want the statistics for all authors whose name begins with 'Mich'.

So, the program parameters I enter at the Run Configuration of eclipse
should look like:

/home/alex/dblp.xml Mich.*
