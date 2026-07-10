# ⚙️ Installing GCC (GNU Compiler Collection) on Linux

A step-by-step guide to installing **GCC**, the GNU Compiler Collection, on Linux. GCC is one of the most widely used compilers for **C**, **C++**, and several other programming languages.

> **Audience:** Beginners, students, and developers setting up a Linux development environment.

---

## 📚 What is GCC?

**GCC (GNU Compiler Collection)** is an open-source compiler suite developed by the GNU Project.

It supports multiple programming languages, including:

- ✅ C
- ✅ C++
- ✅ Objective-C
- ✅ Fortran
- ✅ Go
- ✅ Ada

For most C programming tasks, you'll primarily use the `gcc` compiler.

---

# 🐧 Step 1: Update Your System

Before installing any packages, update your package lists.

### Ubuntu / Debian

```bash
sudo apt update
```

### Fedora / RHEL

```bash
sudo dnf check-update
```

---

# 📦 Step 2: Install GCC

### Ubuntu / Debian

Install GCC along with other essential development tools.

```bash
sudo apt install build-essential -y
```

> The `build-essential` package includes:
>
> - GCC
> - G++
> - Make
> - Standard C libraries
> - Development headers

---

### Fedora / RHEL

```bash
sudo dnf groupinstall "Development Tools"
```

---

# ✅ Step 3: Verify Installation

Check whether GCC has been installed successfully.

```bash
gcc --version
```

Example output:

```text
gcc (Ubuntu 13.2.0-23ubuntu4) 13.2.0
```

You can also verify the C++ compiler:

```bash
g++ --version
```

---

# 📝 Step 4: Create Your First C Program

Create a file named `main.c`.

```c
#include <stdio.h>

int main()
{
    printf("Hello, GCC!\n");
    return 0;
}
```

---

# ⚙️ Step 5: Compile the Program

Compile the source file using GCC.

```bash
gcc main.c -o main_app
```

### Command Breakdown

| Part | Description |
|------|-------------|
| `gcc` | GNU C Compiler |
| `main.c` | Source file |
| `-o` | Specifies output filename |
| `main_app` | Executable to generate |

---

# ▶️ Step 6: Run the Executable

Execute the compiled program.

```bash
./main_app
```

Expected output:

```text
Hello, GCC!
```

---

# 📂 How Compilation Works

```text
main.c
   │
   ▼
 GCC Compiler
   │
   ▼
Object Code
   │
   ▼
Linker
   │
   ▼
main_app (Executable)
```

---

# 🛠 Useful GCC Commands

| Command | Purpose |
|---------|----------|
| `gcc --version` | Display GCC version |
| `gcc file.c` | Compile using default output (`a.out`) |
| `gcc file.c -o app` | Specify executable name |
| `gcc -Wall file.c` | Enable common compiler warnings |
| `gcc -g file.c` | Include debugging information |
| `gcc -O2 file.c` | Optimize generated code |
| `gcc -c file.c` | Compile without linking |

---

# 📁 Example Project Structure

```text
c-project/
│
├── README.md
├── main.c
└── main_app
```

> **Note:** The executable (`main_app`) is typically excluded from version control using a `.gitignore` file.

Example `.gitignore`:

```gitignore
main_app
*.o
*.out
```

---

# 🎯 Summary

- Install GCC using your distribution's package manager.
- Verify the installation with `gcc --version`.
- Write your C source code.
- Compile it with `gcc`.
- Run the generated executable.

---

# 🚀 Next Steps

Once GCC is installed, you can begin exploring:

- 📌 Variables and Data Types
- 📌 Functions
- 📌 Pointers
- 📌 File Handling
- 📌 Dynamic Memory Allocation
- 📌 System Calls
- 📌 Makefiles
- 📌 Debugging with GDB