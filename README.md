Overview
========

This program compiles an old version of Xilog Plus GCode XXL files in PGM
binary format. It is written to drive a SCM Tech 100 CNC equipped with a
Tria 4000 controller.

The SCM Tech 100 was also manufactured with other controllers which may or
may not be compatible with this compiler. Feel free to drop me a email if
it works for you on another controller or CNC.

How to Build
============

Building requires a JDK and Maven 3.1. Then you just have to launch Maven
at the command line prompt:

	mvn package


How to compile an XXL file
==========================

After building, go into `target` and launch:

    java -jar gcode-compiler-VERSION.jar -c FILE.XXL

TODO
====

Code quality doesn't really meet my usual expectations, but it works ;) It's
at least correctly tested... Pull request to fix bugs and to enhance code quality
are more than welcome (if someone ever need this code !!!)

LICENCE
=======

Copyright (C) 2013 Yoann Dubreuil

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
