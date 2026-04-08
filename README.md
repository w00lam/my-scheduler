# 개인 일정 관리 서비스 (My Scheduler)

> **Spring Boot와 JPA를 활용한 RESTful 일정 관리 서버입니다.**

---

## 프로젝트 개요
- **설명**: 사용자가 자신의 일정을 등록, 조회, 수정, 삭제할 수 있는 관리 시스템입니다.
- **핵심 가치**: 데이터 무결성을 위한 비밀번호 검증과 JPA Auditing을 활용한 자동화된 시간 기록.

---

## 기술 스택
- **Language**: Java 17
- **Framework**: Spring Boot 3.x
- **Database**: H2 Database
- **ORM**: Spring Data JPA
- **API Test**: Postman, Swagger (OpenAPI 3.0)

---

## 데이터베이스 설계

```mermaid
classDiagram
direction TB
class schedules {
    bigint id PK "고유 식별자"
    varchar(255) title "일정 제목"
    varchar(255) author "작성자명"
    varchar(255) description "일정 상세 내용"
    varchar(255) password "검증용 비밀번호"
    datetime(6) created_at "생성 일시"
    datetime(6) updated_at "수정 일시"
}
```

---

## 실행 방법

### 1. 애플리케이션 실행
* **IDE 사용 시**: `MySchedulerApplication.java` 파일을 실행(Run)합니다.
* **터미널(Gradle) 사용 시**: 프로젝트 루트 경로에서 아래 명령어를 입력합니다.
```bash
./gradlew bootRun
```

### 2. API 테스트 (Swagger)
* **주소**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
* **방법**: 접속 후 각 API의 **'Try it out'** 버튼을 클릭하여 요청을 보낼 수 있습니다.

### 3. API 명세서
| 기능 | 메서드 | 엔드포인트                         | 설명 |
| :--- | :---: |:------------------------------| :--- |
| **일정 생성** | `POST` | `/api/schedules`              | 새로운 일정을 등록합니다. (비밀번호 필수) |
| **일정 목록 조회** | `GET` | `/api/schedules`              | 전체 목록 혹은 작성자명 필터 조회를 수행합니다. |
| **일정 단건 조회** | `GET` | `/api/schedules/{scheduleId}` | 특정 ID를 가진 일정의 상세 정보를 조회합니다. |
| **일정 수정** | `PATCH` | `/api/schedules/{scheduleId}` | **비밀번호 일치 시** 일정 제목과 작성자명을 수정합니다. |
| **일정 삭제** | `DELETE` | `/api/schedules/{scheduleId}` | **비밀번호 일치 시** 해당 일정을 완전히 삭제합니다. |

---
## 요구사항 체크리스트

<details>
<summary><b>1️⃣ 필수 기능 체크리스트</b></summary>

### Lv 0. API 명세 및 ERD 작성
- [x] <ins>**API 명세서 작성하기**</ins>
  - [x]  API명세서는 프로젝트 root(최상위) 경로의 `README.md`에 작성
- [x] <ins>**ERD 작성하기**</ins>
  - [x] ERD는 프로젝트 root(최상위) 경로의 README.md에 첨부

### Lv 1. 일정 생성
- [x] <ins>**일정 생성 (일정 작성하기)**</ins>
  - [x] 일정 생성 시, 포함되어야할 데이터
    - [x] `일정 제목`, `일정 내용`, `작성자명`, `비밀번호`, `작성/수정일`을 저장
    - [x] `작성/수정일`은 날짜와 시간을 모두 포함한 형태
  - [x] 각 일정의 고유 식별자(ID)를 자동으로 생성하여 관리
  - [x] 최초 생성 시, `수정일`은 `작성일`과 동일
  - [x] `작성일`, `수정일` 필드는 `JPA Auditing`을 활용하여 적용
  - [x] API 응답에 `비밀번호`는 제외해야 합니다.

### Lv 2. 일정 조회
- [x] <ins>**전체 일정 조회**</ins>
  - [x] `작성자명`을 기준으로 등록된 일정 목록을 전부 조회
      - [x] `작성자명`은 조회 조건으로 포함될 수도 있고, 포함되지 않을 수도 있습니다.
      - [x] 하나의 API로 작성해야 합니다.
  - [x] `수정일` 기준 내림차순으로 정렬
  - [x] API 응답에 `비밀번호`는 제외해야 합니다.
- [x] <ins>**선택 일정 조회**</ins>
  - [x] 선택한 일정 단건의 정보를 조회할 수 있습니다.
      - [x] 일정의 고유 식별자(ID)를 사용하여 조회합니다.
  - [x] API 응답에 `비밀번호`는 제외해야 합니다.

### Lv 3. 일정 수정
- [x] <ins>**선택한 일정 수정**</ins>
  - [x] 선택한 일정 내용 중 `일정 제목`, `작성자명`만 수정 가능
    - [x] 서버에 일정 수정을 요청할 때 `비밀번호`를 함께 전달합니다.
    - [x] `작성일`은 변경할 수 없으며, `수정일`은 수정 완료 시, 수정한 시점으로 변경되어야 합니다.
  - [x] API 응답에 `비밀번호`는 제외해야 합니다.

### Lv 4. 일정 삭제
- [x] <ins>**선택한 일정 삭제**</ins>
  - [x] 선택한 일정을 삭제할 수 있습니다.
    - [x] 서버에 일정 삭제을 요청할 때 비밀번호를 함께 전달합니다.

</details>

---

## 프로젝트 구조
```text
src/main/java/com/woolam/myscheduler/
├── MySchedulerApplication.java      # 메인 애플리케이션 클래스
├── config/
│   └── SwaggerConfig.java           # Swagger 및 OpenAPI 설정
├── controller/
│   └── ScheduleController.java      # API 엔드포인트 핸들러
├── dto/
│   ├── ScheduleCreateRequest.java   # 생성 요청 DTO
│   ├── ScheduleCreateResponse.java  # 생성 응답 DTO
│   ├── ScheduleDeleteRequest.java   # 삭제 요청 DTO
│   ├── ScheduleGetRequest.java      # 조회 요청 DTO
│   ├── ScheduleGetResponse.java     # 조회 응답 DTO
│   ├── ScheduleUpdateRequest.java   # 수정 요청 DTO
│   └── ScheduleUpdateResponse.java  # 수정 응답 DTO
├── entity/
│   ├── BaseEntity.java              # 공통 시간 매핑 (Auditing)
│   └── Schedule.java                # 일정 도메인 엔티티
├── repository/
│   └── ScheduleRepository.java      # JPA 리포지토리 인터페이스
└── service/
    └── ScheduleService.java         # 비즈니스 로직 및 검증
```

---

## 프로젝트 회고 (Retrospective)

<details>
<summary><b> 필수 기능</b></summary>

### 개발하며 고민하고 느낀 점

#### 1. 동적 조회 로직에 대한 고민과 해결
전체 일정 조회 시 '작성자명'이라는 조건의 유무에 따라 서로 다른 결과를 반환해야 하는 과제가 있었습니다.
- **문제:** 파라미터가 `null`일 경우 발생할 수 있는 `NullPointerException`과 로직 분기 처리의 복잡성.
- **해결:** Java의 `Optional`을 사용하여 파라미터의 부재 가능성을 명시적으로 처리하고 `Stream API`를 활용해 조건이 있을 때는 필터링된 결과를 없을 때는 전체 리스트를 반환하는 간결하고 선언적인 코드를 구현했습니다.
- **배경:** 이 과정을 통해 **"유연한 코드가 곧 견고한 서비스의 밑바탕이 된다"** 는 것을 배웠으며 함수형 프로그래밍 스타일이 가독성과 유지보수성에 주는 이점을 또 한번 체감할 수 있었습니다.

#### 2. 개발 프로세스에 대한 새로운 접근과 도구의 활용
일반적으로 협업 시에는 ERD와 API 명세를 선행하는 것이 정석이지만 이번 과제에서는 **"구현과 문서화의 자동화"** 라는 기술적 경험에 초점을 맞추어 진행해 보았습니다.

- **도구의 적극적 활용**:
    - 기능을 먼저 구현한 뒤 **Javadoc과 OpenAPI(Swagger)** 를 활용하여 코드가 명세가 되는 동적 문서 생성 과정을 경험했습니다.
    - IntelliJ의 내장 기능을 활용해 실제 구현된 DB 스키마로부터 **ERD를 역공학** 으로 추출하여 마크다운에 반영했습니다.
- **배운 점**:
    - 코드가 변할 때마다 문서가 자동으로 동기화되는 환경을 구축하며 수동 문서화의 실수를 줄이는 자동화의 가치를 배웠습니다.
    - 비록 이번에는 경험 중심의 역순 진행이었으나 이를 통해 역으로 **"왜 설계가 선행되어야 하는지"** 에 대한 중요성을 다시금 깊게 체감하는 계기가 되었습니다.

</details>