#!/bin/bash

input_java_src_dir="$1"
jar_file="$2".jar

javac -classpath `hadoop classpath` "$input_java_src_dir"/*.java
jar cvf "$jar_file" "$input_java_src_dir"/*.class
chmod 777 "$jar_file"
