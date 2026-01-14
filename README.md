




# 🏥 Patient–Doctor Video Call App (Android)

A modern **Android application** built with **Jetpack Compose** that enables **patients to book appointments and make real-time audio/video calls with doctors**, similar to WhatsApp calling.  
The app uses **Firebase** for authentication & data storage and **ZEGOCLOUD** for high-quality audio/video calling.

---

## 📌 Features

- 🔐 **Authentication**
    - Login & Signup using Firebase Authentication
    - Role-based signup: **Patient** or **Doctor**

- 👤 **User Roles**
    - Patient Dashboard
    - Doctor Dashboard

- 📅 **Appointment Flow**
    - Patient books an appointment
    - Patient clicks **“Book Appointment / Call Now”**
    - Doctor joins the call
    - Smooth real-time audio/video call

- 📞 **Audio & Video Calling**
    - Powered by **ZEGOCLOUD**
    - Low latency & stable call experience
    - Audio & Video call support

- ☁️ **Cloud Backend**
    - Firebase Firestore for storing patient & doctor data
    - Real-time user data sync

- 🧭 **Navigation**
    - Jetpack Navigation Compose
    - Auto redirect if user is already logged in
    - Role-based navigation (Patient / Doctor)

- 🎨 **Modern UI**
    - Fully built with **Jetpack Compose**
    - Clean, minimal & responsive UI

---

## 🛠 Tech Stack

| Category | Technology |
|--------|-----------|
| Language | Kotlin |
| UI | Jetpack Compose |
| Navigation | Navigation Compose |
| Authentication | Firebase Auth |
| Database | Firebase Firestore |
| Video/Audio Calls | **ZEGOCLOUD SDK** |
| Architecture | MVVM |
| Async | Kotlin Coroutines |
| Minimum SDK | 24 |
| Target SDK | 34 |

---

## 🧩 App Flow

```text

Login / Signup
   ↓
Select Role (Patient / Doctor)
   ↓
Patient Home / Doctor Home
   ↓
Patient Books Appointment
   ↓
Patient Clicks "Call Now"
   ↓
Doctor Joins
   ↓
Audio / Video Call Starts

```
[![Demo Video]](./Assets/mt.mp4)

## 📚 ScreenShots
|             Screen                    |           Screen                 |            Screen                 |
|:-------------------------------------:|:--------------------------------:|:---------------------------------:|
| <img src = "/Assets/first.jpeg">      | <img src = "/Assets/second.jpeg">| <img src = "/Assets/third.jpeg">  |

|        Screen                     |            Screen                  |            Screen                    |             
|:---------------------------------:|:----------------------------------:|:------------------------------------:|
| <img src = "/Assets/fourth.jpeg"> | <img src = "/Assets/fifth.jpeg">   | <img src = "/Assets/sixth.jpeg">     |

|          Screen                      |            Screen                 |                                        |
|:------------------------------------:|:---------------------------------:|:--------------------------------------:|
| <img src = "/Assets/seventh.jpeg">   | <img src = "/Assets/eigth.jpeg">  | 

