# 📔 MyRecipy (가계부 앱)

> **"엑셀로 꼼꼼하게 관리하던 가계부, 이제 앱으로 더 스마트하게."**

**MyRecipy**는 자신만의 스타일로 엑셀 가계부를 커스텀해서 사용하던 친구의 요청으로 시작된 프로젝트입니다. 
단순한 기능 구현을 넘어, 안드로이드의 최신 기술 스택인 **Navigation 3**, **KMP(Kotlin Multiplatform)** 확장성, 그리고 **Koin**을 활용한 현대적인 아키텍처를 학습하고 적용하는 것을 목표로 합니다.

---

## ✨ Key Features (Roadmap)

- **Custom Ledger**: 엑셀의 유연함을 담은 사용자 맞춤형 소비 기록 로직.
- **Type-safe Navigation**: 구글의 차세대 내비게이션 `Navigation 3` 기반의 안전한 화면 전환.
- **Smart Sync**: `Ktor`를 활용한 데이터 백업 및 복구 (KMP 대응 설계).
- **Local First**: `Room`과 `KSP`를 이용한 빠르고 안정적인 로컬 데이터 저장소.
- **Modern UI**: `Jetpack Compose`와 `Material 3` 기반의 직관적인 디자인.

---

## 🛠 Tech Stack

| Category | Technology |
| :--- | :--- |
| **Language** | Kotlin |
| **UI** | Jetpack Compose (Material 3) |
| **Dependency Injection** | Koin (Core, Android, Compose, Navigation3) |
| **Navigation** | Navigation 3 (Alpha) |
| **Networking** | Ktor (CIO Engine, Content Negotiation, Logging) |
| **Database** | Room (with KSP) |
| **Serialization** | Kotlinx Serialization |
| **Others** | Google Credentials API, Splash Screen, Oss Licenses |

---

## 🏗 Architecture & Build System

- **Multi-module Strategy**: 기능별 모듈화를 통해 코드 재사용성과 유지보수성을 극대화합니다.
- **Build-logic (Convention Plugins)**: 중복되는 Gradle 설정을 `build-logic`으로 공통화하여 효율적으로 관리합니다.
- **Version Catalog**: `libs.versions.toml`을 통해 모든 프로젝트 의존성을 중앙 제어합니다.

---

## 🚀 Getting Started

### Prerequisites
- **Android Studio Jellyfish** | 2023.3.1 이상 권장
- **JDK 17** 이상
- **Kotlin 2.x** (Compose Compiler 포함)

### Installation
```bash
git clone
