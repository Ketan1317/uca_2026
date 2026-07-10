# 📂 Linux File Descriptors & Low-Level File I/O in C

A beginner-friendly guide to understanding **File Descriptors (FDs)**, **system calls**, and low-level file operations in Linux using C.

---

## 📚 Table of Contents

- [File Descriptors](#-file-descriptors-fd)
- [Standard File Descriptors](#-standard-file-descriptors)
- [`<unistd.h>`](#-unistdh)
- [`<fcntl.h>`](#-fcntlh)
- [`open()`](#-open)
- [`read()`](#-read)
- [`write()`](#-write)
- [`close()`](#-close)
- [Reading Files in Chunks](#-reading-files-in-chunks)
- [System Call Flow](#-system-call-flow)
- [Example Program](#-example-program)
- [Key Takeaways](#-key-takeaways)

---

# 📖 File Descriptors (FD)

A **File Descriptor (FD)** is a **non-negative integer** that identifies an **open file or I/O resource** within a process.

Every time a process opens a file, Linux returns a file descriptor that is used for reading, writing, seeking, and closing the file.

### How `open()` Works

```text
open()
    │
    ▼
Kernel locates the file
    │
    ▼
Checks permissions
    │
    ▼
Creates an Open File Object
    │
    ▼
Returns a File Descriptor (FD)
```

Example:

```c
int fd = open("notes.txt", O_RDONLY);
```

If successful:

```text
fd = 3
```

(The first three descriptors are already reserved.)

---

## What the Kernel Stores

When a file is opened, Linux creates an **Open File Object** containing:

- Current file offset
- Read/Write mode
- File status flags
- Inode information
- Reference count

Your program only receives an **integer FD**, while the kernel manages all the metadata.

---

# 🖥 Standard File Descriptors

Every process starts with three open file descriptors.

| FD | Name | Resource |
|----|------|----------|
| 0 | `stdin` | Keyboard |
| 1 | `stdout` | Terminal Output |
| 2 | `stderr` | Error Output |

Example FD Table

| FD | Resource |
|----|----------|
| 0 | stdin |
| 1 | stdout |
| 2 | stderr |
| 3 | a.txt |
| 4 | b.txt |
| 5 | c.txt |

Linux always tries to assign the **lowest available descriptor**.

Example:

```c
close(4);

int fd = open("new.txt", O_RDONLY);
```

The new file will most likely receive:

```text
fd = 4
```

---

# 📦 `<unistd.h>`

The primary POSIX header for **low-level system calls**.

Common functions:

```c
read()
write()
close()
fork()
exec()
pipe()
dup()
dup2()
lseek()
getcwd()
chdir()
```

---

# 📦 `<fcntl.h>`

Contains declarations for:

- `open()`
- File control operations
- File opening flags

Example:

```c
int fd = open(
    "a.txt",
    O_WRONLY | O_CREAT | O_TRUNC,
    0644
);
```

Return values

| Value | Meaning |
|-------|---------|
| `>=0` | Success |
| `-1` | Error |

---

## Common File Opening Flags

| Flag | Description |
|------|-------------|
| `O_RDONLY` | Read only |
| `O_WRONLY` | Write only |
| `O_RDWR` | Read & Write |
| `O_CREAT` | Create file if missing |
| `O_APPEND` | Append to end |
| `O_TRUNC` | Erase existing contents |
| `O_EXCL` | Fail if file exists (used with `O_CREAT`) |

Flags are combined using:

```c
|
```

Example:

```c
O_WRONLY | O_CREAT | O_APPEND
```

---

# 📥 `read()`

Reads bytes from a file descriptor.

### Syntax

```c
char buffer[100];

ssize_t bytesRead = read(fd, buffer, 100);
```

### Parameters

| Parameter | Description |
|-----------|-------------|
| `fd` | File Descriptor |
| `buffer` | Destination buffer |
| `100` | Maximum bytes to read |

### Return Value

| Value | Meaning |
|-------|---------|
| `>0` | Bytes successfully read |
| `0` | End of File (EOF) |
| `-1` | Error |

---

# 📤 `write()`

Writes bytes to a file or output stream.

### Syntax

```c
ssize_t bytesWritten = write(fd, buffer, n);
```

### Parameters

| Parameter | Description |
|-----------|-------------|
| `fd` | File Descriptor |
| `buffer` | Source buffer |
| `n` | Number of bytes |

### Return Value

| Value | Meaning |
|-------|---------|
| `>0` | Bytes written |
| `-1` | Error |

Example:

```c
write(1, "Hello\n", 6);
```

Since

```text
1 → stdout
```

the text appears on the terminal.

---

# ❌ `close()`

Closes an open file descriptor.

### Syntax

```c
close(fd);
```

### Purpose

- Releases kernel resources
- Closes the file
- Makes the descriptor reusable

### Return Value

| Value | Meaning |
|-------|---------|
| `0` | Success |
| `-1` | Error |

---

# 📚 Reading Files in Chunks

Instead of loading an entire file into memory:

```text
File
 │
 ▼
Read 1024 bytes
 │
 ▼
Process
 │
 ▼
Read Next 1024 bytes
 │
 ▼
Process
 │
 ▼
Repeat Until EOF
```

Example:

```c
char buffer[1024];
ssize_t bytesRead;

while ((bytesRead = read(fd, buffer, sizeof(buffer))) > 0)
{
    write(STDOUT_FILENO, buffer, bytesRead);
}
```

### Advantages

- Memory efficient
- Handles huge files
- Faster streaming
- Used by:
  - `cat`
  - `cp`
  - `grep`
  - `tail`
  - Network servers
  - File transfer utilities

---

# ⚙️ System Call Flow

When executing:

```c
read(fd, buffer, 100);
```

Flow:

```text
        Your Program
        (User Space)
              │
              ▼
      glibc Wrapper Function
              │
              ▼
      System Call Interface
              │
              ▼
         Linux Kernel
              │
              ▼
      Filesystem Driver
              │
              ▼
         Disk / Device
              │
              ▲
 Kernel copies data into buffer
              │
              ▲
     read() returns to program
```

Only the **kernel** communicates directly with hardware.

---

# 💻 Example Program

```c
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

int main()
{
    char buffer[1024];

    int fd = open("notes.txt", O_RDONLY);

    if (fd == -1)
    {
        perror("open");
        return 1;
    }

    ssize_t bytesRead;

    while ((bytesRead = read(fd, buffer, sizeof(buffer))) > 0)
    {
        write(STDOUT_FILENO, buffer, bytesRead);
    }

    close(fd);

    return 0;
}
```

This program:

1. Opens a file
2. Reads it in chunks
3. Prints the contents
4. Closes the file

---

# 🚀 Key Takeaways

- File Descriptors are **integers**, not pointers.
- `open()` returns an FD.
- `read()` reads bytes.
- `write()` writes bytes.
- `close()` releases the descriptor.
- Linux reserves:
  - `0 → stdin`
  - `1 → stdout`
  - `2 → stderr`
- Reading files in chunks is efficient and scalable.
- All file operations ultimately execute through **Linux system calls**.

---

## ⭐ If this repository helped you

Give it a ⭐ on GitHub and feel free to fork it or contribute!