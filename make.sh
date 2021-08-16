#!/bin/sh

# shellcheck disable=SC2038
find -name '*.java' | xargs javac -d "out"
java -cp out Battleship
