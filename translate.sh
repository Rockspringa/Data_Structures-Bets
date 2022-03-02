rm ./src/main/java/edu/mooncoder/model/analizer/lexic/Lexer.java
rm -r ./src/main/java/edu/mooncoder/model/analizer/syntax/*

java -jar C:/Cup/java-cup-11b.jar -parser Parser -symbols Tokens ./src/main/res/sintaxis.cup
java -jar C:/JFlex/lib/jflex-full-1.8.2.jar -d ./src/main/java/edu/mooncoder/model/analizer/lexic ./src/main/res/lexico.jflex

mv ./*.java ./src/main/java/edu/mooncoder/model/analizer/syntax/