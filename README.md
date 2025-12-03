# Android Profile Page 📱

A simple and clean Android profile page built with Kotlin.  
This project demonstrates modern Android UI structure, reusable components, and best practices for building personal profile screens in mobile apps.

## Features ✨

- 📝 **User profile form** with fields:
  - 🧑‍💼 Full Name
  - 📞 Phone Number
  - 📧 Email
  - 🎂 Birth Date
  - 🚻 Gender selection (Male/Female)
- ⚠️ Field validation with error messages
- 💾 Save profile data locally using `SharedPreferences`
- 🛠 Reusable Compose UI components
- 🌈 Clean and modern design with gradient header

## Built With 🏗

- 🟣 Kotlin
- 🎨 Jetpack Compose
- 🏢 Android Studio
- 📂 SharedPreferences

## Usage 🚀

1. Clone the repository:

```bash
git clone https://github.com/chakad-4/profilePage.git
Open the project in Android Studio.

Build and run the app on an emulator or physical device.

Fill in the profile form and click "ثبت تغیرات" 💾 to save the data locally.

Project Structure 🗂
MainActivity.kt → Main activity containing the UI and logic

ui.theme → Colors, typography, and theme definitions

res/drawable → Icons and profile placeholder images

Notes 📝
💾 All data is saved locally on the device using SharedPreferences.

⚠️ Form validation ensures all fields are filled before saving.

🚻 Gender selection requires exactly one option to be selected.
