# 🐬 Setting Up MySQL Server on Linux

A quick guide to installing and starting **MySQL Server** on Linux. This guide is intended for **students** using Ubuntu/Debian-based distributions, with notes for Fedora/RHEL users.

---

## 📋 Prerequisites

Before you begin, make sure you have:

- ✅ A Linux system (Ubuntu/Debian recommended)
- ✅ Administrator (`sudo`) privileges
- ✅ An active internet connection

---

## 📦 Step 1: Update Package Lists

Update your package repository to ensure you install the latest available version.

```bash
sudo apt update
```

> **Why?**
> Updating package lists ensures your system knows about the latest software versions and security updates.

---

## 📥 Step 2: Install MySQL Server

Install MySQL Server using the package manager.

```bash
sudo apt install mysql-server -y
```

The `-y` flag automatically confirms the installation.

### 🟥 Fedora / RHEL

```bash
sudo dnf install mysql-server -y
```

---

## ▶️ Step 3: Start the MySQL Service

Start the MySQL server.

```bash
sudo systemctl start mysql
```

---

## 🔄 Step 4: Enable MySQL at Boot

Enable MySQL so it starts automatically whenever the system boots.

```bash
sudo systemctl enable mysql
```

---

## ✅ Step 5: Verify the Installation

Check whether the MySQL service is running correctly.

```bash
sudo systemctl status mysql
```

If everything is working properly, you should see something similar to:

```text
● mysql.service - MySQL Community Server
     Loaded: loaded
     Active: active (running)
```

Press **Q** to exit the status screen.

---

## 🚀 Quick Installation Commands

If you're comfortable with Linux, you can complete the setup with these commands:

```bash
sudo apt update
sudo apt install mysql-server -y
sudo systemctl start mysql
sudo systemctl enable mysql
sudo systemctl status mysql
```

---

## 📌 Platform Notes

| Distribution | Install Command |
|--------------|-----------------|
| Ubuntu | `sudo apt install mysql-server -y` |
| Debian | `sudo apt install mysql-server -y` |
| Fedora | `sudo dnf install mysql-server -y` |
| RHEL | `sudo dnf install mysql-server -y` |

---

## 🎉 Success!

If the MySQL service status shows **`active (running)`**, your MySQL Server has been installed successfully and is ready to use.

In the next step, you can connect to MySQL using:

```bash
sudo mysql
```