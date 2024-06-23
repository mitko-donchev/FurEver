[![Android CI](https://github.com/mitko-donchev/FurEver/actions/workflows/android.yml/badge.svg?branch=master)](https://github.com/mitko-donchev/FurEver/actions/workflows/android.yml)

# **FurEver - Stray Dog Adoption App**

**FurEver** aims to revolutionize the way stray dogs find their forever homes. Mimicking the user
experience of popular dating apps, FurEver allows potential adopters to swipe through profiles of
dogs, “liking” or “skipping” as they go. Our mission is to make the process of adopting stray dogs
engaging, efficient, and compassionate.

## **Table of Contents**

1. [Introduction](#introduction)
2. [Current Features](#current-features)
3. [Installation](#installation)
4. [Usage](#usage)
5. [Architecture](#architecture)
6. [Tech Stack](#tech-stack)
7. [UI/UX Design](#uiux-design)
8. [Demo Video](#demo-video)
9. [Future Development](#future-development)

## **Introduction**

**FurEver** combines the swipe-and-like functionality of dating apps with the noble cause of dog
adoption. Potential adopters can easily browse through dog profiles, learning about each dog’s
personality, history, and needs before deciding to connect with a rescue organization.

## **Current Features**

- **Custom Splash Screen**: Featuring a delightful animation of a running dog.
- **Landing Screen (Home)**: Users can interact with a mock list of dogs, simulating the swiping
  mechanism for adopting dogs.
- **User Interaction**: Like or skip dogs to build a personalized list of potential companions.

## **Installation**

### Prerequisites

- Android Studio Koala or newer
- JDK 17

### Steps

1. Clone the repository:
   ```
   git clone https://github.com/mitko-donchev/ForEver.git
   ```
2. Open the project in Android Studio.
3. Build the project:
   ``` 
   ./gradlew assembleMock 
   ```
4. Run the app on an emulator or connected device.

## Usage

- **Launch the App**: The splash screen will appear with an engaging animation.
- **Navigate to Home**: After the splash screen, the landing screen with a mock list of dogs will be
  displayed.
- **Browse Dogs**: Swipe through dog profiles. “Like” or “Skip” dogs to start building a list of
  favorites.

## Architecture

**FurEver** uses a multi-module architecture to ensure scalability and maintainability. The app
incorporates the latest Android architectural patterns including MVVM (Model-View-ViewModel) and
MVI (Model-View-Intent).

### Modules

- **App Module**: Core application functionalities and UI.
- **Data Module**: Manages data sources and repositories.
- **Domain Module**: Contains use cases and domain models.

### Firebase Integration

Partially integrated to support future features like real-time updates and user authentication.

## Tech Stack

- **UI**: Jetpack Compose for a modern, declarative UI.
- **Dependency Injection**: Hilt for managing dependencies efficiently.
- **Architecture**: Multi-module, MVVM, MVI.
- **Material Design**: Adhering to the latest Material Design guidelines for a seamless user
  experience.
- **Language**: Kotlin.

## UI/UX Design

The UI/UX of **FurEver** has been meticulously crafted to provide an intuitive and delightful user
experience. Special thanks to [Aleksandra Gateva](https://www.linkedin.com/in/alexandra-gateva/) for
their exceptional work on the design.

## Demo Video

Watch our demo video showcasing the splash screen and landing page in action!

![Output sample](/video/furever_demo.gif)

## Future Development

- **Complete Firebase Integration**: Enable real-time data and user authentication.
- **Enhanced Dog Profiles**: Add more details and photos.
- **User Accounts**: Allow users to save their liked dogs and revisit them.
- **Notifications**: Inform users about new dogs and adoption updates.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE.md) file for
details.
