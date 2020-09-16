Java Programming: Arrays, Lists, and Structured Data

Course  **Java Programming: Arrays, Lists, and Structured Data**  by University of Duke University https://www.coursera.org/learn/java-programming-arrays-lists-data/

Course certificate:
https://www.coursera.org/account/accomplishments/verify/SVEBRV4R9GDG

Application includes follow functionality:
- *caesarcipher* - implementation of the Caesar cipher and Caesar cipher cracking (or breaking)
- *vigenere* - implementation of the Vigenère cipher and Vigenère cipher cracking (or breaking)
- *gladlibs* - generates a story using the template file, replacing placeholder words such as <noun> by looking for a random word of that type.
- *weblogs* - reading and processing of web logs

Example:

encrypt(“FIRST LEGION ATTACK EAST FLANK!”, 23)
Caesar Cipher algorithm should return the string encrypted with the key = 23
“CFOPQ IBDFLK XQQXZH BXPQ CIXKH!”

Then create a CaesarCipher object within decrypt: CaesarCipher(26 - key); and call encrypt method.

encrypt(“Meet Me At Dawn”, "DICE")
Vigenère cipher algorithm should return the string encrypted with the key = "DICE" - aaray of ints - "3824"
“Pmgx Ug Db Hdep”

to decrypt -  to find the key the most common character in the language of the message should be found,
to find the shift for each index in the key  - string should be sliced. And we get the key (which is an array of integers).
