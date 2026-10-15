## 1. gzip
gzip compresses files using the DEFLATE compression algorithm

### Compress a file
gzip report.txt
### Decompress it
gzip -d report.txt.gz
### Keep the original file while compressing
gzip -k report.txt
### Display compressed file info
gzip -l report.txt.gz

### gzip compresses data, usually one file or a data stream.
### tar bundles multiple files and directories into an archive.


## 2. find
find searches for a file or directory into a directory hierarchy based on filename, type, size, modification time, and permissions.

### find <path> <conditions> <actions>

### Find all files named notes.txt under the current directory
find . -name "notes.txt"
### Find all .c files
find . -type f -name "*.c"
### Find directories named build
find . -type d -name "build"
### Find files larger than 100 MB
find . -type f -size +100M
### Find files modified in the last 24 hours
find . -type f -mtime -1
### Find files with a specific permission mode
find . -type f -perm 644

## xargs — Build command arguments from input
xargs reads items from standard input and passes them as arguments to another command

### Find .c files and pass their paths to wc
find . -type f -name "*.c" -print0 | xargs -0 wc -l

Here:
- find discovers C files.
- -print0 separates filenames with a null character, safely handling    spaces and newlines.
- xargs -0 reads those null-separated filenames.
- wc -l counts lines.

## lscpu — Display CPU information
lscpu displays information about the CPU architecture and topology, usually obtained from /proc/cpuinfo, sysfs, and system information provided by the kernel.


## sed — Stream editor
sed processes text line by line and can perform substitutions, deletions, insertions, and other transformations.

### sed 'command' filename

### Replace the first occurrence of "cat" on each line
sed 's/cat/dog/' file.txt
### Replace all occurrences on each line
sed 's/cat/dog/g' file.txt
### Print only lines containing "error"
sed -n '/error/p' log.txt
### Delete lines containing "debug"
sed '/debug/d' log.txt
### Print only lines 5 through 10
sed -n '5,10p' file.txt

### Use -i carefully, because it modifies the file.

## awk — Text processing and field extraction
awk is a pattern-scanning and text-processing language.
It is especially useful for extracting columns, filtering records, and performing calculations.
By default, awk treats whitespace as a field separator.

## students.txt
Aman 85
Riya 92
Karan 76

### Print every line
awk '{print $0}' students.txt
### Print the first column
awk '{print $1}' students.txt
### Print the second column
awk '{print $2}' students.txt
### Print names with marks greater than 80
ak '$2 > 80 {print $1, $2}' students.txt
### Calculate the average of the second column
ak '{sum += $2; count++} END {if (count) print sum/count}' students.txt

### Important awk concepts
Expression	Meaning
$0	        Entire current record, usually a line
$1	        First field
$2	        Second field
NR	        Current record/line number read
NF	        Number of fields in the current record
BEGIN	    Executes before normal input processing
END	        Executes after input processing finishes

### You can also specify a delimiter:
awk -F ',' '{print $1, $3}' data.csv


## stat — Display file metadata
stat displays metadata about a file or directory, including its size, inode number, permissions, ownership, and timestamps.

### Display metadata for a file
stat report.txt
### Display metadata for a directory
stat myfolder

#### stat Main.java 
File: Main.java
size: 1314            Blocks: 8          IO Block: 4096   regular file
Device: 0,71    Inode: 562949953780918  Links: 1
Access: (0777/-rwxrwxrwx)  Uid: ( 1000/ketan1317)   Gid: ( 1000/ketan1317)
Access: 2026-10-13 14:13:34.129615500 +0000
Modify: 2026-10-13 14:13:34.129615500 +0000
Change: 2026-10-13 14:13:34.129615500 +0000
 Birth: -