import os

# Define the content of the README.md file
readme_content = """# Public Transport Management System - Java Project

## 📖 Overview
This project is a comprehensive Java-based backend system designed to manage the complex logistics of public transportation. It handles everything from database-style entity relationships to real-time scheduling logic and route optimization.

## 🏗️ System Architecture & Data Model
The project implements a relational data structure to ensure data integrity and efficient querying:

* **Travel (נסיעה)**: The central entity connecting a specific Bus, Driver, and Line with a departure time.
* **Bus (אוטובוס)**: Stores unique identifiers, license plates, and seating capacity.
* **Driver (נהג)**: Manages driver details, contact info, and performance ratings.
* **Line (קו)**: Defines the route with a unique number, origin, destination, and a list of stations.
* **Station (תחנה)**: Contains station names and a reference list of lines passing through.
* **Station_Line (תחנה במסלול קו)**: A junction table managing the **Many-to-Many** relationship between Lines and Stations. It tracks the specific order of each station within a route.

## ⚙️ Business Logic & Assumptions
To simulate a reliable transportation environment, the system operates under these core assumptions:
* **Fixed Travel Time**: The duration between any two consecutive stations is precisely **1 minute**.
* **Daily Consistency**: The schedule remains identical every day, ensuring predictable planning.

## 🛠️ Key Functionalities

### 👮 Admin Management (מנהל)
* **Trip Creation**: Logic for generating new travel instances.
* **Dynamic Route Updates**: Ability to add or remove stations from an existing line. The system automatically recalculates and updates the station sequence for all subsequent stations in the route.

### 📱 Passenger Information Service ("Kal-Kav")
* **Station Search**: Users can input a station and line number to receive the exact arrival time.
* **Live Tracking**: Real-time identification of bus locations along the route axis.
* **Auditory Information**: A feature to hear or list all stations belonging to a specific line.
* **Advanced Scheduling**: 
    * Viewing all trips for the day.
    * Filtering trips by a specific hour.
    * Finding the last trip of the day.

## 🚀 Technical Stack
* **Language**: Java
* **Database Logic**: Relational mapping with PK/FK constraints.
* **Version Control**: Git & GitHub.
"""

# Save the content to a file
file_path = 'README.md'
with open(file_path, 'w', encoding='utf-8') as f:
    f.write(readme_content)

print(f"File saved to: {file_path}")
