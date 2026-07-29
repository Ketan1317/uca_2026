const box = document.getElementById("box");
const ul = document.createElement("ul");

const fetchData = async () => {
  try {
    const response = await fetch("https://dummyjson.com/users");

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }

    const data = await response.json();
    data.users.forEach((user) => {
      const li = document.createElement("li");
      li.textContent = `${user.firstName} ${user.lastName} - ${user.email}`;
      ul.appendChild(li);
    });

    box.appendChild(ul);
  } catch (error) {
    console.error("Failed to fetch users:", error);
    box.textContent = "No users found";
  }
};

fetchData();