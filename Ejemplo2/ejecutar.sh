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
echo "Lanzando el cliente"
echo
java -cp . -Djava.security.policy=server.policy Cliente_Ejemplo_Multi_Threaded localhost 5
