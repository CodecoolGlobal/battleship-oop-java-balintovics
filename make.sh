#!/bin/sh

find -name '*.java' | xargs javac -d "cica"
java -cp cica Battleship
