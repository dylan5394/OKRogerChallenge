# Solution

## Running:

1. Clone the repository
2. Open as new eclipse workspace and run the app 

## Assumptions:

Words are not different purely based on case. For example, it assumes the word "PROGRAMMING" is the same as the word "programming."

Words separated by a hyphen are parsed for their individual words. For example the string "state-of-the-art" would increment the counts of "state", "of", "the", and "art" all by 1.

Apostrophes and all other non alphabetical characters are parsed out of the file. This means that if the file contains the word "I'll" and "ill" it will think they are the same word. This is a limitation of the program.

## Notes:

The program uses a trie to perform prefix lookups. The prefix "th" is hard coded into the program. If ran, the program will output all words beginning with "th" and the number of times they occur to the console.

The test file given in the link in the problem description pdf is included in the project in the resources folder. The output generated when the program runs this file can be found in output.txt. 
