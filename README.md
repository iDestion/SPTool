#SPTool
Tool to try and find advice from (physiotherapy) transcripts.

###Tutorial
Run tool with program arguments containing strings that define the terms to be searched. File input containing transcripts to be entered into `/src/in.txt`

###Input
`/src/in.txt` containing transcript, turns seperated by newlines and turns starting with `speaker:`

###Output
Depending on the `final String JSON` active in Main.java, the program writes a JSON file, TXT file or textual output to the console.

###Numbering turns
By executing the TurnCounter psvm, a seperate program will create a file that contains the turn numbers used in scoring, followed by the text of the turn, to be able to compare scores to actual turns easily.

###Dependencies
Tool depends on the `Json.Simple` library. Jar file for json-simple can be found in the lib folder included in the project. 
