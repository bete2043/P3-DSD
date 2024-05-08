#!/bin/sh -e

echo
echo "Lanzando el ligador de RMI..."
echo
rmiregistry  &

echo
echo "Compilando con javac..."
echo
javac *.java

echo
echo "Lanzando el servidor..."
echo
java -cp . -Djava.rmi.server.codebase=file:./ -Djava.rmi.server.hostname=localhost -Djava.security.policy=server.policy Ejemplo &

echo
echo "Lanzando el primer cliente"
echo
java -cp . -Djava.security.policy=server.policy Cliente_Ejemplo localhost 0

echo
echo "Lanzando el segundo cliente"
echo
java -cp . -Djava.security.policy=server.policy Cliente_Ejemplo localhost 1
