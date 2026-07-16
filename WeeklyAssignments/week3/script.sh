#!/bin/bash

echo "---- Name and Department ----"
awk -F',' '{print $1 " " $2}' data.txt
echo

echo "---- Engineering Employees ----"
awk -F',' '$2=="Engineering" {print $0}' data.txt
echo

echo "---- Employee Payment ----"
awk -F',' -v cost=0 '{cost=$3*$4} {print $1 " pay is: $" cost}' data.txt 
echo

echo "---- Row and Field Count ----"
awk -F',' '{print NR " (" NF  " Fields) : " $0}' data.txt 
echo

echo "---- Total Engineering Cost ----"
awk -F',' -v total=0 '{ if($2 == "Engineering") total += $3*$4} END {print "Total Engineering Payroll: $" total}' data.txt
