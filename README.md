# Beacon
지역별 실시간 재난 속보 및 안전 대응 API 서버 입니다.

## 프로젝트 목표
*  지역별로 실시간 가공된 재난 속보를 제공 하는 것이 목표입니다.
*  재난 발생시 대피소, 행동 요령 등 안내를 푸시 알람 및 지도 앱 서비스를 통해 전달 하는 것이 목표입니다.

## 주요기능
![image](https://github.com/Beacon-2023/Beacon-backend/assets/82764703/00b83de3-cf5f-4074-a3f3-265d0cb810e0)

자세한 주요기능은 https://github.com/Beacon-2023/Beacon-frontend 에서 확인할 수 있습니다.
## 시연 영상
하단 이미지를 클릭하면 영상을 시청할 수 있습니다.
<a href="https://www.youtube.com/watch?v=2kt1DZehcwI&ab_channel=%EC%8B%A0%EA%B7%BC%EC%9E%AC" rel="nofollow">
 <img src= "https://github.com/Beacon-2023/Beacon-backend/assets/82764703/6d0629be-65d9-497e-a366-c0cdd2bc7895">
</a>


## 개발언어 & 환경
### 백엔드: 
1. 개발 환경
- 인프라: AWS EC2 , AWS RDS , Firebase Clude Messaging
- DB: MySQL
- 개발 도구: IntelliJ
- 라이브러리: Jsoup
2. 개발 언어 및 프레임워크
- 언어: Java 17
- 프레임워크: Spring Boot 3.1.2
- ORM 라이브러리: Spring Data JPA
### 프론트:
1. 개발 환경
- 인프라: Firebase Cloud Messaging  
- 개발 도구: Android Studio 
- 라이브러리: Workmanager,Room
2. 개발 언어 및 프레임워크
- 언어: Kotlin


## 소스 디렉토리 구조
![image](https://github.com/Beacon-2023/Beacon-backend/assets/82764703/9cad8142-b720-4fe0-a168-3b5207489683)

## 프로젝트 아키텍처 
<img width="1200" alt="image" src="https://github.com/Beacon-2023/Beacon-backend/assets/82764703/f3639313-8fd6-4081-8fa3-90bfb375a8ed">

프론트 아키텍처는 https://github.com/Beacon-2023/Beacon-frontend 에서 확인할 수 있습니다.

## 사용자 요구사항
https://luck-hurricane-634.notion.site/1cabe7a63abd4e5fbb100bca0702e2e8?pvs=4

## erd schema
![image](https://github.com/Beacon-2023/Beacon-backend/assets/82764703/4f3d1ef6-47da-4b34-a9d0-5d2cb83c2c70)

## 라이브 API 서버
http://43.202.105.197:8080/swagger

## 플로우 차트 
1. <a href="https://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/sfc/dis/disasterMsgList.jsp?menuSeq=679">
   국민재난안전포털 사이트의 재난문자 스크래핑
</a>

 - SpringBoot에서 @Scheduling으로 10초마다 스크래핑
 - 스크래핑한 문자들은 중복확인을 거치고 필요한 재난문자들의 지역코드,발송시각,재난내용 그리고 재난 유형을 뽑아서 DB에 저장
 - 다음 PR에서 자세한 코드를 볼 수 있습니다 : https://github.com/Beacon-2023/Beacon-backend/pull/23
<img width="744" alt="스크린샷 2023-09-07 오후 2 33 56" src="https://github.com/Beacon-2023/Beacon-backend/assets/82764703/7b70d09a-49ab-42da-80d3-ee9387d84995">

2. <b>FCM을 사용하여 재난문자 푸시알림</b>
   - FCM Push 서버 구축
   - 새로운 재난문자가 들어오면 해당 재난문자의 지역코드를 확인
   - 해당 지역코드를 가진 fcm토큰에만 재난문자를 보냅니다
   - fcm 패키지에서 자세한 코드를 볼 수 있습니다
   - location 패키지에서 클라이언트에서 workmanager를 통해 15분마다 보내온 위치를 db에 저장하는 로직을 확인할 수 있습니다
   - 현재 위치 정보 서비스에서 2022.4.20부터 시행된 개정에 의해 사업자 등록을 해야 사용할 수 있는 기능 입니다 향후 다른 방향으로 진행할 예정입니다
   - 위치기반서비스 법률 참고 : https://www.kimchang.com/ko/insights/detail.kc?sch_section=4&idx=25117
3. <b>기본 및 커스텀 가이드라인 제공</b>
   - 기본가이드라인은 재난안전포털에서 제공해주는 기본가이드라인을 제공
   - 커스텀은 직접 사용자가 작성하여 db에 저장할 수 있게 제공
   - guideline 패키지에서 자세한 코드를 볼 수 있습니다
4. <b>회원가입 및 로그인 기능 제공</b>
   - session을 mysql에 저장하는 방식으로 했습니다. 향후 인메모리 db인 redis로 교체할 예정입니다
5. <b>사용자 근처 대피소 찾기 서비스</b>
   - 민방위대피소,지진옥외대피소,산불대피소 csv파일들을 추출하여 db에서 관리
     ![image](https://github.com/Beacon-2023/Beacon-backend/assets/82764703/1486162e-3c9c-4e1e-a7f9-144c3bf06472)
   - JPQL을 이용해서 해당 유저 위치 근처인 대피소을 클라이언트에 제공
   - 재난문자에 맞는 재난대피소를 제공
   - shelter 패키지에서 자세한 코드를 볼 수 있습니다
 
## 하면서 고민한 내용
https://github.com/Beacon-2023/Beacon-backend/wiki/Beacon-WIKI

## Getting Started
