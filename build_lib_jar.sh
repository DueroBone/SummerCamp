#!/bin/fish

javac lib/**.java
jar cf lib/library.jar lib/**.class
find lib -type f -name "*.class" -delete
