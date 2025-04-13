# Foody 프로젝트

음식 관련 서비스를 제공하는 Spring Boot 기반 백엔드 애플리케이션입니다.

## 프로젝트 구조

```
foody
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.umc.foody
│   │   │       ├── domain
│   │   │       │   └── member
│   │   │       │       ├── controller
│   │   │       │       ├── entity
│   │   │       │       ├── repository
│   │   │       │       └── service
│   │   │       │           ├── command
│   │   │       │           └── query
│   │   │       ├── global
│   │   │       │   └── common
│   │   │       │       ├── base
│   │   │       │       └── exception
│   │   │       │           └── code
│   │   │       └── FoodyApplication.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── static
│   │       └── templates
│   └── test
│       └── java
│           └── com.umc.foody
│               └── FoodyApplicationTests.java
├── .gitignore
├── build.gradle
├── gradlew
├── gradlew.bat
└── README.md
```

## 코드 컨벤션

### 1. 패키지 구조

- **도메인형 패키지 구조**: 기능이 아닌 도메인(비즈니스 영역)별로 패키지를 구성합니다.
  - `domain`: 각 도메인별 비즈니스 로직이 위치합니다.
  - `global`: 프로젝트 전반에서 사용되는 공통 요소들이 위치합니다.

### 2. 도메인 패키지 내부 구조

각 도메인 패키지는 다음과 같은 내부 구조를 가집니다:
- `controller`: 해당 도메인의 API 엔드포인트를 관리합니다.
- `entity`: JPA 엔티티 클래스들이 위치합니다.
- `repository`: 데이터 접근 계층 인터페이스가 위치합니다.
- `service`: 비즈니스 로직이 구현되는 서비스 클래스들이 위치합니다.
  - `command`: 데이터 변경(CUD)을 담당하는 서비스를 구현합니다.
  - `query`: 데이터 조회(R)를 담당하는 서비스를 구현합니다.

### 3. 네이밍 컨벤션

- **클래스명**: 파스칼 케이스(PascalCase)를 사용합니다. 예: `MemberService`
- **메소드명, 변수명**: 카멜 케이스(camelCase)를 사용합니다. 예: `getUserById`
- **상수**: 대문자 스네이크 케이스(SNAKE_CASE)를 사용합니다. 예: `MAX_USER_COUNT`
- **패키지명**: 소문자만 사용합니다. 예: `com.umc.foody.domain.member`

### 4. 코드 스타일

- **들여쓰기**: 탭(Tab)을 사용합니다.
- **중괄호**: K&R 스타일을 사용합니다(여는 중괄호는 같은 줄에 배치).
- **최대 줄 길이**: 120자를 권장합니다.
- **메서드 분리**: 한 메서드는 한 가지 기능만 수행하도록 합니다.
- **주석**: 필요한 경우에만 주석을 추가하고, JavaDoc 스타일을 사용합니다.

### 5. 예외 처리

- 글로벌 예외 처리를 사용하여 일관된 응답 형식을 유지합니다.
- 커스텀 예외 클래스를 사용하여 비즈니스 로직에 관련된 예외를 명확히 구분합니다.

### 6. Lombok 사용

- 반복적인 코드를 줄이기 위해 Lombok을 적극적으로 활용합니다.
- 주로 사용하는 어노테이션: `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@Builder`

## Git 컨벤션

### 1. 브랜치 전략

- **main**: 제품으로 출시할 수 있는 브랜치
- **develop**: 다음 출시 버전을 개발하는 브랜치
- **feature/xxx**: 기능을 개발하는 브랜치
- **hotfix/xxx**: 출시 버전에서 발생한, 급하게 수정해야 하는 버그를 수정하는 브랜치

### 2. 커밋 메시지 컨벤션

커밋 메시지는 다음 형식을 따릅니다:
```
<type>: <subject>

<body>

<footer>
```

#### Type
- **feat**: 새로운 기능 추가
- **fix**: 버그 수정
- **docs**: 문서 수정
- **style**: 코드 formatting, 세미콜론 누락 등 (코드 변경 없음)
- **refactor**: 코드 리팩토링
- **test**: 테스트 코드, 리팩토링 테스트 코드 추가
- **chore**: 빌드 업무 수정, 패키지 매니저 수정 등

#### Subject
- 50자를 넘지 않고, 마침표를 붙이지 않습니다.
- 명령문으로 작성합니다. ("변경함" 보다는 "변경")

#### Body (선택 사항)
- 어떻게 보다는 무엇을, 왜 변경했는지 설명합니다.
- 여러 줄의 메시지를 작성할 땐 "-"로 구분합니다.

#### Footer (선택 사항)
- Breaking Changes가 있을 때 명시합니다.
- 해결된 이슈 번호를 명시합니다. (Closes #123)

### 3. Pull Request 컨벤션

PR 제목은 다음 형식을 따릅니다:
```
[<type>] <subject>
```

PR 본문에는 다음 내용을 포함합니다:
- 작업 내용 요약
- 특이사항
- 테스트 결과 (필요한 경우)

## 주요 디렉토리 설명

### domain

비즈니스 로직을 담당하는 도메인 계층입니다. 각 도메인은 독립적인 기능 단위로 구성됩니다.

#### member

사용자 관련 기능을 구현하는 도메인입니다.
- `controller`: 사용자 API 엔드포인트
- `entity`: 사용자 관련 엔티티 클래스
- `repository`: 사용자 데이터 접근 인터페이스
- `service/command`: 사용자 정보 생성/수정/삭제 서비스
- `service/query`: 사용자 정보 조회 서비스

### global

프로젝트 전반에서 사용되는 공통 모듈입니다.

#### common

- `base`: 공통으로 사용되는 기본 클래스들
  - `BaseEntity`: 생성일자, 수정일자, 삭제일자를 관리하는 JPA 엔티티 기본 클래스
  - `BaseResponse`: API 응답의 일관성을 위한 응답 래퍼 클래스
- `exception`: 예외 처리 관련 클래스들
  - `code`: 에러 코드와 메시지를 정의하는 인터페이스와 구현체
  - `ExceptionAdvice`: 전역 예외 처리기
  - `RestApiException`: 사용자 정의 예외 클래스

## 기술 스택

- Java 17
- Spring Boot 3.4.4
- Spring Data JPA
- Spring Security
- MySQL
- Gradle
- Lombok
