# 🚁 DroneTelemetryViewer

A modern Android application built with **Kotlin + Jetpack Compose** for viewing real-time MAVLink drone telemetry using UDP/TCP connections.

---

# ✨ Features

## 📡 MAVLink Telemetry

- Real-time telemetry monitoring
- UDP & TCP MAVLink connection
- Live telemetry updates
- Auto reconnect support
- Connection state monitoring

---

## 📊 Telemetry Dashboard

Displays:

- Latitude
- Longitude
- Altitude
- Battery Percentage
- Flight Mode
- Armed Status

---

## 🔌 Connection Management

- UDP connection
- TCP connection
- Auto reconnect
- Connection timeout handling
- Invalid IP/Port handling
- Stream interruption recovery

---

## 🛡 Error Handling

Handles:

- Invalid IP address
- Invalid port
- Connection timeout
- Null telemetry packets
- Unsupported MAVLink packets
- Disconnected stream
- Socket errors

---

## 🧠 Architecture

Project follows:

- Clean Architecture
- MVVM
- Repository Pattern
- Dependency Injection (Hilt)
- Kotlin Coroutines
- StateFlow
- Jetpack Compose

---

# 🏗 Project Structure

```bash
app/
│
├── core/
│   ├── common/
│   ├── mavlink/
│   ├── network/
│   └── ui/
│
├── data/
│   ├── connection/
│   └── telemetry/
│
├── domain/
│   ├── model/
│   ├── repository/
│   └── usecase/
│
├── feature/
│   ├── connection/
│   └── dashboard/
│
└── navigation/
```

---

# 🧰 Tech Stack

| Technology | Usage |
|------------|-------|
| Kotlin | Main Language |
| Jetpack Compose | UI |
| Hilt | Dependency Injection |
| Coroutines | Async Programming |
| MAVLink | Drone Communication |
| StateFlow | State Management |
| MVVM | Architecture |

---

# 📸 Screenshots

<p align="center">
  <img src="https://github.com/mdAkhtar21/DroneTelemetry/blob/master/BatteryConnection.jpeg" width="250"/>
  <img src="https://github.com/mdAkhtar21/DroneTelemetry/blob/master/ConnectionScreen.jpeg" width="250"/>
  <img src="https://github.com/mdAkhtar21/DroneTelemetry/blob/master/DashboardScreen.jpeg" width="250"/>
</p>
---

# 🚀 Getting Started

## 1️⃣ Clone Repository

```bash
git clone https://github.com/mdAkhtar21/DroneTelemetry.git
```

---

## 2️⃣ Open Project

Open in:

- Android Studio Hedgehog or newer

---

## 3️⃣ Add Dependencies

Gradle sync project.

---

## 4️⃣ Run Application

Connect your:

- PX4 SITL
- ArduPilot SITL
- Real Drone Telemetry Stream

---

# 📡 Testing Telemetry

## UDP Example

| Field | Value |
|------|------|
| IP | 0.0.0.0 |
| Port | 14550 |

---

## TCP Example

| Field | Value |
|------|------|
| IP | 192.168.1.10 |
| Port | 5760 |

---

# 🧪 SITL Testing

## PX4

```bash
make px4_sitl jmavsim
```

---

## ArduPilot

```bash
sim_vehicle.py -v ArduCopter --console --map
```

---

# 📦 Dependencies

```gradle
implementation("com.google.dagger:hilt-android:2.50")
implementation("androidx.navigation:navigation-compose")
implementation("io.dronefleet.mavlink:mavlink:1.1.11")
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
```

---

# 🔐 Permissions

```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

---

# 🎯 Future Improvements

- Google Maps Integration
- Live Drone Tracking
- Mission Planner
- MAVLink Command Sender
- Flight Path Recording
- Offline Logging
- Drone Camera Feed
- Waypoint Navigation
