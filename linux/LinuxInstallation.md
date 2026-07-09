# 🚀 Linux Development Environment Setup Guide

A beginner-friendly guide to setting up a complete development environment on a Linux system. This guide covers installing and verifying essential tools required for C/C++ development, Java programming, Git version control, and terminal-based editing.

> **Target Audience:** Students, beginners, and developers setting up a fresh Linux environment.

---

## 📚 What You'll Install

- 🐧 Linux Environment Verification
- 🌿 Git
- ☕ Java (JDK)
- ✍️ Vim Editor
- ⚙️ GCC (C Compiler)
- 📂 GitHub Repository Setup

---

# 🐧 1. Verify Your Linux Distribution

Before installing any software, identify your Linux distribution.

### Command

```bash
cat /etc/os-release
```

### Expected Output

```text
NAME="Ubuntu"
VERSION="24.04 LTS"
ID=ubuntu
...
```

---

# 🌿 2. Install & Configure Git

Git is the most widely used version control system.

## Install Git

### Ubuntu / Debian

```bash
sudo apt update
sudo apt install git -y
```

### Fedora / RHEL

```bash
sudo dnf install git -y
```

---

## Verify Installation

```bash
git --version
```

Example

```text
git version 2.43.0
```

---

## Configure Git

Set your username and email.

```bash
git config --global user.name "Your Name"
git config --global user.email "you@example.com"
```

Verify the configuration.

```bash
git config --list
```

---

# 📂 3. Create a GitHub Repository

Create a new repository on GitHub for storing your projects.

Suggested structure:

```
linux-setup/
│
├── README.md
├── .gitignore
├── HelloJava.java
└── main.c
```

Clone the repository.

```bash
git clone <repository-url>
```

Move inside it.

```bash
cd repository-name
```

---

## Create a `.gitignore`

Example:

```gitignore
*.class
*.o
*.out
*.exe
main_app
.vscode/
.idea/
```

Commit your changes.

```bash
git add .
git commit -m "Initial project setup"
git push
```

---

# ☕ 4. Install Java

Install the Java Development Kit (JDK).

### Ubuntu / Debian

```bash
sudo apt install default-jdk -y
```

### Fedora / RHEL

```bash
sudo dnf install java-latest-openjdk-devel -y
```

---

## Verify Java

```bash
java -version
```

```bash
javac -version
```

---

## Create a Sample Java Program

```java
public class HelloJava {

    public static void main(String[] args) {
        System.out.println("Hello, Linux!");
    }

}
```

Compile

```bash
javac HelloJava.java
```

Run

```bash
java HelloJava
```

---

# ✍️ 5. Install Vim

Vim is one of the most popular terminal-based text editors.

### Ubuntu / Debian

```bash
sudo apt install vim -y
```

### Fedora / RHEL

```bash
sudo dnf install vim -y
```

---

## Verify Installation

```bash
vim --version
```

Expected output begins with something similar to:

```text
VIM - Vi IMproved 9.x
```

---

# ⚙️ 6. Install GCC (C Compiler)

Install the GNU Compiler Collection.

### Ubuntu / Debian

```bash
sudo apt install build-essential -y
```

### Fedora /RHEL

```bash
sudo dnf groupinstall "Development Tools"
```

---

## Verify GCC

```bash
gcc --version
```

---

## Create a Simple C Program

```c
#include <stdio.h>

int main()
{
    printf("Hello from Linux!\n");
    return 0;
}
```

Compile

```bash
gcc main.c -o main_app
```

Run

```bash
./main_app
```

Expected Output

```text
Hello from Linux!
```

---

# 📁 Recommended Project Structure

```
linux-setup
│
├── README.md
├── .gitignore
├── HelloJava.java
└── main.c
```

---

# 📋 Installation Checklist

- ✅ Linux environment verified
- ✅ Git installed
- ✅ Git configured
- ✅ GitHub repository created
- ✅ Java installed
- ✅ Java program compiled successfully
- ✅ Vim installed
- ✅ GCC installed
- ✅ C program compiled successfully

---

# 🛠 Useful Commands

| Task | Command |
|------|----------|
| Update packages | `sudo apt update` |
| Install Git | `sudo apt install git -y` |
| Check Git | `git --version` |
| Configure Git | `git config --global` |
| Clone Repository | `git clone <url>` |
| Install Java | `sudo apt install default-jdk -y` |
| Check Java | `java -version` |
| Compile Java | `javac File.java` |
| Run Java | `java ClassName` |
| Install Vim | `sudo apt install vim -y` |
| Check Vim | `vim --version` |
| Install GCC | `sudo apt install build-essential -y` |
| Compile C | `gcc main.c -o main_app` |
| Run Program | `./main_app` |

---

# 🎉 You're Ready!

Your Linux development environment is now configured with:

- 🐧 Linux
- 🌿 Git
- ☕ Java
- ✍️ Vim
- ⚙️ GCC
- 📂 GitHub

You can now start building, compiling, and managing projects efficiently using the Linux command line.